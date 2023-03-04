package PCRoom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

public class PCAdministrator {
    private final int MONEYBYHOUR = 2000; // 시간당 가격
    private final int MONEYBYHOURNIGHT = 1500; // 야간 시간당 가격
    private final int BONUSPERCENT = 10; // 보너스 정도
    private final int BOUNUSLIMIT = 2000; // 보너스 최소금액
    private final int PCNUM = 16; // PC개수
    private ArrayList<PC> pcList = null;
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public PCAdministrator() {
        pcList = new ArrayList<>(PCNUM);
        for (int i = 0; i < PCNUM; i++) {
            pcList.add(new PC(i));
        }
    }
    private void showFunction(){
        System.out.println("menu : show all functions");
        System.out.println("price tag : show price tag");
        System.out.println("stop number : stop PC");
        System.out.println("add number, money : add time (ex. add 13, 3000) -> add PC13 3000won");
        System.out.println("number : show PC end time");
        System.out.println("exit : stop PC Admin program");
    }
    private void showPcStatus(){
        System.out.print("run : ");
        for (PC pc : pcList) {
            if (!pc.isTimeEnd())
                System.out.printf(pc + " ");
        }
        System.out.print("\nempty : ");
        for (PC pc : pcList) {
            if (pc.isTimeEnd())
                System.out.printf(pc + " ");
        }
        System.out.println();
    }
    private void showPriceTag(){
        System.out.println("!!Price Tag!!");
        System.out.println(MONEYBYHOUR + "won : 1hour");
        System.out.println("----------");
        System.out.println("Money Event");
        System.out.println("more than " + BOUNUSLIMIT + "won : " + BONUSPERCENT + "% bonus time");
        System.out.println("----------");
        System.out.println("Late Night Event");
        System.out.println("0am - 6am");
        System.out.println(MONEYBYHOURNIGHT + "won : 1hour");
    }
    private void wrongInput(){
        System.err.println("wrong input, check menu again");
        showFunction();
    }

    private int getTimeByPriceTag(int money){
        if (new Date().getHours() < 6){
            if (money >= BOUNUSLIMIT){
                return (int)((money/MONEYBYHOURNIGHT)*(1 + BONUSPERCENT/100.0));
            }
            else
                return (money/MONEYBYHOURNIGHT);
        }
        else {
            if (money >= BOUNUSLIMIT){
                return (int)((money/MONEYBYHOUR)*1.1);
            }
            else
                return (money/MONEYBYHOUR);
        }
    }
    public void run(){
        showFunction();
        while (true){
            showPcStatus();
            System.out.print(">");
            String order = "";
            try {
                order = br.readLine().trim();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (order.equals("exit"))break;

            if (order.equals("menu")){
                showFunction();
            }
            else if (order.equals("price tag")) {
                showPriceTag();
            }
            else if (order.contains("stop")) {
                int PCnum;
                try {
                    PCnum = Integer.parseInt(order.substring(4).trim());
                } catch (NumberFormatException e) {
                    wrongInput();
                    continue;
                }
                pcList.get(PCnum).stop();
            }
            else if (order.contains("add")) {
                System.out.println(order);
                int PCnum, money;
                try {
                    PCnum = Integer.parseInt(order.substring(3,order.indexOf(',')).trim());
                    money = Integer.parseInt(order.substring(order.indexOf(',')+1).trim());
                } catch (NumberFormatException e) {
                    wrongInput();
                    continue;
                }
                int time = getTimeByPriceTag(money);
                pcList.get(PCnum).addTime(time);
            }
            else {
                int PCnum;
                try {
                    PCnum = Integer.parseInt(order);
                } catch (NumberFormatException e) {
                    wrongInput();
                    continue;
                }
                pcList.get(PCnum).showEndTime();
            }


        }
    }
}
