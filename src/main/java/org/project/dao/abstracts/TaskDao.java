package org.project.dao.abstracts;

import org.project.entity.Task;

import java.util.List;

public interface TaskDao {
    Task findById(Long id);

    List<Task> findAll();

    void save(Task entity);

    void update(Task entity);

    void delete(Task entity);
}
