Task Tracker CLI

This is a simple command line interface (CLI) application for managing tasks. You can add, update, delete, mark, and list tasks directly from the terminal. Tasks are saved to a JSON file, so they stay saved between runs.

Built with Java and Spring Boot, using Maven.

Features


Add a Task: Add a new task with a description.
Update a Task: Update the description of an existing task.
Delete a Task: Remove a task by its ID.
Mark a Task: Mark a task as "in progress" or "done".
List Tasks: List all tasks or filter them by status (e.g., todo, in-progress, done).


Installation


Clone the repository:


   git clone https://github.com/wasifahhme/task-tracker.git
   cd task-tracker


Build the project with Maven:


   mvn clean install


Run the application:


   java -jar target/tasktracker-0.0.1-SNAPSHOT.jar <command> [arguments]

Usage

# Adding a new task
java -jar target/tasktracker-0.0.1-SNAPSHOT.jar add "Buy groceries"
# Output: Task added successfully (ID: 1)

# Updating a task
java -jar target/tasktracker-0.0.1-SNAPSHOT.jar update 1 "Buy groceries and cook dinner"
# Output: Task updated successfully (ID: 1)

# Deleting a task
java -jar target/tasktracker-0.0.1-SNAPSHOT.jar delete 1
# Output: Task deleted successfully (ID: 1)

# Marking a task as in progress
java -jar target/tasktracker-0.0.1-SNAPSHOT.jar mark-in-progress 1
# Output: Task marked as 'in progress' (ID: 1)

# Marking a task as done
java -jar target/tasktracker-0.0.1-SNAPSHOT.jar mark-done 1
# Output: Task marked as 'done' (ID: 1)

# Listing all tasks
java -jar target/tasktracker-0.0.1-SNAPSHOT.jar list
# Output: list of all tasks

# Listing tasks by status
java -jar target/tasktracker-0.0.1-SNAPSHOT.jar list todo
java -jar target/tasktracker-0.0.1-SNAPSHOT.jar list in-progress
java -jar target/tasktracker-0.0.1-SNAPSHOT.jar list done

Project Structure

src/main/java/com/wasif/tasktracker/
├── TasktrackerApplication.java
├── model/
│   ├── Task.java
│   └── TaskStatus.java
├── repository/
│   ├── TaskRepository.java
│   └── JsonFileTaskRepository.java
├── service/
│   ├── TaskService.java
│   └── TaskServiceImpl.java
├── cli/
│   ├── CommandType.java
│   ├── CommandParser.java
│   └── CommandRunner.java
└── exception/
    ├── TaskNotFoundException.java
    └── InvalidCommandException.java

Notes


Tasks are stored in a file called tasks.json in the folder where you run the application. This file is created automatically the first time you add a task.
If you enter a command that does not exist, the application will show an error message instead of crashing.
If you try to update, delete, or mark a task that does not exist, the application will show a clear error message with the task ID.