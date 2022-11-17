package com.xm.exercise.cryptorecommendationservice.application.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(new Info().title("Crypto Recommendation Service")
                        .description("Crypto Recommendation Service")
                        .version("v0.0.1"));
    }
}
