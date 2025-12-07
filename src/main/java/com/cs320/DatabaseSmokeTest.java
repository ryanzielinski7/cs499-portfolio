package com.cs320;

public class DatabaseSmokeTest {

    public static void main(String[] args) {

        TaskService service = new TaskService();

        try {
            System.out.println("=== Smoke Test Start ===");

            // 1. Add a task
            Task t = new Task("abc", "Name", "Description");
            service.addTask(t);
            System.out.println("Added task 'abc'");

            // 2. Retrieve it
            Task fromDb = service.getTask("abc");
            System.out.println("Retrieved: " + fromDb.getTaskId() + ", " + fromDb.getName());

            // 3. Update it
            service.updateTask("abc", "New Name", "New Description");
            Task updated = service.getTask("abc");
            System.out.println("Updated: " + updated.getName());

            // 4. Delete it
            service.deleteTask("abc");
            System.out.println("Deleted task 'abc'");

            System.out.println("=== Smoke Test Completed Successfully ===");

        } catch (Exception e) {
            System.out.println("Smoke test failed:");
            e.printStackTrace();
        }
    }
}
