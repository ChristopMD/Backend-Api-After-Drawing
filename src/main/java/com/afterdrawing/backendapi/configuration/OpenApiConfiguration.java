package com.afterdrawing.backendapi.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration  {

    @Bean(name = "afterdrawingOpenApi")
    public OpenAPI afterdrawingOpenApi() {
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("Authorization", new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("Bearer").bearerFormat("JWT")))
                .info(new Info()
                        .title("AfterDrawing  Application API")
                        .description(
                                "AfterDrawing API implemented with Spring Boot RESTFUL service and documented using springdoc-openapi and OpenAPI 3.0.1"));
    }
}
