package org.project.dao.implementation;

import org.project.dao.abstracts.CategoryDao;
import org.project.entity.Category;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/to-do-app";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "root";

    @Override
    public Category findById(Long id) {
        String query = "SELECT * FROM category WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Long categoryId = resultSet.getLong("id");
                String categoryDescription = resultSet.getString("description");

                return new Category(categoryId, categoryDescription);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM category");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Long categoryId = rs.getLong("id");
                String categoryDescription = rs.getString("description");

                Category category = new Category(categoryId, categoryDescription);
                categories.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return categories;
    }

    @Override
    public void save(Category entity) {
        String query = "INSERT INTO category (id, description) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, entity.getId());
            statement.setString(2, entity.getDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Category entity) {
        String query = "UPDATE category SET description = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getDescription());
            statement.setLong(2, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Category entity) {
        String query = "DELETE FROM category WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement deleteStatement = connection.prepareStatement(query)) {

            if (entity.getTaskList() != null) {
                String deleteTasks = "DELETE FROM task_category WHERE category_id = ?";
                PreparedStatement deleteTasksStatement = connection.prepareStatement(deleteTasks);
                deleteTasksStatement.setLong(1, entity.getId());
                deleteTasksStatement.executeUpdate();
            }

            deleteStatement.setLong(1, entity.getId());
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
