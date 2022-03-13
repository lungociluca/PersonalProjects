package com.utcn.lab2.model;

import javax.persistence.*;

@Entity
@Table(name = "question_tags")
public class QuestionTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "question_id")
    private Integer questionId;

    @Column(name = "tag_id")
    private Integer tagId;

    public QuestionTag(Integer id, Integer questionId, Integer tag_id) {
        this.id = id;
        this.questionId = questionId;
        this.tagId = tag_id;
    }

    public QuestionTag() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }
}
