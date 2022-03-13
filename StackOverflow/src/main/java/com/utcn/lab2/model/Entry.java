package com.utcn.lab2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "Entry")
public class Entry {

    @Id
    @Column(name = "entry_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer entry_id;

    //@OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "author_id", referencedColumnName = "user_id")
    @Column(name = "author_id")
    private Integer author_id;

    @Column (name = "entry_text")
    private String entry_text;

    @Column (name = "creation_date")
    @CreationTimestamp
    private Date creation_date;

    public Entry(Integer entry_id, Integer author_id, String entry_text, Date creation_date) {
        this.entry_id = entry_id;
        this.author_id = author_id;
        this.entry_text = entry_text;
        this.creation_date = creation_date;
    }

    public Entry() {

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

    public String getEntry_text() {
        return entry_text;
    }

    public void setEntry_text(String entry_text) {
        this.entry_text = entry_text;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }
}
