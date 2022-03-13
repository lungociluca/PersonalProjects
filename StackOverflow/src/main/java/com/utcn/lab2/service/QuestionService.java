package com.utcn.lab2.service;

import com.utcn.lab2.model.Question;
import com.utcn.lab2.model.QuestionTag;
import com.utcn.lab2.model.Tag;
import com.utcn.lab2.repository.IQuestionRepository;
import com.utcn.lab2.repository.IQuestionTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class QuestionService {

    @Autowired
    IQuestionRepository iQuestionRepository;

    @Autowired
    private IQuestionTagRepository iQuestionTagRepository;

    @Autowired
    TagService tagService;

    private List<Integer> getQuestionsCorrespondingWithTag(Integer tagId) {
        List<QuestionTag> relations = iQuestionTagRepository.findByTagId(tagId);
        List<Integer> idList = new ArrayList<>(relations.size());
        for(QuestionTag relation : relations) {
            idList.add(relation.getQuestionId());
        }
        return idList;
    }

    public List<Question> findByEntryId(Integer id) {
        return iQuestionRepository.findByEntryId(id);
    }

    public Question save(Question question) {
        return iQuestionRepository.save(question);
    }

    public Question findById(Integer id) {
        return iQuestionRepository.findById(id).orElse(null);
    }

    public List<Question> findByTitle(String title) {
        return iQuestionRepository.findByTitleContaining(title);
    }

    public List<Question> findByTagName(String name) {
        List<Tag> tags = tagService.getTagsByName(name);
        List<Integer> questionsId = getQuestionsCorrespondingWithTag(tags.get(0).getTag_id());
        List<Question> questions = new ArrayList<>(questionsId.size());
        for(Integer id : questionsId) {
            questions.add(findById(id));
        }
        return questions;
    }
}
