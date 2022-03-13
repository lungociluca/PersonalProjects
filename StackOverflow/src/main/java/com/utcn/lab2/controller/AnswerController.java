package com.utcn.lab2.controller;

import com.utcn.lab2.model.Answer;
import com.utcn.lab2.model.Entry;
import com.utcn.lab2.service.AnswerService;
import com.utcn.lab2.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @RequestMapping(method = RequestMethod.GET, value = "/answerByQuestionId")
    @ResponseBody
    private List<Entry> getAnswersToQuestion(@RequestParam(name = "questionId") Integer questionId) {
        return answerService.getAnswersByQuestionAnsweredId(questionId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/saveAnswer")
    @ResponseBody
    private Answer save(@RequestBody Answer answer) {
        System.out.println(answer);
        return answerService.save(answer);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/deleteAnswer")
    @ResponseBody
    private void delete(@RequestParam(name = "id") Integer id) {
        answerService.delete(id);
    }
}
