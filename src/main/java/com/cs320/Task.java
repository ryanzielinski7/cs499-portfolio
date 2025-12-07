package com.cs320;

public class Task {

    private final String taskId;
    private String name;
    private String description;

    public Task(String taskId, String name, String description) {
        if (taskId == null || taskId.trim().isEmpty() || taskId.length() > 10) {
            throw new IllegalArgumentException("Task ID must be non-null, non-empty, and at most 10 characters.");
        }
        if (name == null || name.trim().isEmpty() || name.length() > 20) {
            throw new IllegalArgumentException("Task name must be non-null, non-empty, and at most 20 characters.");
        }
        if (description == null || description.trim().isEmpty() || description.length() > 50) {
            throw new IllegalArgumentException("Task description must be non-null, non-empty, and at most 50 characters.");
        }

        this.taskId = taskId;
        this.name = name;
        this.description = description;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty() || name.length() > 20) {
            throw new IllegalArgumentException("Task name must be non-null, non-empty, and at most 20 characters.");
        }
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty() || description.length() > 50) {
            throw new IllegalArgumentException("Task description must be non-null, non-empty, and at most 50 characters.");
        }
        this.description = description;
    }
}
