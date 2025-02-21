package com.backend.tlg.depgirpro.repository;

import com.backend.tlg.depgirpro.entity.SecretKeyEnc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SecretKeyRepository extends JpaRepository<SecretKeyEnc, Long> {

    @Query("SELECT sk FROM SecretKeyEnc sk ORDER BY sk.id DESC LIMIT 1")
    SecretKeyEnc encontrarUltimo();
}
