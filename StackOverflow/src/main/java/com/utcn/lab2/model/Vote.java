package com.utcn.lab2.model;

import javax.persistence.*;

@Entity
@Table(name = "votes")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vote_id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "vote_type")
    private Integer voteType;

    @Column(name = "entry_id")
    private Integer entryId;

    public Vote(Integer vote_id, Integer userId, Integer voteType, Integer entryId) {
        this.vote_id = vote_id;
        this.userId = userId;
        this.voteType = voteType;
        this.entryId = entryId;
    }

    public Vote() {

    }

    public Integer getVote_id() {
        return vote_id;
    }

    public void setVote_id(Integer vote_id) {
        this.vote_id = vote_id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getVoteType() {
        return voteType;
    }

    public void setVoteType(Integer voteType) {
        this.voteType = voteType;
    }

    public Integer getEntryId() {
        return entryId;
    }

    public void setEntryId(Integer entryId) {
        this.entryId = entryId;
    }
}
