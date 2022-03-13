package com.utcn.lab2.repository;

import com.utcn.lab2.model.Tag;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITagRepository extends CrudRepository<Tag, Integer> {
    List<Tag> findByTagName(String name);
}
