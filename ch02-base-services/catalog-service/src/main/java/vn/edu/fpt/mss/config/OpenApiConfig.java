package vn.edu.fpt.mss.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI catalogServiceOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Catalog Service API")
                .description("RESTful API for the Chinook catalog: artists, albums, genres, media types and tracks.")
                .version("v1")
                .contact(new Contact().name("FPT University"))
                .license(new License().name("MIT")));
    }
}