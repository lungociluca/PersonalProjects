package com.utcn.lab2.service;

import com.utcn.lab2.model.QuestionTag;
import com.utcn.lab2.model.Tag;
import com.utcn.lab2.repository.IQuestionTagRepository;
import com.utcn.lab2.repository.ITagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService {

    @Autowired
    private ITagRepository iTagRepository;

    public List<Tag> getTagsByName(String name) {
        return iTagRepository.findByTagName(name);
    }
}
