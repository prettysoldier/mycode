package test;

import java.util.ArrayList;
import java.util.Timer;

public class B {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        list.add(null);
        System.out.println(list.size());
        System.out.println(list.isEmpty());
        System.out.println(list.get(0) == null);

        new Timer();

    }

    Object o = new Object() {
        @Override
        protected void finalize() throws Throwable {
            // TODO Auto-generated method stub
            super.finalize();
        }
    };

}
