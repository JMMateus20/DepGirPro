package com.backend.tlg.depgirpro.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name="crypto_keys")
public class CryptoKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2048)
    private String cryptoKey;

    private boolean isValid;

    @CreationTimestamp
    private LocalDateTime fechaCreacion;


    public CryptoKey(String cryptoKey, boolean isValid) {
        this.cryptoKey = cryptoKey;
        this.isValid = isValid;
    }
}
