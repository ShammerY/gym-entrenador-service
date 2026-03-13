package com.gym.trainer.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configuración de OpenAPI/Swagger para documentación de la API.
 *
 * Acceder a la documentación:
 * - Swagger UI: http://localhost:8085/swagger-ui.html
 * - OpenAPI JSON: http://localhost:8085/api-docs
 */
@Configuration
public class OpenApiConfig {

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuerUri;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Gym Trainer Service API")
                .version("1.0.0")
                .description("""
                    API REST para la gestión de entrenadores del gimnasio.

                    ## Funcionalidades
                    - CRUD completo de entrenadores
                    - Consulta por ID

                    ## Autenticación
                    Esta API utiliza OAuth2/JWT con Keycloak.
                    Para obtener un token, autentícate en Keycloak con las credenciales proporcionadas.

                    ## Roles
                    - **ADMIN**: Acceso completo (CRUD)
                    - **TRAINER**: Puede consultar y actualizar su información
                    - **MEMBER**: Puede consultar entrenadores
                    """)
                .contact(new Contact()
                    .name("Equipo Gimnasio")
                    .email("soporte@gimnasio.com"))
                .license(new License()
                    .name("MIT License")
                    .url("https://opensource.org/licenses/MIT")))
            .servers(List.of(
                new Server()
                    .url("http://localhost:8085")
                    .description("Servidor de desarrollo")))
            .addSecurityItem(new SecurityRequirement().addList("bearer-jwt"))
            .components(new Components()
                .addSecuritySchemes("bearer-jwt", new SecurityScheme()
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")
                    .description("Token JWT obtenido de Keycloak"))
                .addSecuritySchemes("oauth2", new SecurityScheme()
                    .type(SecurityScheme.Type.OAUTH2)
                    .flows(new OAuthFlows()
                        .password(new OAuthFlow()
                            .tokenUrl(issuerUri + "/protocol/openid-connect/token")))));
    }
}
