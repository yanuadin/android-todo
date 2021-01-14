package com.todoapk.data.model;

import com.todoapk.base.BaseModel;

public class Task extends BaseModel {
    private String id, title, time;
    private int date, status;

    public Task(String id, int date, String title, String time, int status) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.time = time;
        this.status = status;
    }

    public Task(int date, String title, String time, int status) {
        this.date = date;
        this.title = title;
        this.time = time;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return String.valueOf(this.date);
    }

    public void setDate(int date) {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
