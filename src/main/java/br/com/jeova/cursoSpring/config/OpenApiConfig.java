package br.com.jeova.cursoSpring.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI custonOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Cadastro usuario - Restfull API with Java 18 and spring boot ")
                        .version("v1")
                        .description("API para fins didatios e de aprendizado")
                        .termsOfService("Termos de serviços URL")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("Licence URL"))
                );
    }
}
