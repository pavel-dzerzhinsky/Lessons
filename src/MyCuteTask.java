public class MyCuteTask implements Task{
    private String name;
    private String id;

    MyCuteTask(){}

    MyCuteTask(String id, String name) {
        this.id=id;
        this.name=name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public String toString() {
        return id.concat(" ").concat(name);
    }
}
