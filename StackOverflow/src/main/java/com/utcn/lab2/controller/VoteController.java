package com.utcn.lab2.controller;

import com.utcn.lab2.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VoteController {

    @Autowired
    VoteService voteService;

    @RequestMapping(method = RequestMethod.GET, value = "vote")
    @ResponseBody
    private void vote(@RequestParam(name = "entryId") Integer entryId, @RequestParam(name = "voteValue") Integer voteValue, @RequestParam(name = "authorId") Integer authorId) {
        voteService.vote(entryId, voteValue, authorId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "voteCount")
    @ResponseBody
    private Integer voteCount(@RequestParam(name = "entryId") Integer entryId) {
        return voteService.getVoteCount(entryId);
    }
}
