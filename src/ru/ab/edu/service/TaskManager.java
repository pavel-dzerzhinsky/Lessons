package ru.ab.edu.service;

import java.io.PrintStream;

public interface TaskManager {
    void addTask(String id, String name, String description, String status);

    void addTask(Task task);

    String removeTask(String id);

    Task findTask(String query) throws Exception;

    void updateTask(String id, String name, String description, String status);

    void getListTasks(PrintStream whereToShow);
}
