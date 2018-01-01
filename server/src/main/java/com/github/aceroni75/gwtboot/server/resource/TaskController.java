package com.github.aceroni75.gwtboot.server.resource;

import com.github.aceroni75.gwtboot.server.entity.TaskRepository;
import com.github.aceroni75.gwtboot.shared.entity.Task;
import com.github.aceroni75.gwtboot.shared.resource.TaskResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TaskController implements TaskResource {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Iterable<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTask(Long id) {
        return taskRepository.findOne(id);
    }

    @Override
    public Task addTask(Task task) {
        return taskRepository.save(new Task(task.getTitle(), task.getText()));
    }

    @Override
    public Task updateTask(Long id, Task task) {
        Task existing = taskRepository.findOne(id);
        existing.setText(task.getText());
        existing.setTitle(task.getTitle());
        return taskRepository.save(existing);
    }

    @Override
    public Void deleteTask(Long id) {
        taskRepository.delete(id);
        return null;
    }

}
