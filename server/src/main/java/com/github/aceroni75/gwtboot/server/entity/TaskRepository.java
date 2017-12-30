package com.github.aceroni75.gwtboot.server.entity;

import com.github.aceroni75.gwtboot.shared.entity.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
}
