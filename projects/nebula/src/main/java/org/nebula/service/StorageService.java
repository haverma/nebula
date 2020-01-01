package org.nebula.service;

import org.nebula.config.Constants;
import org.nebula.database.BerkeleyDbWrapper;
import org.nebula.datamodel.KeyValue;
import org.nebula.main.EntryPoint;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/** Client facing APIs to store and manage key-value pairs */
@Path(Constants.PATH_NEBULA)
@Produces(MediaType.APPLICATION_JSON)
public class StorageService {

  private BerkeleyDbWrapper database = EntryPoint.getNebula().getDbWrapper();

  /** Returns values for the supplied key */
  @GET
  @Path(Constants.PATH_GET + "/{key}")
  public Response processGet(@PathParam("key") String key) {
    KeyValue value = database.get(key);
    // TODO: return KeyValue object instead of just the key
    if (value == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok(value).build();
  }

  /** Delete the key-value pair associated with the given key. */
  @DELETE
  @Path(Constants.PATH_DELETE + "/{key}")
  public Response deleteKey(@PathParam("key") String key) {
    boolean retValue = database.delete(key);
    if (!retValue) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok("deleted").build();
  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Path(Constants.PATH_PUT)
  public Response put(KeyValue keyValue) {
    boolean retVal = database.put(keyValue.getKey(), keyValue.getValue());
    if (retVal) {
      return Response.status(Response.Status.OK).build();
    } else {
      return Response.status(Response.Status.NOT_MODIFIED).build();
    }
  }
}
