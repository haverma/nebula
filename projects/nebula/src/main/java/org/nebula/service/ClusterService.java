package org.nebula.service;

import nebula.datamodel.NebulaNodeMetadata;
import org.nebula.config.Constants;
import org.nebula.datamodel.KeyValue;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Service APIs to communicate with cluster nodes
 */
@Path(Constants.PATH_NEBULA)
@Produces(MediaType.APPLICATION_JSON)
public class ClusterService {
    private static List<NebulaNodeMetadata> clusterNodesMetadata = new ArrayList<>();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path(Constants.PATH_PUT)
    public Response joinCluster(NebulaNodeMetadata nebulaNodeMetadata) {
        boolean retVal = database.put(keyValue.getKey(), keyValue.getValue());
        if (retVal) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_MODIFIED).build();
        }
    }


}
