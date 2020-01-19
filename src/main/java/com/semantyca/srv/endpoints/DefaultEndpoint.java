package com.semantyca.srv.endpoints;

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
import com.semantyca.transport.SeMessage;
import com.semantyca.transport.SePayload;
import com.semantyca.srv.persistence.GroupServiceJpaFactory;

@Path("/auth")
public class DefaultEndpoint {

    @Inject
    private SePayload sePayload;
    @Inject
    private SemantycaUser semantycaUser;

    @Inject
    private GroupServiceJpaFactory jpaFactory;

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
        EntityManager em = jpaFactory.getEntityManager();
        return Response.ok(em.createNamedQuery("SemantycaGroup.findAll", SemantycaGroup.class).getResultList()).build();
    }

    @POST
    @Path("/group/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addGroup(String groupName) {
        EntityManager em = jpaFactory.getEntityManager();
        SemantycaGroup se = new SemantycaGroup();
        se.setGroupName(groupName);
        em.persist(se);
        return Response.ok(se).build();
    }

}