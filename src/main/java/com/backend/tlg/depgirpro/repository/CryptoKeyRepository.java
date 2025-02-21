package com.backend.tlg.depgirpro.repository;

import com.backend.tlg.depgirpro.entity.CryptoKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CryptoKeyRepository extends JpaRepository<CryptoKey, Long> {

    @Query("SELECT C FROM CryptoKey AS C ORDER BY C.id DESC LIMIT 1")
    CryptoKey encontrarUltimo();
}
