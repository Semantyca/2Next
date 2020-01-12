package com.semantyca.controller;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.semantyca.service.Greeter;

@Path("/")
public class HelloRest {

    @Inject
    private Greeter greeter;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String hello() {
        return greeter.getGreeting();
    }

}