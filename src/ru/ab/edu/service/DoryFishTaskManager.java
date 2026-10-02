package ru.ab.edu.service;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class DoryFishTaskManager implements TaskManager {
    private final List<Task> myCuteTasks = new ArrayList<>();

    @Override
    public void addTask(String id, String name, String description, String status) {
        myCuteTasks.add(new MyCuteTask(id, name, description, status));
    }

    public void addTask(Task task) {
        myCuteTasks.add(task);
    }

    public String removeTask(String id) {
        for (Task t : myCuteTasks) {
            if (id.equals(t.getTitle())) {
                myCuteTasks.remove(t);
                return "The task " + id + " has been removed";
            }
        }
        return "No task found with id " + id;
    }

    @Override
    public Task findTask(String query) {
        for (Task t : myCuteTasks) {
            if (query.equals(t.getID()) || query.equals(t.getTitle())) {
                return t;
            }
        }
        return null;
    }

    @Override
    public void updateTask(String id, String title, String description, String status) {
        for (Task t : myCuteTasks) {
            if (id.equals(t.getID())) {
                t.updateTask(title, description, status);
            }
        }
    }

    public void getListTasks(PrintStream whereToShow) {
        for (Task myCuteTask : myCuteTasks) {
            whereToShow.println(myCuteTask);
        }
    }


}
