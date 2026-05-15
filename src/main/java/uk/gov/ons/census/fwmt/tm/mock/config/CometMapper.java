package uk.gov.ons.census.fwmt.tm.mock.config;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.impl.generator.JavassistCompilerStrategy;
import org.springframework.stereotype.Component;
import uk.gov.ons.census.fwmt.common.data.tm.Case;
import uk.gov.ons.census.fwmt.common.data.tm.CaseRequest;

@Component
public class CometMapper extends ConfigurableMapper {

  @Override
  public void configureFactoryBuilder(final DefaultMapperFactory.Builder builder) {
    builder.compilerStrategy(new JavassistCompilerStrategy());
  }

  @Override
  protected final void configure(final MapperFactory factory) {
    factory.classMap(CaseRequest.class, Case.class).byDefault().register();
  }
}
