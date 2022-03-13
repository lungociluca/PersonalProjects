package com.utcn.lab2.repository;

import com.utcn.lab2.model.Entry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEntryRepository extends CrudRepository<Entry, Integer> {
}
