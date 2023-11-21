package org.project.dao.abstracts;

import org.project.entity.User;

import java.util.List;

public interface UserDao {
    User findById(Long id);

    List<User> findAll();

    void save(User entity);

    void update(User entity);

    void delete(User entity);
}
