import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayListTaskManager implements TaskManager{
    private List <Task> myCuteTasks = new ArrayList<>();

    @Override
    public void addTask(String id, String name) {
        myCuteTasks.add(new MyCuteTask(id, name));
    }

    public void addTask(Task task) {myCuteTasks.add(task);}
    public String removeTask(String id) {
        Iterator <Task> it = myCuteTasks.iterator();
        while (it.hasNext()) {
            Task t = it.next();
            if (id.equals(t.getName())) {
                myCuteTasks.remove(t);
                return "The task "+id+" has been removed";
            }
        }
        return "No task found with id " + id;
    }
    public Task getTaskByID(String id) {
        Iterator <Task> it = myCuteTasks.iterator();
        while (it.hasNext()) {
            Task t = it.next();
            if (id.equals(t.getID())) {
                return t;
            }
        }
        return null;
    }

    public Task getTaskByName(String name) {
        Iterator <Task> it = myCuteTasks.iterator();
        while (it.hasNext()) {
            Task t = it.next();
            if (name.equals(t.getName())) {
                return t;
            }
        }
        return null;
    }

    @Override
    public void changeTask(String id, String name) {
        Iterator <Task> it = myCuteTasks.iterator();
        while (it.hasNext()) {
            Task t = it.next();
            if (id.equals(t.getID())) {
                t.setName(name);
            }
        }
    }

    public void showAll(PrintStream whereToShow) {
        Iterator <Task> it = myCuteTasks.iterator();
        while (it.hasNext()) {
                whereToShow.println(it.next());
        }
    }


}
