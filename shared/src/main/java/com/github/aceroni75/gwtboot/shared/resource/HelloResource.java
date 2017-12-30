package com.github.aceroni75.gwtboot.shared.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/hello")
@Produces("application/json")
public interface HelloResource {

    @GET
    String hello();
}
