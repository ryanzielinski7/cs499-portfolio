package com.cs320;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDatabase {

    // SQLite DB file in the project directory
    private static final String DB_URL = "jdbc:sqlite:tasks.db";

    public TaskDatabase() {
        initializeDatabase();
    }

    private void initializeDatabase() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            String createTableSql =
                    "CREATE TABLE IF NOT EXISTS tasks (" +
                            "task_id TEXT PRIMARY KEY," +
                            "name TEXT NOT NULL," +
                            "description TEXT NOT NULL" +
                    ")";

            statement.execute(createTableSql);

        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize tasks database.", e);
        }
    }

    private Connection getConnection() throws SQLException {
        // Requires SQLite JDBC driver on the classpath (e.g., org.xerial:sqlite-jdbc)
        return DriverManager.getConnection(DB_URL);
    }

    public boolean taskExists(String taskId) {
        String sql = "SELECT 1 FROM tasks WHERE task_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, taskId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to check if task exists: " + taskId, e);
        }
    }

    public void insertTask(Task task) {
        String sql = "INSERT INTO tasks (task_id, name, description) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, task.getTaskId());
            ps.setString(2, task.getName());
            ps.setString(3, task.getDescription());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to insert task with ID: " + task.getTaskId(), e);
        }
    }

    public void deleteTask(String taskId) {
        String sql = "DELETE FROM tasks WHERE task_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, taskId);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete task with ID: " + taskId, e);
        }
    }

    public void updateTask(Task task) {
        String sql = "UPDATE tasks SET name = ?, description = ? WHERE task_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, task.getName());
            ps.setString(2, task.getDescription());
            ps.setString(3, task.getTaskId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to update task with ID: " + task.getTaskId(), e);
        }
    }

    public Task getTask(String taskId) {
        String sql = "SELECT task_id, name, description FROM tasks WHERE task_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, taskId);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }
                String id = rs.getString("task_id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                return new Task(id, name, description);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve task with ID: " + taskId, e);
        }
    }

    public List<Task> getAllTasks() {
        String sql = "SELECT task_id, name, description FROM tasks ORDER BY task_id";
        List<Task> tasks = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String id = rs.getString("task_id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                tasks.add(new Task(id, name, description));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve tasks.", e);
        }

        return tasks;
    }
}
