package test.timer;

import java.util.TimerTask;

public class ATask extends TimerTask {

    private int i;

    public ATask(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        System.out.println("ATask" + i);
    }

}
