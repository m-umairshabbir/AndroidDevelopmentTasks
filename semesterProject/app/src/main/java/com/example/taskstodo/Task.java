package com.example.taskstodo;
public class Task {
    private String id;
    private String name;
    private boolean completed; // New field to represent task completion status

    public Task() {
        // Default constructor required for Firebase
    }

    public Task(String id, String name) {
        this.id = id;
        this.name = name;
        this.completed = false; // By default, task is not completed
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
