package com.semantyca.controller;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.semantyca.entity.UiPage;
import com.semantyca.entity.transport.Message;
import com.semantyca.entity.transport.Payload;

@Path("/")
public class WelcomePageController {

    @Inject
    private Payload payload;

    @GET
    @Path("/welcome")
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello() {
        Message output = new Message(payload);
        return Response.ok(output).build();
    }

}