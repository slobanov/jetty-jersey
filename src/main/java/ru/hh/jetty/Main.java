package ru.hh.jetty;

import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletContainer;

public class Main {

  public static void main(String[] args) throws Exception {
    var server = new Server(8080);

    var servletContextHandler = new ServletContextHandler("/my-app");

    var servletHolder = servletContextHandler.addServlet(new ServletContainer(), "/*");
    servletHolder.setInitParameter(ServerProperties.PROVIDER_PACKAGES, Main.class.getPackageName());
    servletHolder.setInitParameter(ServerProperties.WADL_FEATURE_DISABLE, "true");

/*
    servletContextHandler.addServlet(new MyServlet(), "/status");
    servletContextHandler.addFilter(new FirstFilter(), "*", EnumSet.of(DispatcherType.REQUEST));
    servletContextHandler.addFilter(new SecondFilter(), "*", EnumSet.of(DispatcherType.REQUEST));
*/

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
