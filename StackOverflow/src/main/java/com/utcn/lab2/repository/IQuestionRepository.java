package com.utcn.lab2.repository;

import com.utcn.lab2.model.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IQuestionRepository extends CrudRepository<Question, Integer> {
    List<Question> findByTitleContaining(String title);

    List<Question> findByEntryId(Integer id);
}
