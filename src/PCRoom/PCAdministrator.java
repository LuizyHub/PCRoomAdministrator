package PCRoom;

import java.util.ArrayList;

public class PCAdministrator {
    private final int PCNUM = 16;
    private ArrayList<PC> pcList = null;
    public PCAdministrator() {
        pcList = new ArrayList<>(PCNUM);
        for (int i = 0; i < PCNUM; i++) {
            pcList.add(new PC(this, i));
        }
    }
    public void run(){
        System.out.println(pcList);
    }
}
