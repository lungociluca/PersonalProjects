package com.utcn.lab2.controller;

import com.utcn.lab2.dto.CompleteQuestion;
import com.utcn.lab2.model.Entry;
import com.utcn.lab2.model.Question;
import com.utcn.lab2.model.Tag;
import com.utcn.lab2.repository.IEntryRepository;
import com.utcn.lab2.service.CompleteQuestionService;
import com.utcn.lab2.service.EntryService;
import com.utcn.lab2.service.QuestionService;
import com.utcn.lab2.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CompleteQuestionController {

    @Autowired
    CompleteQuestionService completeQuestionService;

    @Autowired
    TagService tagService;

    @RequestMapping(method = RequestMethod.GET, value = "/questions")
    @ResponseBody
    private List<CompleteQuestion> findByTitle(@RequestParam(name = "title") String title) {
        return completeQuestionService.getQuestionsByTitle(title);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/questionsByTag")
    @ResponseBody
    private List<CompleteQuestion> findByTag(@RequestParam(name = "name") String name) {
        return completeQuestionService.getQuestionsByTag(name);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/saveQuestion")
    @ResponseBody
    public CompleteQuestion saveQuestion(@RequestBody CompleteQuestion question) {
        return completeQuestionService.save(question);
    }
}
