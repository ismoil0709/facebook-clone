package uz.pdp.facebookapp.config.openApi;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI openAPI() {
        final String securitySchemeName = "Bearer Authentication";
        return new OpenAPI()
                .info(new Info()
                        .title("Spring Boot Security with OpenAPI")
                        .description("The project is aimed to upgrade knowledge on spring boot web security.")
                        .version("1.0.0")
                        .termsOfService("https://swagger.io")
                        .contact(new Contact()
                                .name("Ismoil")
                                .email("abduganiyev.ismoil001@gmail.com")
                                .url("https://t.me/ismoil_0709")
                        )
                ).servers(List.of(
                        new Server().url("http://localhost:8080").description("Local Server"),
                        new Server().url("http://localhost:9090").description("Staging Server")
                )).addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName)
                ).components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name   (securitySchemeName)
                                .bearerFormat("JWT")
                                .scheme("bearer")
                                .type(SecurityScheme.Type.HTTP)
                        )
                );
    }
}
