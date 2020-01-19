package com.semantyca.srv.endpoints;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.semantyca.entity.SemantycaGroup;
import com.semantyca.entity.SemantycaUser;
import com.semantyca.srv.persistence.GroupService;
import com.semantyca.transport.SeMessage;
import com.semantyca.transport.SePayload;

@RequestScoped
@Path("/auth")
@Transactional
public class DefaultEndpoint {

    @Inject
    private SePayload sePayload;
    @Inject
    private SemantycaUser semantycaUser;

    @Inject
    private GroupService groupService;

    @GET
    @Path("/welcome")
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello() {
        SeMessage output = new SeMessage(sePayload, semantycaUser);
        return Response.ok(output).build();
    }

    @GET
    @Path("/groups")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listGroups() {
        return Response.ok(groupService.getAll()).build();
    }

    @POST
    @Path("/group/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addGroup(SemantycaGroup group) {
        return Response.ok(groupService.saveOrUpdate(group)).build();
    }

}