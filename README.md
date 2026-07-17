# Task Tracker CLI

This is a simple command-line interface (CLI) application for managing tasks. You can add, update, delete, mark, and list tasks directly from the terminal.

## Features

- **Add a Task:** Add a new task with a description.
- **Update a Task:** Update the description of an existing task.
- **Delete a Task:** Remove a task by its ID.
- **Mark a Task:** Mark a task as "in progress" or "done."
- **List Tasks:** List all tasks or filter them by status (e.g., `todo`, `in progress`, `done`).

## Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/wasifahhme/task-tracker.git
   cd task-tracker
   ```

2. **Build the project with Maven:**

   ```bash
   mvn clean install
   ```

3. **Run the application:**

   ```bash
   java -jar target/tasktracker-0.0.1-SNAPSHOT.jar <command> [arguments]
   ```

## Usage

```bash
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
# Output: Task marked as in progress (ID: 1)

# Marking a task as done
java -jar target/tasktracker-0.0.1-SNAPSHOT.jar mark-done 1
# Output: Task marked as done (ID: 1)

# Listing all tasks
java -jar target/tasktracker-0.0.1-SNAPSHOT.jar list
# Output: List of all tasks

# Listing tasks by status
java -jar target/tasktracker-0.0.1-SNAPSHOT.jar list todo
java -jar target/tasktracker-0.0.1-SNAPSHOT.jar list in-progress
java -jar target/tasktracker-0.0.1-SNAPSHOT.jar list done
```