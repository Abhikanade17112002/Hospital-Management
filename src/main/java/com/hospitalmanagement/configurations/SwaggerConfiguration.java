package com.hospitalmanagement.configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

@OpenAPIDefinition(

        info = @Info(
                title = "🏥 Hospital Management System APIs",
                version = "1.0.0",
                description = """
                        Enterprise-grade REST APIs for Hospital Management System.

                        Features:
                        - JWT Authentication
                        - OAuth2 Login
                        - Role Based Access Control
                        - Appointment Management
                        - Doctor Management
                        - Patient Management
                        - Department Management
                        - Insurance Management
                        """,

                contact = @Contact(
                        name = "Abhishek Kanade",
                        email = "abhishekrangnathkanade21@gmail.com"
                ),

                license = @License(
                        name = "Apache 2.0"
                )
        ),

        servers = {
                @Server(
                        description = "Local Development Server",
                        url = "http://localhost:8080"
                )
        },

        security = {
                @SecurityRequirement(name = "bearerAuth")
        },

        externalDocs = @ExternalDocumentation(
                description = "Project Documentation",
                url = "https://github.com/your-github-repo"
        ),

        tags = {

                @Tag(
                        name = "Authentication APIs",
                        description = "User Authentication APIs"
                ),

                @Tag(
                        name = "Patient APIs",
                        description = "Patient Management APIs"
                ),

                @Tag(
                        name = "Doctor APIs",
                        description = "Doctor Management APIs"
                ),

                @Tag(
                        name = "Appointment APIs",
                        description = "Appointment Management APIs"
                ),

                @Tag(
                        name = "Insurance APIs",
                        description = "Insurance Management APIs"
                ),

                @Tag(
                        name = "Admin APIs",
                        description = "Administrative APIs"
                )
        }
)

@SecurityScheme(
        name = "bearerAuth",
        description = "JWT Authentication Security Scheme",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT"
)

public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {

        final String securitySchemeName = "bearerAuth";

        return new OpenAPI()

                .addSecurityItem(
                        new io.swagger.v3.oas.models.security.SecurityRequirement()
                                .addList(securitySchemeName)
                )

                .components(
                        new Components()
                                .addSecuritySchemes(
                                        securitySchemeName,

                                        new io.swagger.v3.oas.models.security.SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                                .description("Paste JWT Token Here")
                                )
                );
    }
}