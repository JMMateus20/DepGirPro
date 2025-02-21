package com.backend.tlg.depgirpro.config.security.authorization;

import com.backend.tlg.depgirpro.entity.Operacion;
import com.backend.tlg.depgirpro.entity.Permiso;
import com.backend.tlg.depgirpro.entity.Persona;
import com.backend.tlg.depgirpro.exceptions.NotFoundExceptionManaged;
import com.backend.tlg.depgirpro.repository.OperacionRepository;
import com.backend.tlg.depgirpro.repository.PersonaRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CustomAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    @Autowired
    private OperacionRepository opRep;

    @Autowired
    private PersonaRepository personaRep;

    private static final Logger logger= LoggerFactory.getLogger(CustomAuthorizationManager.class);

    @Override
    public void verify(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        AuthorizationManager.super.verify(authentication, object);
    }

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        HttpServletRequest request=object.getRequest();
        String url=extractUrl(request);
        String httpMethod= request.getMethod();
        System.out.println("metodo http de la petición actual: " + httpMethod);
        boolean isPublic=isPublic(url, httpMethod);
        logger.info("Se verifica si la petición actual es pública");
        if (isPublic){
            logger.info("La petición es pública, entonces autoriza acceso");
            return new AuthorizationDecision(true);
        }
        boolean isGranted=isGranted(url, httpMethod, authentication.get());

        return new AuthorizationDecision(isGranted);
    }

    private String extractUrl(HttpServletRequest request){
        String contextPath= request.getContextPath();
        String url=request.getRequestURI();
        url=url.replace(contextPath, "");
        logger.info("se extrajo la URL de la petición: {}", url);
        return url;
    }

    private static Predicate<Operacion> getOperacionPredicate(String url, String httpMethod){
        logger.info("Se ingresó al método predicate que busca la peticion actual en la base de datos");
        logger.info("Se comienza a recorrer cada operacion guardada en la base de datos");
        return endpoint->{
            String basePath=endpoint.getModulo().getBase_path();
            logger.info("de la operacion actual, se extrae el basePath, esdecir, el del módulo al cual pertenece: {}", basePath);
            Pattern pattern=Pattern.compile(basePath.concat(endpoint.getPath()));
            logger.info("Se concatena su basePath con su path para formar la url completa: {}", pattern);
            Matcher matcher=pattern.matcher(url);
            logger.info("Se procede a verificar si la operacion actual de la bd es la misma que la que se envia en la petición y su metodo http es el mismo, si es así, el predicate devuelve true");
            return matcher.matches() && endpoint.getMetodo_http().equals(httpMethod);
        };
    }


    private boolean isPublic(String url, String httpMethod){
        List<Operacion> operacionesPublicas=this.opRep.encontrarOperacionesPublicas();
        logger.info("Se verifica si la petición actual enviada por el cliente es publica comparandola con las operaciones publicas guardadas en la base de datos.");
        boolean isPublic=operacionesPublicas.stream().anyMatch(getOperacionPredicate(url, httpMethod));
        logger.info("La operacion es publica: {}", isPublic);
        return isPublic;
    }


    private List<Operacion> operacionesDeUsuarioActual(Authentication auth){
        UsernamePasswordAuthenticationToken authToken=(UsernamePasswordAuthenticationToken) auth;
        String correo=(String) authToken.getPrincipal();
        Persona personaBD=this.personaRep.findByCorreo(correo).orElseThrow(()-> new NotFoundExceptionManaged("404", "Error de búsqueda", "Persona no encontrada en la base de datos", HttpStatus.NOT_FOUND));
        List<Operacion> operaciones=new ArrayList<>();
        personaBD.getRole().getPermisos().forEach(permiso->operaciones.add(permiso.getOperacion()));
        logger.info("Extrae los permisos o las operaciones que puede hacer el usuario segun su rol");
        operaciones.forEach(op->System.out.println(op.getNombre()));
        return operaciones;
    }

    private boolean isGranted(String url, String httpMethod, Authentication auth){
        if (auth==null || !(auth instanceof UsernamePasswordAuthenticationToken)){
            throw new AuthenticationCredentialsNotFoundException("Usuario no logeado");
        }
        List<Operacion> operaciones=operacionesDeUsuarioActual(auth);
        boolean estaAutorizado=operaciones.stream().anyMatch(getOperacionPredicate(url, httpMethod));
        logger.info("Se verifica si el usuario está intentando acceder a una operacion a la cual si tiene permisos, se verifica usando el predicate si la url de la peticion actual coincide con la de alguna operacion de las que le permite su rol");
        logger.info("esta autorizado: {}", estaAutorizado);
        return estaAutorizado;
    }
}
