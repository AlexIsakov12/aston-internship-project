package org.project.dao.abstracts;

import org.project.entity.Category;

import java.util.List;

public interface CategoryDao {
    Category findById(Long id);

    List<Category> findAll();

    void save(Category entity);

    void update(Category entity);

    void delete(Category entity);
}
