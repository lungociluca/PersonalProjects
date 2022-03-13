package com.utcn.lab2.service;

import com.utcn.lab2.model.Answer;
import com.utcn.lab2.model.Entry;
import com.utcn.lab2.repository.IAnswerRepository;
import com.utcn.lab2.repository.IEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class AnswerService {

    @Autowired
    private IAnswerRepository iAnswerRepository;

    @Autowired
    private IEntryRepository iEntryRepository;

    @Autowired
    private VoteService voteService;

    private List<Answer> getAnswersRelations(Integer id) {
        return iAnswerRepository.findByQuestionAnsweredId(id);
    }

    public Answer getAnswer(Integer id) {
        return iAnswerRepository.findById(id).orElse(null);
    }

    public List<Answer> findByEntryId(Integer id) {
        return iAnswerRepository.findByEntryId(id);
    }

    public List<Entry> getAnswersByQuestionAnsweredId(Integer id) {
        List<Answer> answers = getAnswersRelations(id);
        List<Entry> entries = new ArrayList<>(answers.size());

        for(Answer answer : answers) {
            entries.add(iEntryRepository.findById(answer.getEntryId()).orElse(null));
        }

        //sort by votes
        Collections.sort(entries, new Comparator<Entry>() {
            @Override
            public int compare(Entry o1, Entry o2) {
                return voteService.compareEntriesByVote(o1, o2);
            }
        });

        return entries;
    }

    public Answer save(Answer answer) {
        return iAnswerRepository.save(answer);
    }

    public void delete(Integer id){
        Answer answer = iAnswerRepository.findById(id).orElse(null);
        if(answer == null) {
            System.out.println("No answer with id " + id + " was found in the data base");
            return;
        }
        iAnswerRepository.deleteById(id);
        voteService.findByEntryId(answer.getEntryId());
        voteService.deleteByEntryId(answer.getEntryId());
        iEntryRepository.deleteById(answer.getEntryId());
    }
}
