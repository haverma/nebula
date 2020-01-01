package org.nebula.main;

import com.sleepycat.je.DatabaseException;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.nebula.config.Settings;
import org.nebula.database.BerkeleyDbWrapper;
import org.nebula.service.StorageService;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;

public class EntryPoint {

  private static Nebula nebula;

  public static Nebula getNebula() {
    return nebula;
  }

  private static void initNebula(Settings settings) throws DatabaseException {
    nebula = new Nebula(new BerkeleyDbWrapper(), settings);
  }

  private static void runNebulaServices() throws IOException {
    ResourceConfig rcWork = new ResourceConfig(StorageService.class);

    URI nebulaServiceUri =
        UriBuilder.fromUri("http://0.0.0.0")
            .port(nebula.getSettings().getStorageServicePort())
            .build();
    HttpServer server = GrizzlyHttpServerFactory.createHttpServer(nebulaServiceUri, rcWork);
    server.start();
  }

  public static void main(String[] args) {
    Settings settings = new Settings();
    settings.parse(args);
    try {
      initNebula(settings);
      runNebulaServices();
    } catch (Exception e) {
      // TODO: log warning
      e.printStackTrace();
    }
  }
}
