package com.wasif.tasktracker.repository;

import com.wasif.tasktracker.model.Task;
import java.util.Optional;
import java.util.List;

public interface TaskRepository {
    Optional<Task> findById(int id);
    List<Task> findAll();
    Task save(Task task);
    void deleteById(int id);
}
