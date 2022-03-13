package com.utcn.lab2.model;

import javax.persistence.*;

@Entity
@Table(name = "answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer answer_id;

    @Column(name = "entry_id")
    Integer entryId;

    @Column(name = "question_answered_id")
    Integer questionAnsweredId;

    public Answer(Integer answer_id, Integer entryId, Integer questionAnsweredId) {
        this.answer_id = answer_id;
        this.entryId = entryId;
        this.questionAnsweredId = questionAnsweredId;
    }

    public Answer() {

    }

    public String toString() {
        return "Answer: id=" + answer_id + " entry=" + entryId + " questionAnswered=" + questionAnsweredId;
    }

    public Integer getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(Integer answer_id) {
        this.answer_id = answer_id;
    }

    public Integer getEntryId() {
        return entryId;
    }

    public void setEntryId(Integer entryId) {
        this.entryId = entryId;
    }

    public Integer getQuestionAnsweredId() {
        return questionAnsweredId;
    }

    public void setQuestionAnsweredId(Integer questionAnsweredId) {
        this.questionAnsweredId = questionAnsweredId;
    }
}
