//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() throws IOException {
    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.
    System.out.println("add <id> <name> - adds a new task");
    System.out.println("rm <id> - removes a task by id");
    System.out.println("fid <id> - find by id");
    System.out.println("fname <id> - find by name");
    System.out.println("ch <id> <name> - nchage name by id");
    System.out.println("showAll - find by name      ");

    TaskManager tm = new ArrayListTaskManager();
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    while (true) {
        String s = reader.readLine();
        String[] split = s.split(" ");
        if ("Exit".equals(s)) {
            return;
        } else if ("showAll".equals(s)) {
            tm.showAll(System.out);
        } else if (!"".equals(s) && s.startsWith("add")) {
            tm.addTask(split[1], split[2]);
        } else if (!"".equals(s) && s.startsWith("fid")) {
            System.out.println(tm.getTaskByID(split[1]));
        } else if (!"".equals(s) && s.startsWith("fname")) {
            System.out.println(tm.getTaskByName(split[1]));
        } else if (!"".equals(s) && s.startsWith("ch")) {
            tm.changeTask(split[1], split[2]);
        } else if (!"".equals(s) && s.startsWith("rm")) {
            tm.removeTask(split[1]);
        } else {
            System.out.println("See acceptable commands above, smartass ;-)" );
        }
    }
}
