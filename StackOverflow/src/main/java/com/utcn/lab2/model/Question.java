package com.utcn.lab2.model;

import javax.persistence.*;

@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer question_id;

    @Column (name = "entry_id")
    private Integer entryId;

    @Column (name = "title")
    private String title;

    public Question(Integer question_id, Integer entry_id, String title) {
        this.question_id = question_id;
        this.entryId = entry_id;
        this.title = title;
    }

    public Question() {

    }

    public Integer getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Integer question_id) {
        this.question_id = question_id;
    }

    public Integer getEntryId() {
        return entryId;
    }

    public void setEntryId(Integer entryId) {
        this.entryId = entryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
