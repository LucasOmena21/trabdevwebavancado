package org.example.sbccmsapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI openAPI() {

    var info = new Info()
        .title("SBC CMS API")
        .description("API para o sistema SBC CMS")
        .version("v0.0.1");

    return new OpenAPI().info(info);
  }
}
