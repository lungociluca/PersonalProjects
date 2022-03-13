package com.utcn.lab2.repository;

import com.utcn.lab2.model.Answer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAnswerRepository extends CrudRepository<Answer, Integer> {
    List<Answer> findByQuestionAnsweredId(Integer id);

    List<Answer> findByEntryId(Integer id);
}
