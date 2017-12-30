package com.github.aceroni75.gwtboot.shared.resource;

import com.github.aceroni75.gwtboot.shared.entity.Task;

import javax.ws.rs.*;

@Path("/task")
@Consumes("application/json")
@Produces("application/json")
public interface TaskResource {

    @GET
    Iterable<Task> getAllTasks();

    @GET
    @Path("{id}")
    Task getTask(@PathParam("id") Long id);

    @PUT
    Task addTask(Task task);

    @PUT
    @Path("{id}")
    Task updateTask(@PathParam("id") Long id, Task task);

    @DELETE
    @Path("{id}")
    void deleteTask(@PathParam("id") Long id);
}
