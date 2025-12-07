package com.cs320;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskServiceTest {

    @Test
    public void testAddNullTaskFails() {
        TaskService s = new TaskService();
        assertThrows(IllegalArgumentException.class, () -> s.addTask(null));
    }

    private TaskService service;

    @BeforeEach
    public void setup() {
        service = new TaskService();
    }

    @Test
    public void testAddTask() {
        Task task = new Task("001", "Task1", "Test task");
        service.addTask(task);
        assertEquals(task, service.getTask("001"));
    }

    @Test
    public void testAddDuplicateTaskId() {
        Task task1 = new Task("001", "Task1", "Test task");
        Task task2 = new Task("001", "Task2", "Another task");
        service.addTask(task1);
        assertThrows(IllegalArgumentException.class, () -> service.addTask(task2));
    }

    @Test
    public void testDeleteTask() {
        Task task = new Task("001", "Task1", "Test task");
        service.addTask(task);
        service.deleteTask("001");
        assertNull(service.getTask("001"));
    }

    @Test
    public void testDeleteNonexistentTask() {
        assertThrows(IllegalArgumentException.class, () -> service.deleteTask("999"));
    }

    @Test
    public void testUpdateTaskName() {
        Task task = new Task("001", "Task1", "Test task");
        service.addTask(task);
        service.updateTaskName("001", "Updated Name");
        assertEquals("Updated Name", service.getTask("001").getName());
    }

    @Test
    public void testUpdateTaskDescription() {
        Task task = new Task("001", "Task1", "Test task");
        service.addTask(task);
        service.updateTaskDescription("001", "Updated Description");
        assertEquals("Updated Description", service.getTask("001").getDescription());
    }

    @Test
    public void testUpdateInvalidTask() {
        assertThrows(IllegalArgumentException.class, () -> service.updateTaskName("404", "New Name"));
        assertThrows(IllegalArgumentException.class, () -> service.updateTaskDescription("404", "New Desc"));
    }
}
