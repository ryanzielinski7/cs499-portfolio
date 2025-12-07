package com.cs320;

public class TaskService {

    private final TaskDatabase database;

    public TaskService() {
        this.database = new TaskDatabase();
    }

    // Constructor for injecting a mock or alternate database in tests, if needed
    public TaskService(TaskDatabase database) {
        if (database == null) {
            throw new IllegalArgumentException("TaskDatabase cannot be null.");
        }
        this.database = database;
    }

    public void addTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null.");
        }

        String taskId = task.getTaskId();
        if (database.taskExists(taskId)) {
            throw new IllegalArgumentException("Task ID already exists: " + taskId);
        }

        database.insertTask(task);
    }

    public void deleteTask(String taskId) {
        if (taskId == null || taskId.trim().isEmpty() || taskId.length() > 10) {
            throw new IllegalArgumentException("Task ID must be non-null, non-empty, and at most 10 characters.");
        }

        if (!database.taskExists(taskId)) {
            throw new IllegalArgumentException("Task ID does not exist: " + taskId);
        }

        database.deleteTask(taskId);
    }

    public void updateTask(String taskId, String name, String description) {
        if (taskId == null || taskId.trim().isEmpty() || taskId.length() > 10) {
            throw new IllegalArgumentException("Task ID must be non-null, non-empty, and at most 10 characters.");
        }

        Task existing = database.getTask(taskId);
        if (existing == null) {
            throw new IllegalArgumentException("Task ID does not exist: " + taskId);
        }

        // Reuse validation logic inside Task setters
        existing.setName(name);
        existing.setDescription(description);

        database.updateTask(existing);
    }

    public Task getTask(String taskId) {
        if (taskId == null || taskId.trim().isEmpty() || taskId.length() > 10) {
            throw new IllegalArgumentException("Task ID must be non-null, non-empty, and at most 10 characters.");
        }
        return database.getTask(taskId);
    }
}
