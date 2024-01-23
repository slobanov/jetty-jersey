package ru.hh.jetty;

import jakarta.inject.Singleton;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.ext.ReaderInterceptor;
import jakarta.ws.rs.ext.ReaderInterceptorContext;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
@Singleton
public class RequestLoggingInterceptor implements ReaderInterceptor  {

  private static final Logger log = LoggerFactory.getLogger(RequestLoggingInterceptor.class);

  @Override
  public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
    var inputStream = context.getInputStream();
    var requestBody = new BufferedReader(new InputStreamReader(inputStream))
        .lines()
        .collect(Collectors.joining("\n"));
    log.info("request: " + requestBody);

    context.setInputStream(new ByteArrayInputStream(requestBody.getBytes()));
    return context.proceed();
  }
}
