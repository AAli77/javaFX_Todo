package com.example.finalproject_todo.model;

import java.sql.Timestamp;

public class Task {
    private int userId;
    private int taskId;
    private String task;
    private Timestamp dateCreated;
    private String description;

    public Task() {
    }

    public Task(int userId, String task, Timestamp dateCreated, String description) {
        this.userId = userId;
        this.task = task;
        this.dateCreated = dateCreated;
        this.description = description;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
}
