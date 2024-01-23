package ru.hh.jetty;

import java.nio.ByteBuffer;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.util.Callback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyHandler extends Handler.Abstract.NonBlocking {

  private static final Logger log = LoggerFactory.getLogger(MyHandler.class);

  @Override
  public boolean handle(Request request, Response response, Callback callback) throws Exception {
    var path = request.getHttpURI().getPath();
    if (path.contains("status")) {
      log.info("'status' in path");
    }

    var buffer = ByteBuffer.wrap("Hello from handler!\n".getBytes());
    response.write(true, buffer, callback);
    return true;
  }
}
