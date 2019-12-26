package java_.util.logging;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author hsj
 * @create 2019/12/26
 */
public class Log_Demo {

    private static final Logger LOGGER = Logger.getLogger("Log_Demo");

    public static void main(String[] args) {

//        Logger.getGlobal().setLevel(Level.ALL);
//        Logger.getGlobal().info("asdfsdf");

        LOGGER.setLevel(Level.ALL);
        LOGGER.setUseParentHandlers(false);
        Handler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);
        LOGGER.addHandler(handler);

        f();
    }

    public static void f(){

        LOGGER.entering("Log_Demo", "main");

        LOGGER.info("111");
        LOGGER.warning("222");
        LOGGER.severe("333");

        LOGGER.exiting("Log_Demo", "main");
    }
}
