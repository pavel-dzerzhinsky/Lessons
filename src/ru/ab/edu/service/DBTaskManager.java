package ru.ab.edu.service;

import ru.ab.edu.dto.CreateTaskDTO;
import ru.ab.edu.dto.TaskDTO;
import ru.ab.edu.dto.UpdateTaskDTO;
import ru.ab.edu.repository.TaskRepository;

import java.io.PrintStream;
import java.util.List;

public class DBTaskManager implements TaskManager {
    private final TaskRepository taskRepository;

    public DBTaskManager() {
        this.taskRepository = new TaskRepository();
    }

    @Override
    public void addTask(String id, String title, String description, String status) {
        taskRepository.save(new CreateTaskDTO(title, description, status));
        System.out.println("Задача добавлена.");
    }

    @Override
    public void updateTask(String id, String title, String description, String status) {
        taskRepository.update(new UpdateTaskDTO(id, title, description, status));
    }

    @Override
    public void getListTasks(PrintStream whereToShow) {
        List<TaskDTO> tasks = taskRepository.findAll();
        for (TaskDTO task : tasks) {
            whereToShow.println(task);
        }
    }

    @Override
    public void addTask(Task task) {
        taskRepository.save(new CreateTaskDTO(task.getTitle(), task.getDescription(), task.getStatus()));
        System.out.println("Задача добавлена.");
    }

    @Override
    public String removeTask(String id) {
        try {
            taskRepository.deleteById(id);
            return "The task" + id + " has been removed";
        } catch (Exception e) {
            return e.toString();
        }
    }

    @Override
    public Task findTask(String query) throws Exception {
        List<TaskDTO> tasks = taskRepository.searchByTitleOrDescription(query);
        for (TaskDTO task : tasks) {
            return new MyCuteTask(task.id(), task.title(), task.description(), task.status());
        }
        throw new Exception("No task found by query " + query);
    }
}


