package org.project.dao.abstracts;

import org.project.entity.Mandatory;

import java.util.List;

public interface MandatoryDao {
    Mandatory findById(Long id);

    List<Mandatory> findAll();

    void save(Mandatory entity);

    void update(Mandatory entity);

    void delete(Mandatory entity);
}
