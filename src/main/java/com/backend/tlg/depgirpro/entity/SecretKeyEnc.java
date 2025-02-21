package com.backend.tlg.depgirpro.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name="secret_keys")
public class SecretKeyEnc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(length = 4096)
    private String secretKey;


    private boolean isValid;

    @CreationTimestamp
    private LocalDateTime fechaCreacion;

    public SecretKeyEnc(String secretKey, boolean isValid) {
        this.secretKey = secretKey;
        this.isValid = isValid;
    }
}
