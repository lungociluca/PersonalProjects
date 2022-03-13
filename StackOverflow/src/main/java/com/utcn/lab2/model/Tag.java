package com.utcn.lab2.model;

import javax.persistence.*;

@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tag_id;

    @Column(name = "tag_name")
    private String tagName;

    public Tag(Integer tag_id, String tag_name) {
        this.tag_id = tag_id;
        this.tagName = tag_name;
    }

    public Tag() {

    }

    public Integer getTag_id() {
        return tag_id;
    }

    public void setTag_id(Integer tag_id) {
        this.tag_id = tag_id;
    }

    public String getTag_name() {
        return tagName;
    }

    public void setTag_name(String tag_name) {
        this.tagName = tag_name;
    }
}
