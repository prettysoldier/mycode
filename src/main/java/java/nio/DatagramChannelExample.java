package java.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * @author Shuaijun He
 */
public class DatagramChannelExample {

    public static void main(String[] args) throws IOException {
        DatagramChannel channel = DatagramChannel.open();
        channel.socket().bind(new InetSocketAddress(9999));

        DatagramChannelExample.sendData(channel);

        DatagramChannelExample.receiveData(channel);
    }

    private static void sendData(DatagramChannel channel) {
        new Thread(
            () -> {
                while (true) {
                    InputStreamReader isr = new InputStreamReader(System.in);

                    BufferedReader br = new BufferedReader(isr);
                    String s = "";
                    try {
                        s = br.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    ByteBuffer buf = ByteBuffer.allocate(48);
                    buf.clear();
                    buf.put(s.getBytes());
                    buf.flip();

                    try {
                        channel.send(buf, new InetSocketAddress("localhost",
                            9999));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
    }

    private static void receiveData(DatagramChannel channel) throws IOException {
        ByteBuffer buf = ByteBuffer.allocate(48);
        while (true) {
            buf.clear();
            channel.receive(buf);
            buf.flip();
            while (buf.hasRemaining()) {
                System.out.print((char) buf.get());
            }
        }
    }
}
