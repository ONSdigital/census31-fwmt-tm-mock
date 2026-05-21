package uk.gov.ons.census.fwmt.tm.mock.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("Mock Totalmobile Comet")
            .description("Mock Totalmobile Comet for use by the FWMTG")
            .version("v2"));
  }
}
