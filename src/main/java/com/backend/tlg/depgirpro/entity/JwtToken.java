package com.backend.tlg.depgirpro.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name="jwt_tokens")
public class JwtToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2048)
    private String token;

    private Date expiracion;

    private boolean isValid;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="persona_id")
    private Persona persona;


    public JwtToken(String token, Date expiracion, Persona persona) {
        this.token = token;
        this.expiracion = expiracion;
        this.isValid=true;
        this.persona = persona;
    }
}
