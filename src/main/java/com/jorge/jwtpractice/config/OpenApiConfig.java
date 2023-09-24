package com.jorge.jwtpractice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.Servers;

@OpenAPIDefinition(
    info = @Info(
            contact = @Contact(
                    name = "Jorge",
                    email = "test@hotmail.com",
                    url = "https://github.com/JorgeEnriquez123"
            ),
            title = "JWT Test",
            description = "This ia test project about using JWT",
            version = "1.0",
            license = @License(
                    name = "License name",
                    url = "https://example.com"
            ),
            termsOfService = "Terms of service"
        ),
    servers = {
            @Server(
                    description = "Local ENV",
                    url = "http://localhost:8080"
            ),
            @Server(
                    description = "PROD ENV",
                    url = "https://realwebsite.com"
            )
    },
    security = {                        // * This is to set the same security for All endpoints
            @SecurityRequirement(       // ! It has to be the same name as @SecurityScheme
                    name = "bearerAuth"
            )
    }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",

        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
