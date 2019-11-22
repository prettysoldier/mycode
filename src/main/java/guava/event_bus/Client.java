package guava.event_bus;

import com.google.common.eventbus.EventBus;

/**
 * @author hsj
 * @create 2019/11/22
 */
public class Client {

    public static void main(String[] args) {

        final EventBus eventBus = new EventBus();

        eventBus.register(new OneListener());

        System.out.println("post the string event.");
        eventBus.post("I am String event");
        
        System.out.println("post the int event.");
        eventBus.post(1000 + "");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
