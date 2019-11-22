package guava.event_bus;

import com.google.common.eventbus.Subscribe;

/**
 * @author hsj
 * @create 2019/11/22
 */
public class OneListener {

    @Subscribe
    public void action(final String event){

        System.out.println("OneListener 收到 : " + event);
    }
}
