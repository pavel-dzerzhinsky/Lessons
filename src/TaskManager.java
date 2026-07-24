import java.io.PrintStream;

public interface TaskManager {
    public void addTask(String id, String name);
    public void addTask(Task task);
    public String removeTask(String id);
    public Task getTaskByID(String id);
    public Task getTaskByName(String name);
    public void changeTask(String id, String name);
    public void showAll(PrintStream whereToShow);
}
