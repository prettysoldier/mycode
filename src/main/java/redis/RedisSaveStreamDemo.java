package redis;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Redis字符串可以存入二进制流
 *
 * @author shuaijunhe
 * @create 2019/3/25
 * @description
 */
public class RedisSaveStreamDemo {

    public static void main(String[] args) {
        RedisUtil.getJedisClient().del("key_image", "key_image_base64", "key_obj");

        setImageBytes("key_image");
        getImageBytes("key_image");

        setImageBase64("key_image_base64");
        getImageBase64("key_image_base64");

        setData("key_obj", new Student());
        Student student = (Student) getData("key_obj");
        System.out.println(student);

    }


    public static void setImageBytes(String key){

        try {
            BufferedImage bufferedImage = ImageIO.read(new File("D:/test/图片.png"));
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", outputStream);
            RedisUtil.getJedisClient().set(key.getBytes(), outputStream.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getImageBytes(String key){

        try {
            byte[] bytes = RedisUtil.getJedisClient().get(key.getBytes());
            FileOutputStream fos = new FileOutputStream("D:/test/图片_copy.png");
            fos.write(bytes);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setImageStr(String key){

        try {
            BufferedImage bufferedImage = ImageIO.read(new File("D:/test/图片.png"));
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", outputStream);
            // 这种方式是错误的 ！！！这种方式是错误的 ！！！这种方式是错误的 ！！！
            RedisUtil.getJedisClient().set(key, new String(outputStream.toByteArray()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getImageStr(String key){

        try {
            String str = RedisUtil.getJedisClient().get(key);
            FileOutputStream fos = new FileOutputStream("D:/test/图片_copy_str.png");
            fos.write(str.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setImageBase64(String key){

        try {
            BufferedImage bufferedImage = ImageIO.read(new File("D:/test/图片.png"));
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", outputStream);
            RedisUtil.getJedisClient().set(key, new BASE64Encoder().encode(outputStream.toByteArray()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getImageBase64(String key){

        try {
            String data = RedisUtil.getJedisClient().get(key);
            FileOutputStream fos = new FileOutputStream("D:/test/图片_copy_base64.png");
            fos.write(new BASE64Decoder().decodeBuffer(data));
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setData(String key, Object obj){

        try {
            byte[] data = object2Bytes(obj);

            RedisUtil.getJedisClient().set(key.getBytes(), data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object getData(String key){
        byte[] find = RedisUtil.getJedisClient().get(key.getBytes());
        return byte2Object(find);
    }

    /**
     * 序列化方法
     * @param value
     * @return
     */
    private static byte[] object2Bytes(Object value) {
        if (value == null)
            return null;
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(arrayOutputStream);
            outputStream.writeObject(value);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                arrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return arrayOutputStream.toByteArray();
    }

    /**
     * 反序列化方法
     * @param bytes
     * @return
     */
    private static Object byte2Object(byte[] bytes) {
        if (bytes == null || bytes.length == 0){
            return null;
        }
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));
            return inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    static class Student implements Serializable{
        private String name = "张三";
        private int age = 18;

        @Override
        public String toString() {
            return "name=" + name + ",age=" + age;
        }
    }

}
