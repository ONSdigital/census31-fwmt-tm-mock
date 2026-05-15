package uk.gov.ons.census.fwmt.tm.mock.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import uk.gov.ons.census.fwmt.tm.mock.comet.api.AddLatencyFilter;

@Configuration
public class LatencyConfig {
  
  @Value(value="${latency.ms:0}")
  private Integer latency;
  
  @Bean
  public LatencyBean makeLatencyBean() {
    LatencyBean lb = new LatencyBean();
    lb.setLatency(latency);
    return lb;
  }
  
  @Bean
  public FilterRegistrationBean<AddLatencyFilter> loggingFilter(LatencyBean lb){
      FilterRegistrationBean<AddLatencyFilter> registrationBean 
        = new FilterRegistrationBean<>();
           
      registrationBean.setFilter(new AddLatencyFilter(lb));
      registrationBean.addUrlPatterns("/cases/*");  
      return registrationBean;    
  }
}
