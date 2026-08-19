import ru.ab.edu.service.DBTaskManager;
import ru.ab.edu.service.DoryFishTaskManager;
import ru.ab.edu.service.TaskManager;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() throws Exception {
    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.
    System.out.println("add <id> <name> - adds a new task");
    System.out.println("rm <id> - removes a task by id");
    System.out.println("fdescr <id> - find by id");
    System.out.println("fname <id> - find by name");
    System.out.println("ch <id> <name> - nchage name by id   111   ");
    System.out.println("showAll - find by name      ");

    TaskManager tm = new DoryFishTaskManager();
    tm = new DBTaskManager();
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    while (true) {
        String s = reader.readLine();
        String[] split = s.split(" ");
        if ("Exit".equals(s)) {
            return;
        } else if ("showAll".equals(s)) {
            tm.getListTasks(System.out);
        } else if (s.startsWith("add")) {
            tm.addTask(split[1], split[2], split[3], split[4]);
        } else if (s.startsWith("fdescr")) {
            System.out.println(tm.findTask(split[1]));
        } else if (s.startsWith("fname")) {
            System.out.println(tm.findTask(split[1]));
        } else if (s.startsWith("ch")) {
            tm.updateTask(split[1], split[2], split[3], split[4]);
        } else if (s.startsWith("rm")) {
            System.out.println(tm.removeTask(split[1]));
        } else {
            System.out.println("See acceptable commands above, smartass ;-)");
        }
    }
}
