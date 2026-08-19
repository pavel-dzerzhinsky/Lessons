package ru.ab.edu.service;

public interface Task {
    void updateTask(String title, String description, String status);

    String getTitle();

    String getID();

    String getDescription();

    String getStatus();
}
