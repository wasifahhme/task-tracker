package com.wasif.tasktracker.repository;

import java.io.File;
import java.io.IOException;
//import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import com.wasif.tasktracker.exception.TaskNotFoundException;
import com.wasif.tasktracker.model.Task;


@Repository
public class JsonFileTaskRepository implements TaskRepository {

    private final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    private final File file = new File("tasks.json");

    


    @Override
    public Optional<Task> findById(int id) {
        for(Task task: findAll())
        {
           if(task.getId() == id) return Optional.of(task);

        }
        return Optional.empty();       
    }

    @Override
    public List<Task> findAll() {

        if(!file.exists()) return new ArrayList<>();

        try {
            return mapper.readValue(file, new TypeReference<List<Task>>() {});
        } catch (IOException e)
        {
            throw new RuntimeException("Failed to read tasks file", e);
        }

    }

    @Override
    public Task save(Task task) {
        List<Task> tasks = findAll();
        boolean found = false;
        for(int i = 0; i< tasks.size(); i++)
        {
            if(tasks.get(i).getId() == task.getId()){
                tasks.set(i, task);
                found = true;
            }
        }
        if(!found) tasks.add(task);

        try {
            mapper.writeValue(file, tasks);
            return task;
        } catch (IOException e) {
            throw new RuntimeException("Failed to write tasks file", e);
        }

    }

    @Override
    public void deleteById(int id) {
        List<Task> tasks = findAll();
        tasks.removeIf(t -> t.getId()==id);
        try {
            mapper.writeValue(file, tasks);
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete task", e);
        }
    }


}
