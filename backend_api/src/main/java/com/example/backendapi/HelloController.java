package com.example.backendapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Controller exposing basic health and documentation redirect endpoints.
 */
@RestController
@Tag(name = "Hello Controller", description = "Basic endpoints for backendapi")
public class HelloController {
    
    // PUBLIC_INTERFACE
    @GetMapping("/")
    @Operation(summary = "Welcome endpoint", description = "Returns a welcome message")
    public String hello() {
        /** Basic liveness response for root path. */
        return "Hello, Spring Boot! Welcome to backendapi";
    }
    
    // PUBLIC_INTERFACE
    @GetMapping("/docs")
    @Operation(
            summary = "API Documentation",
            description = "Redirects to Springdoc Swagger UI. Uses the canonical Springdoc UI path /swagger-ui/index.html and preserves the original scheme/host/port."
    )
    public RedirectView docs(HttpServletRequest request) {
        /**
         * Redirect to Swagger UI with correct external scheme/host/port when behind a proxy.
         * Springdoc 2.x serves the UI at /swagger-ui/index.html by default.
         */
        String target = UriComponentsBuilder
                .fromHttpRequest(new ServletServerHttpRequest(request))
                .replacePath("/swagger-ui/index.html")
                .replaceQuery(null)
                .build()
                .toUriString();

        RedirectView rv = new RedirectView(target);
        // Use HTTP 1.1 compatible redirects when necessary (preserves 303/307 semantics if used)
        rv.setHttp10Compatible(false);
        return rv;
    }
    
    // PUBLIC_INTERFACE
    @GetMapping("/health")
    @Operation(summary = "Health check", description = "Returns application health status as plain text 'OK'")
    public String health() {
        /** Simple health indicator for external probes. Prefer /actuator/health for detailed status. */
        return "OK";
    }
    
    // PUBLIC_INTERFACE
    @GetMapping("/api/info")
    @Operation(summary = "Application info", description = "Returns application information")
    public String info() {
        /** Application metadata endpoint. */
        return "Spring Boot Application: backendapi";
    }
}