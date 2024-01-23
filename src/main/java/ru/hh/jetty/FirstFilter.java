package ru.hh.jetty;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FirstFilter extends HttpFilter {

  private static final Logger log = LoggerFactory.getLogger(FirstFilter.class);

  @Override
  protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
    log.info("In FirstFilter");
    chain.doFilter(req, res);
  }
}
