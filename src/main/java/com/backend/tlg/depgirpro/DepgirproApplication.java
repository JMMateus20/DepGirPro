package com.backend.tlg.depgirpro;

import com.backend.tlg.depgirpro.entity.CryptoKey;
import com.backend.tlg.depgirpro.entity.SecretKeyEnc;
import com.backend.tlg.depgirpro.repository.CryptoKeyRepository;
import com.backend.tlg.depgirpro.repository.SecretKeyRepository;
import com.backend.tlg.depgirpro.services.auth.CryptoKeyGenerator;
import com.backend.tlg.depgirpro.services.auth.CryptoService;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class DepgirproApplication{





	public static void main(String[] args) {
		SpringApplication.run(DepgirproApplication.class, args);
	}


	@Bean
	public OpenAPI customerOpenAPI(){
		return new OpenAPI()
				.components(new Components())
				.info(new Info().title("DepGirPro APP")
						.version("1.0"))
				.components(new Components()
						.addSecuritySchemes("bearerAuth",
								new SecurityScheme()
										.type(SecurityScheme.Type.HTTP)
										.scheme("bearer")
										.bearerFormat("JWT")))
				.addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
	}



}
