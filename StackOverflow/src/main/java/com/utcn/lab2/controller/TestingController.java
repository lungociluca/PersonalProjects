package com.utcn.lab2.controller;

import com.utcn.lab2.model.Answer;
import com.utcn.lab2.model.Question;
import com.utcn.lab2.model.QuestionTag;
import com.utcn.lab2.model.Tag;
import com.utcn.lab2.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TestingController {

    @Autowired
    CompleteQuestionService completeQuestionService;

    @Autowired
    QuestionService questionService;

    @Autowired
    TagService tagService;

    @Autowired
    QuestionTagService questionTagService;

    @Autowired
    AnswerService answerService;

    @RequestMapping(method = RequestMethod.GET, value = "/test")
    @ResponseBody
    private List<QuestionTag> test(@RequestParam(name = "p") Integer name) {
        return  questionTagService.getQuestionTagRelations(name);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/t")
    @ResponseBody
    private Answer test1(@RequestParam(name = "name") Integer id) {
        return answerService.getAnswer(id);
    }
}
