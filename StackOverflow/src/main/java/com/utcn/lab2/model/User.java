package com.utcn.lab2.model;

import javax.persistence.*;

@Entity
@Table(name = "user_data")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    @Column (name = "username")
    private String username;

    @Column (name = "user_password")
    private String user_password;

    @Column (name = "score")
    private Integer score;

    public User(Integer user_id, String username, String user_password, Integer score) {
        this.user_id = user_id;
        this.username = username;
        this.user_password = user_password;
        this.score = score;
    }

    public User() {

    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}


