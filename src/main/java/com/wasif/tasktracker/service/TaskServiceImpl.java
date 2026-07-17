package com.wasif.tasktracker.service;

import com.wasif.tasktracker.exception.TaskNotFoundException;
import com.wasif.tasktracker.model.Task;
import com.wasif.tasktracker.model.TaskStatus;
import com.wasif.tasktracker.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task addTask(String description) {
        int maxId = 0;
        for(Task task : taskRepository.findAll())
        {
            if(task.getId()>maxId)
            {
                maxId = task.getId();
            }
        }
        int newId = maxId + 1;

        Task newTask = new Task();
        newTask.setId(newId);
        newTask.setDescription(description);
        newTask.setStatus(TaskStatus.TODO);
        newTask.setCreatedAt(LocalDateTime.now());
        newTask.setUpdatedAt(LocalDateTime.now());

        return taskRepository.save(newTask);
    }

    @Override
    public Task updateTask(int id, String description) {
        Optional<Task> maybeTask = taskRepository.findById(id);
        if(maybeTask.isEmpty())
        {
            throw new TaskNotFoundException("Task with id " + id + " not found");
        }
        Task task = maybeTask.get();
        task.setDescription(description);
        task.setUpdatedAt(LocalDateTime.now());

        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(int id) {
        Optional<Task> maybeTask = taskRepository.findById(id);
        if(maybeTask.isEmpty())
        {
            throw new TaskNotFoundException("Task with id " + id + " not found");
        }
        taskRepository.deleteById(id);

        
    }

    @Override
    public void markInProgress(int id) {
        Optional<Task> maybeTask = taskRepository.findById(id);
        if(maybeTask.isEmpty())
        {
            throw new TaskNotFoundException("Task with id " + id + " not found");
        }
        Task task =  maybeTask.get();
        task.setStatus(TaskStatus.IN_PROGRESS);
        task.setUpdatedAt(LocalDateTime.now());
        taskRepository.save(task);

    }

    @Override
    public void markDone(int id) {

        Optional<Task> maybeTask = taskRepository.findById(id);
        if(maybeTask.isEmpty()){
            throw new TaskNotFoundException("Task with id " + id + " not found");
        }
        Task task = maybeTask.get();
        task.setStatus(TaskStatus.DONE);
        task.setUpdatedAt(LocalDateTime.now());

        taskRepository.save(task);
    }

    @Override
    public List<Task> listTasks() {
        return taskRepository.findAll();

    }

    @Override
    public List<Task> listTasksByStatus(TaskStatus status) {
        List<Task> tasksByStatus = new ArrayList<>();
        for(Task task: taskRepository.findAll())
        {
            if(task.getStatus() == status ){
                tasksByStatus.add(task);
            }
        }
        return tasksByStatus;
    }
}
