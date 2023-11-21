package org.project.dao.implementation;

import org.project.dao.abstracts.UserDao;
import org.project.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/to-do-app";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "root";

    @Override
    public User findById(Long id) {
        String query = "SELECT * FROM app_user WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Long userId = resultSet.getLong("id");
                String userNickname = resultSet.getString("nickname");
                String userEmail = resultSet.getString("email");

                return new User(userId, userNickname, userEmail);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM app_user");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Long userId = rs.getLong("id");
                String userNickname = rs.getString("nickname");
                String userEmail = rs.getString("email");

                User user = new User(userId, userNickname, userEmail);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;
    }

    @Override
    public void save(User entity) {
        String query = "INSERT INTO app_user VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, entity.getId());
            statement.setString(2, entity.getNickname());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User entity) {
        String query = "UPDATE app_user SET nickname = ?, email = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getNickname());
            statement.setString(2, entity.getEmail());
            statement.setLong(3, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(User entity) {
        String query = "DELETE FROM app_user WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
