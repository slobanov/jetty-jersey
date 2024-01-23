package ru.hh.jetty;

import jakarta.servlet.DispatcherType;
import java.util.EnumSet;
import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.server.Server;

public class Main {

  public static void main(String[] args) throws Exception {
    var server = new Server(8080);

    var servletContextHandler = new ServletContextHandler("/my-app");
    servletContextHandler.addServlet(new MyServlet(), "/status");
    servletContextHandler.addFilter(new FirstFilter(), "*", EnumSet.of(DispatcherType.REQUEST));
    servletContextHandler.addFilter(new SecondFilter(), "*", EnumSet.of(DispatcherType.REQUEST));

    server.setHandler(servletContextHandler);
/*
    server.setHandler(new MyHandler());
*/

    try {
      server.start();
      server.join();
    } finally {
      server.destroy();
    }
  }

}
