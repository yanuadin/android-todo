package com.todoapk.data.model;

import com.todoapk.base.BaseModel;

public class Task extends BaseModel {
    private String id;
    private String date;
    private String title;
    private String time;

    public Task(String id, String date, String title, String time) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.time = time;
    }

    public Task(String date, String title, String time) {
        this.date = date;
        this.title = title;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
