package ru.ab.edu.service;

public class MyCuteTask implements Task {
    private String title;
    private String id;
    private String description;
    private String status;

    MyCuteTask(String id, String title, String description, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    @Override
    public void updateTask(String title, String description, String status) {
        this.title = title;
        this.description = description;
        this.status = status;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return id.concat(" ").concat(title)
                .concat(" ").concat(description)
                .concat(" ").concat(status);
    }
}
