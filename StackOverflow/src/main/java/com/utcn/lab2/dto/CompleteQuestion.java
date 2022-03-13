package com.utcn.lab2.dto;

import com.utcn.lab2.model.Entry;
import com.utcn.lab2.model.Question;

import java.util.Date;

public class CompleteQuestion {

    private Integer question_id;
    private Integer entry_id;
    private Integer author_id;
    private String title;
    private String text;
    private Date creation_date;

    public CompleteQuestion(Integer question_id, Integer entry_id, Integer author_id, String title, String text, Date creation_date) {
        this.question_id = question_id;
        this.entry_id = entry_id;
        this.author_id = author_id;
        this.title = title;
        this.text = text;
        this.creation_date = creation_date;
    }

    public CompleteQuestion(Question question, Entry entry) {
        this.question_id = question.getQuestion_id();
        this.entry_id = entry.getEntry_id();
        this.author_id = entry.getAuthor_id();
        this.title = question.getTitle();
        this.text = entry.getEntry_text();
        this.creation_date = entry.getCreation_date();
    }

    public CompleteQuestion() {

    }

    public Integer getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Integer question_id) {
        this.question_id = question_id;
    }

    public Integer getEntry_id() {
        return entry_id;
    }

    public void setEntry_id(Integer entry_id) {
        this.entry_id = entry_id;
    }

    public Integer getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Integer author_id) {
        this.author_id = author_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }
}
