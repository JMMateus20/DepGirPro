package com.backend.tlg.depgirpro.services.auth;

import com.backend.tlg.depgirpro.entity.CryptoKey;
import com.backend.tlg.depgirpro.entity.Persona;
import com.backend.tlg.depgirpro.entity.SecretKeyEnc;
import com.backend.tlg.depgirpro.exceptions.InternalServerExceptionManaged;
import com.backend.tlg.depgirpro.repository.CryptoKeyRepository;
import com.backend.tlg.depgirpro.repository.SecretKeyRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class JWTService {

    @Value("${security.jwt.expiration-in-minutes}")
    private Long EXPIRATION_IN_MINUTES;
    @Autowired
    private CryptoKeyGenerator skg;
    @Autowired
    private CryptoService cryptoService;
    @Autowired
    private CryptoKeyRepository cryptoRep;
    @Autowired
    private SecretKeyRepository secretKeyRep;
    private String secretKey;


    @PostConstruct
    public void metodoPostInyeccionDeDependencias() {
        if (this.cryptoRep.findAll().isEmpty() && this.secretKeyRep.findAll().isEmpty()){
            return;
        }
        String cryptoKey=skg.generateBase64CryptoKey();
        CryptoKey cryptoEntity=this.cryptoRep.encontrarUltimo();
        SecretKeyEnc secretEntity=this.secretKeyRep.encontrarUltimo();
        if (cryptoEntity.isValid() && secretEntity.isValid()) {
            secretKey=decrypt(secretEntity.getSecretKey(), cryptoEntity.getCryptoKey());
            cryptoEntity.setValid(false);
            secretEntity.setValid(false);
            cryptoRep.save(cryptoEntity);
            secretKeyRep.save(secretEntity);
        }else {
            throw new InternalServerExceptionManaged("No se pudo realizar el proceso de encriptado y desencriptado de la secretKey");
        }
        CryptoKey cryptoKeyNew=this.cryptoRep.save(new CryptoKey(cryptoKey, true));
        this.secretKeyRep.save(new SecretKeyEnc(encrypt(secretKey, cryptoKeyNew.getCryptoKey()), true));
    }



    public String generarToken(Persona persona, Map<String, Object> claims){
        Date issuedAt=new Date(System.currentTimeMillis());
        Date expiration=new Date((EXPIRATION_IN_MINUTES*60*1000)+issuedAt.getTime());
        return Jwts.builder()
                .header().type("JWT")
                .and()
                .subject(persona.getCorreo())
                .issuedAt(issuedAt)
                .expiration(expiration)
                .claims(claims)
                .signWith(generarKey(), Jwts.SIG.HS256)
                .compact();
    }

    public String extractUsername(String jwt){
        return extractAllClaims(jwt).getSubject();

    }

    private Claims extractAllClaims(String jwt){
        return Jwts.parser().verifyWith(generarKey()).build()
                .parseSignedClaims(jwt).getPayload();
    }

    private SecretKey generarKey(){
        byte[] secretAsBytes= Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(secretAsBytes);
    }

    private String encrypt(String textoAEncriptar, String key) {
        try {
            return cryptoService.encrypt(textoAEncriptar, key);
        }catch(Exception e) {
            throw new InternalServerExceptionManaged("No se pudo encriptar la clave secreta");
        }
    }


    private String decrypt(String textoADesencriptar, String key) {
        try {
            return cryptoService.decrypt(textoADesencriptar, key);
        }catch(Exception e) {
            throw new InternalServerExceptionManaged("No se pudo desencriptar la clave secreta");
        }
    }

    public String extractTokenFromRequest(HttpServletRequest request){
        String header=request.getHeader("Authorization");
        if (!StringUtils.hasText(header) || !header.startsWith("Bearer ")){
            return null;
        }
        return header.split(" ")[1];
    }
    public Date extractExpiration(String jwt){
        return this.extractAllClaims(jwt).getExpiration();
    }
}
