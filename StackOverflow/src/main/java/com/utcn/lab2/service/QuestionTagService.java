package com.utcn.lab2.service;

import com.utcn.lab2.model.QuestionTag;
import com.utcn.lab2.repository.IQuestionTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionTagService {

    @Autowired
    IQuestionTagRepository iQuestionTagRepository;

    public List<QuestionTag> getQuestionTagRelations(Integer tagId) {
        return iQuestionTagRepository.findByTagId(tagId);
    }
}
