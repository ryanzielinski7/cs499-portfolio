package com.cs320;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void testValidTaskCreation() {
        Task task = new Task("123", "Name", "Description");
        assertEquals("123", task.getTaskId());
        assertEquals("Name", task.getName());
        assertEquals("Description", task.getDescription());
    }

    @Test
    public void testInvalidTaskId() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task(null, "Name", "Description");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("12345678901", "Name", "Description");
        });
    }

    @Test
    public void testInvalidName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("123", null, "Description");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("123", "This name is way too long to be valid", "Description");
        });
    }

    @Test
    public void testInvalidDescription() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("123", "Name", null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("123", "Name", "A".repeat(51));
        });
    }

    @Test
    public void testSetters() {
        Task task = new Task("123", "Name", "Description");
        task.setName("New Name");
        task.setDescription("New Description");

        assertEquals("New Name", task.getName());
        assertEquals("New Description", task.getDescription());
    }

    @Test
    public void testSettersInvalid() {
        Task task = new Task("123", "Name", "Description");
        assertThrows(IllegalArgumentException.class, () -> task.setName(null));
        assertThrows(IllegalArgumentException.class, () -> task.setName("A".repeat(21)));
        assertThrows(IllegalArgumentException.class, () -> task.setDescription(null));
        assertThrows(IllegalArgumentException.class, () -> task.setDescription("A".repeat(51)));
    }
}
