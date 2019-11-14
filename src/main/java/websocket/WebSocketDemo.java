package websocket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * @author shuaijunhe
 * @create 2019/11/14
 * @description
 */
@ServerEndpoint("/test")
public class WebSocketDemo {

    @OnMessage
    public void onMessage(String message, Session session) throws IOException, InterruptedException{
        System.out.println("客户端说：" + message);

        int i = 0;
        while(true){
            session.getBasicRemote().sendText("world" + i++);
            Thread.sleep(2000);
        }
    }
}
