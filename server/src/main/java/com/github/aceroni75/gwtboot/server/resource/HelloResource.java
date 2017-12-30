package com.github.aceroni75.gwtboot.server.resource;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Component
@Path("/hello")
@Produces("application/json")
public class HelloResource {

    @GET
    public String hello() {
        return "Hello world!";
    }
}
