package com.backend.tlg.depgirpro.services;

import com.backend.tlg.depgirpro.entity.Persona;

public interface MailService {


    void sendVerificationEmail(Persona persona, String token);
}
