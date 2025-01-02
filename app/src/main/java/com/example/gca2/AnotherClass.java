package com.example.gca2;

public class AnotherClass {
    private String name;
    private String title;
    private String learning;
    private String date;

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter and Setter for learning
    public String getLearning() {
        return learning;
    }

    public void setLearning(String learning) {
        this.learning = learning;
    }

    // Getter and Setter for date
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // Constructor with parameters
    public AnotherClass(String name, String title, String learning, String date) {
        this.name = name;
        this.title = title;
        this.learning = learning;
        this.date = date;
    }

    // Default constructor
    public AnotherClass() {
    }
}
