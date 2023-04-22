package com.yuhualing.model;

import java.io.Serializable;

public class Todo implements Serializable {

//    private static final long serialVersionUID = -23802748;

    private Integer id;
    private String text;
    private Integer completed;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public Integer getCompleted() {
        return completed;
    }

    public void setCompleted(Integer completed) {
        this.completed = completed;
    }
}