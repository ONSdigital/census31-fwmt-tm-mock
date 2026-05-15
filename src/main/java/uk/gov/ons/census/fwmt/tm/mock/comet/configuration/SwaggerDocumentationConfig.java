package uk.gov.ons.census.fwmt.tm.mock.comet.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerDocumentationConfig {

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("Mock Totalmobile Comet")
        .description("Mock Totalmobile Comet for use by the FWMTG")
        .version("v2")
        .build();
  }

  @Bean
  public Docket customImplementation() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("uk.gov.ons.census.fwmt"))
        .build()
        .directModelSubstitute(org.threeten.bp.LocalDate.class, java.sql.Date.class)
        .directModelSubstitute(org.threeten.bp.OffsetDateTime.class, java.util.Date.class)
        .apiInfo(apiInfo());
  }

}
