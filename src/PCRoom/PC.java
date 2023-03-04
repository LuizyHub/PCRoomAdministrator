package PCRoom;

public class PC {
    private PCAdministrator administrator = null;
    private int pcNumber;


    public PC(PCAdministrator administrator, int pcNumber) {
        this.administrator = administrator;
        this.pcNumber = pcNumber;
    }

    @Override
    public String toString() {
        return "PC" + pcNumber;
    }
}
