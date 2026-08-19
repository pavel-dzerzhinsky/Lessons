package ru.ab.edu.repository;

import ru.ab.edu.dto.CreateTaskDTO;
import ru.ab.edu.dto.TaskDTO;
import ru.ab.edu.dto.UpdateTaskDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    private static final String CREATE_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS task (
                id          SERIAL PRIMARY KEY,
                title       VARCHAR(255) NOT NULL,
                description TEXT,
                status      VARCHAR(50) NOT NULL
            )
            """;

    private static final String INSERT_SQL = """
            INSERT INTO task (title, description, status)
            VALUES (?, ?, ?)
            """;

    private static final String FIND_ALL_SQL = """
            SELECT id, title, description, status
            FROM task
            """;

    private static final String DELETE_BY_ID_SQL = """
            DELETE FROM task WHERE id = ?
            """;

    private static final String UPDATE_SQL = """
            UPDATE task
            SET title = ?, description = ?, status = ?
            WHERE id = ?
            """;

    private static final String SEARCH_BY_TITLE_OR_DESCRIPTION_SQL = """
            SELECT id, title, description, status
            FROM task
            WHERE title ILIKE ? OR description ILIKE ?
            """;

    private final String url;
    private final String username;
    private final String password;

    public TaskRepository() {
        this.url = "jdbc:postgresql://localhost:5432/data";
        this.username = "postgres";
        this.password = "data";
        createTableIfNotExists();
    }

    private void createTableIfNotExists() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(CREATE_TABLE_SQL);
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при создании таблицы: " + e.getMessage(), e);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public List<TaskDTO> findAll() {
        List<TaskDTO> tasks = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_ALL_SQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                tasks.add(new TaskDTO(
                        rs.getString("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("status")
                ));
            }
            return tasks;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при чтении задач: " + e.getMessage(), e);
        }
    }

    public List<TaskDTO> searchByTitleOrDescription(String searchString) {
        List<TaskDTO> tasks = new ArrayList<>();
        String searchPattern = "%" + searchString + "%";

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(SEARCH_BY_TITLE_OR_DESCRIPTION_SQL)) {

            ps.setString(1, searchPattern);
            ps.setString(2, searchPattern);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    tasks.add(new TaskDTO(
                            rs.getString("id"),
                            rs.getString("title"),
                            rs.getString("description"),
                            rs.getString("status")
                    ));
                }
            }
            return tasks;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при поиске задач: " + e.getMessage(), e);
        }
    }

    public void save(CreateTaskDTO createTaskDTO) {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_SQL)) {

            ps.setString(1, createTaskDTO.title());
            ps.setString(2, createTaskDTO.description());
            ps.setString(3, createTaskDTO.status());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при сохранении задачи: " + e.getMessage(), e);
        }
    }

    public void deleteById(String id) {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE_BY_ID_SQL)) {

            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при удалении задачи: " + e.getMessage(), e);
        }
    }

    public void update(UpdateTaskDTO task) {
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_SQL)) {

            ps.setString(1, task.title());
            ps.setString(2, task.description());
            ps.setString(3, task.status());
            ps.setString(4, task.id());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при обновлении задачи: " + e.getMessage(), e);
        }
    }
}