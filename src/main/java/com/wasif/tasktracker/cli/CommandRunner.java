package com.wasif.tasktracker.cli;

import com.wasif.tasktracker.service.TaskService;

import com.wasif.tasktracker.service.TaskServiceImpl;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.wasif.tasktracker.exception.InvalidCommandException;
import com.wasif.tasktracker.exception.TaskNotFoundException;
import com.wasif.tasktracker.model.Task;
import com.wasif.tasktracker.model.TaskStatus;

@Component
public class CommandRunner implements CommandLineRunner {

    private final TaskServiceImpl taskServiceImpl;
    private final TaskService taskService;
    private final CommandParser commandParser;

    public CommandRunner(TaskService taskService, CommandParser commandParser, TaskServiceImpl taskServiceImpl)
    {
        this.taskService = taskService;
        this.commandParser = commandParser;
        this.taskServiceImpl = taskServiceImpl;
    }

    @Override
    public void run(String... args) throws Exception{
        if(args.length == 0)
        {
            System.out.println("No command provided. Usage: <command> [arguments]");
            return;
        }
        String rawCommand = args[0];
        CommandType type = commandParser.parseCommandType(rawCommand);
        try {
        switch (type){
            case ADD:
                Task newTask = taskService.addTask(args[1]);
                System.out.println("Task added successfully (ID: " + newTask.getId() + ")");
                break;

            case UPDATE:
                int id = Integer.parseInt(args[1]);
                String newDescription = args[2];
                Task updatedTask = taskService.updateTask(id, newDescription);
                System.out.println("Task updated successfully (ID: " + updatedTask.getId() + ")" );
                break;

            case DELETE:
                int deleteId = Integer.parseInt(args[1]);
                taskService.deleteTask(deleteId); 
                System.out.println("Task deleted successfully (ID: " + deleteId + ")");
                break;

            case MARK_IN_PROGRESS:
                int progressId = Integer.parseInt(args[1]);
                taskService.markInProgress(progressId);
                System.out.println("Task marked as 'in progress' (ID: " + progressId + ")");
                break;
            
            case MARK_DONE:
                int completedId = Integer.parseInt(args[1]);
                taskService.markDone(completedId);
                System.out.println("Task marked as 'done' (ID: " + completedId + ")");
                break;

            case LIST:
                List<Task> listAllTasks;
                if(args.length<2)
                {
                    listAllTasks = taskService.listTasks();
                }
                else{
                    TaskStatus filterStatus;
                    try{
                        filterStatus = TaskStatus.valueOf(args[1].toUpperCase().replace("-", "_"));
                    } catch(IllegalArgumentException e)
                    {
                        System.out.println("Invalid status: " + args[1]);
                        return;
                    }
                    
                    listAllTasks = taskService.listTasksByStatus(filterStatus);
                }
                for(Task task: listAllTasks){
                    System.out.println(task.getId() + " [" + task.getStatus() + "] " + task.getDescription());
                }
                break;

            default:
                throw new InvalidCommandException("Unknown Command: " + rawCommand);
            }
            } catch (InvalidCommandException | TaskNotFoundException e)
            {
                System.out.println(e.getMessage());
            }

        }
        

    }
    

