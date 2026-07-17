package com.wasif.tasktracker.service;

import com.wasif.tasktracker.model.Task;
import com.wasif.tasktracker.model.TaskStatus;
import java.util.List;

public interface TaskService {
    Task addTask(String description);
    Task updateTask(int id, String description);
    void deleteTask(int id);
    void markInProgress(int id);
    void markDone(int id);
    List<Task> listTasks();
    List<Task> listTasksByStatus(TaskStatus status);


    
}
