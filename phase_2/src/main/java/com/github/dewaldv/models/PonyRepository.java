package com.github.dewaldv.models;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PonyRepository extends CrudRepository<Pony, Integer> {
    Pony findByName(String name);
}
