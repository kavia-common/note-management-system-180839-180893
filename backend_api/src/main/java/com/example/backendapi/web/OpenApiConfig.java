package com.example.backendapi.web;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI metadata for better documentation.
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Notes API",
                version = "1.0.0",
                description = "REST API for creating, retrieving, updating, and deleting notes."
        ),
        tags = {
                @Tag(name = "Notes", description = "CRUD operations for Notes"),
                @Tag(name = "Hello Controller", description = "Basic endpoints for backendapi")
        }
)
public class OpenApiConfig {
    // No code required; annotations drive OpenAPI metadata.
}
