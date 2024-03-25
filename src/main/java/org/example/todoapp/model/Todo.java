package org.example.todoapp.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;


@Entity
public class Todo {
    @Id
    private int id;
    @Column
    private String username;
    @Column // optional when name of column and property is same
    private String description;
    @Column
    private LocalDate targetDate;
    @Column
    private Boolean done;


    public Todo(){}

    public Todo(int id, String username, String description, LocalDate targetDate, boolean done) {
        this.id = id;
        this.username = username;
        this.description = description;
        this.targetDate = targetDate;
        this.done = done;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public void setDone(boolean done) {
        this.done = done;
    }


    @Override
    public String toString() {
        return "Todo{" +
               "id=" + id +
               ", username='" + username + '\'' +
               ", desctiption='" + description + '\'' +
               ", targetDate='" + targetDate + '\'' +
               ", done='" + done + '\'' +
               '}';
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public boolean getDone() {
        return done;
    }


}
