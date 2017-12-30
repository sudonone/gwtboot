package com.github.aceroni75.gwtboot.server.resource;

import com.github.aceroni75.gwtboot.server.entity.Task;
import com.github.aceroni75.gwtboot.server.entity.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;

@Component
@Path("/task")
@Consumes("application/json")
@Produces("application/json")
public class TaskResource {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskResource(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GET
    public Iterable<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @GET
    @Path("{id}")
    public Task getTask(@PathParam("id") Long id) {
        return taskRepository.findOne(id);
    }

    @PUT
    public Task addTask(Task task) {
        return taskRepository.save(new Task(task.getTitle(), task.getText()));
    }

    @PUT
    @Path("{id}")
    public Task updateTask(@PathParam("id") Long id, Task task) {
        Task existing = taskRepository.findOne(id);
        existing.setText(task.getText());
        existing.setTitle(task.getTitle());
        return taskRepository.save(existing);
    }

    @DELETE
    @Path("{id}")
    public void deleteTask(@PathParam("id") Long id) {
        taskRepository.delete(id);
    }

}
