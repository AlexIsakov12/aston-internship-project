package org.project.dao.implementation;

import org.project.dao.abstracts.MandatoryDao;
import org.project.entity.Mandatory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MandatoryDaoImpl implements MandatoryDao {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/to-do-app";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "root";

    @Override
    public Mandatory findById(Long id) {
        String query = "SELECT * FROM mandatory WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Long mandatoryId = resultSet.getLong("id");
                String mandatoryDescription = resultSet.getString("description");

                return new Mandatory(mandatoryId, mandatoryDescription);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Mandatory> findAll() {
        List<Mandatory> mandatoryList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM category");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Long mandatoryId = rs.getLong("id");
                String mandatoryDescription = rs.getString("description");

                Mandatory mandatory = new Mandatory(mandatoryId, mandatoryDescription);
                mandatoryList.add(mandatory);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return mandatoryList;
    }

    @Override
    public void save(Mandatory entity) {
        String query = "INSERT INTO mandatory VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, entity.getId());
            statement.setString(2, entity.getDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Mandatory entity) {
        String query = "UPDATE mandatory SET description = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getDescription());
            statement.setLong(2, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Mandatory entity) {
        String query = "DELETE FROM mandatory WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
