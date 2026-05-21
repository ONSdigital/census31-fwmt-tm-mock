package uk.gov.ons.census.fwmt.tm.mock.comet.api;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import lombok.extern.slf4j.Slf4j;
import uk.gov.ons.census.fwmt.tm.mock.config.LatencyBean;

@Slf4j
public class AddLatencyFilter implements jakarta.servlet.Filter {

  private LatencyBean latencyBean;

  public AddLatencyFilter(LatencyBean latencyBean) {
    this.latencyBean = latencyBean;
  }
  
  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    try {
      log.debug("Latency:" + latencyBean.getLatency());
      Thread.sleep(latencyBean.getLatency());
    } catch (InterruptedException e) {
      log.error("Could not add latency", e);
    }finally {
      chain.doFilter(request, response);
    }
  }
    
  @Override
  public void destroy() {
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }
}
