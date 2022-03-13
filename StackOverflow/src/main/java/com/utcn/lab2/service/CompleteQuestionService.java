package com.utcn.lab2.service;

import com.utcn.lab2.dto.CompleteQuestion;
import com.utcn.lab2.model.Entry;
import com.utcn.lab2.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CompleteQuestionService {

    @Autowired
    private EntryService entryService;

    @Autowired
    private QuestionService questionService;

    private List<CompleteQuestion> returnSortedList(List<CompleteQuestion> list) {
        Collections.sort(list, new Comparator<CompleteQuestion>() {
            @Override
            public int compare(CompleteQuestion o1, CompleteQuestion o2) {
                boolean after = o2.getCreation_date().after(o1.getCreation_date());
                if(after)
                    return -1;
                else
                    return 1;
            }
        });
        return list;
    }

    private List<CompleteQuestion> joinQuestionAndEntry(List<Question> questions) {
        List<CompleteQuestion> completeQuestions = new ArrayList<>(questions.size());
        for(Question question : questions) {
            Entry entry = entryService.findById(question.getEntryId());
            completeQuestions.add(new CompleteQuestion(question, entry));
        }
        return completeQuestions;
    }

    public List<CompleteQuestion> getQuestionsByTitle(String title) {
        List<Question> questions = questionService.findByTitle(title);
        List<CompleteQuestion> completeQuestions = joinQuestionAndEntry(questions);
        return returnSortedList(completeQuestions);
    }

    public List<CompleteQuestion> getQuestionsByTag(String tag) {
        List<Question> questions = questionService.findByTagName(tag);
        List<CompleteQuestion> completeQuestions = joinQuestionAndEntry(questions);
        return returnSortedList(completeQuestions);
    }

    public CompleteQuestion save(CompleteQuestion completeQuestion) {
        Question question = new Question(completeQuestion.getQuestion_id(),
                                        completeQuestion.getEntry_id(),
                                        completeQuestion.getTitle());
        Entry entry = new Entry(completeQuestion.getEntry_id(),
                                completeQuestion.getAuthor_id(),
                                completeQuestion.getText(),
                                completeQuestion.getCreation_date());
        entryService.save(entry);
        //make sure the question id to identify the entry is the same as the saved entry
        question.setEntryId(entry.getEntry_id());
        questionService.save(question);
        return completeQuestion;
    }
}
