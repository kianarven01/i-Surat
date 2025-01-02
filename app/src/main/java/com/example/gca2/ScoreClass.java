package com.example.gca2;

import java.util.Date;

public class ScoreClass {
    private String name;
    private String category;
    private String score;
    private String date;

    // Constructor with all fields
    public ScoreClass(String name, String category, String score, String date) {
        this.name = name;
        this.category = category;
        this.score = score;
        this.date = date;
    }

    // Default constructor
    public ScoreClass() {
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // toString method for easy display
    @Override
    public String toString() {
        return "Score{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", score=" + score +
                ", date='" + date + '\'' +
                '}';
    }
}
