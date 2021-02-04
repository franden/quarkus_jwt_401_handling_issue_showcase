package org.acme.rest.boundary;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("api")
public class GreetingResource {

    @Path("secured")
    @RolesAllowed("admin")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }


    @Path("exception")
    @PermitAll
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String throwException() {
        throw new IllegalStateException("throwException was called");
    }
}