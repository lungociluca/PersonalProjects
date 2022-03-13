package com.utcn.lab2.repository;

import com.utcn.lab2.model.Question;
import com.utcn.lab2.model.QuestionTag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IQuestionTagRepository extends CrudRepository<QuestionTag, Integer> {
    List<QuestionTag> findByTagId(Integer id);
}
