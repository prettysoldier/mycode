package source_code_java.java_.timer;

import java.util.Timer;

@SuppressWarnings("ALL")
public class MyTimer {

    public static void main(String[] args) {

        Timer timer = new Timer();
        for (int i = 0; i < 1000; i++) {
            timer.schedule(new ATask(i), i, 2000);
        }
    }
}
