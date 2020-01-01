package org.nebula.node.service;

import org.nebula.node.config.Constants;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/** APIs exposed by the database nebula node */
@Path(Constants.PATH_NEBULA_NODE)
@Produces(MediaType.APPLICATION_JSON)
public class NodeService {

  /** Handles the health check request */
  @GET
  @Path(Constants.PATH_HEALTH_CHECK)
  public Response healthCheck() {
    return Response.ok(System.currentTimeMillis()).build();
  }
}
