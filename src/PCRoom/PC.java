package PCRoom;

import java.util.Date;

public class PC {
    private int pcNumber;

    private Date endTime;


    public PC(int pcNumber) {
        this.pcNumber = pcNumber;
        endTime = new Date();
    }

    public void addTime(long min){
        if (isTimeEnd()){
            endTime.setTime(System.currentTimeMillis() + min*60*1000);
        }
        else {
            endTime.setTime(endTime.getTime() + min*60*1000);
        }
        showEndTime();
    }

    public void showEndTime(){
        System.out.println(this + " " + (endTime.getTime() - System.currentTimeMillis())/(1000*60) + "min left");
    }

    public void stop(){
        if (!isTimeEnd())
            endTime.setTime(System.currentTimeMillis());
    }

    public boolean isTimeEnd(){
        if (endTime.getTime() <= System.currentTimeMillis())
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "PC" + pcNumber;
    }
}
