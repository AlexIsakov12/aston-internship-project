package init;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseInitializer {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/to-do-app";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "root";

    public void init() {
        create();
        insert();
    }

    public static void create() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("""
                                         
                      CREATE TABLE category (
                         id BIGINT PRIMARY KEY,
                         description VARCHAR(255)
                      
                                        );
                                         
                     CREATE TABLE mandatory (
                         id BIGINT PRIMARY KEY,
                         description VARCHAR(255)
                      
                                        );
                                         
                     CREATE TABLE app_user (
                         id BIGINT PRIMARY KEY,
                         nickname VARCHAR(255),
                         email VARCHAR(255)
                      
                                        );
                                         
                     CREATE TABLE task (
                         id BIGINT PRIMARY KEY,
                         description VARCHAR(255),
                         user_id BIGINT,
                         mandatory_id BIGINT,
                         FOREIGN KEY (user_id) REFERENCES app_user(id),
                         FOREIGN KEY (mandatory_id) REFERENCES mandatory(id)
                      
                                        );
                                         
                     CREATE TABLE task_category (
                         task_id BIGINT,
                         category_id BIGINT,
                         FOREIGN KEY (task_id) REFERENCES task(id),
                         FOREIGN KEY (category_id) REFERENCES category(id),
                         PRIMARY KEY (task_id, category_id)
                     );
                     """
             )) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void drop() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("""
                    DROP TABLE IF EXISTS task_category CASCADE;
                    DROP TABLE IF EXISTS task CASCADE;
                    DROP TABLE IF EXISTS category CASCADE;
                    DROP TABLE IF EXISTS mandatory CASCADE;
                    DROP TABLE IF EXISTS app_user CASCADE;
                    DROP TABLE IF EXISTS user_task CASCADE;
                    """
        )) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void insert() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("""
                     INSERT INTO app_user (id, nickname, email)
                     VALUES (100, 'first_nick', 'first_email@gmail.com'),
                            (101, 'second_nick', 'second_email@gmail.com');
                            
                     INSERT INTO mandatory (id, description)
                     VALUES (300, 'Necessary'),
                            (301, 'Mid'),
                            (302, 'Not necessary');
                            
                     INSERT INTO category (id, description)
                     VALUES (400, 'Home'),
                            (401, 'Job'),
                            (402, 'Learning');
                            
                     INSERT INTO task (id, description, user_id, mandatory_id)
                     VALUES  (200, 'do_homework', 100, 300),
                             (201, 'do_cleaning', 100, 301),
                             (202, 'do_coding', 101, 301),
                             (203, 'do_writing', 101, 302);
                     
                     INSERT INTO task_category(task_id, category_id)
                     VALUES  (200, 400),
                             (201, 400),
                             (202, 402),
                             (203, 402);
                     \s
                     """)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
