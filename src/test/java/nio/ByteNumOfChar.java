
package test.nio;

import java.io.UnsupportedEncodingException;

/**
 * 验证char是12个bit,
 *
 * @author Shuaijun He
 */
public class ByteNumOfChar {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "鷕";
        char x = '鷕';
        byte[] bytes = null;
        byte[] bytes1 = null;
        bytes = str.getBytes("utf-8");
        bytes1 = ByteNumOfChar.charToByte(x);
        System.out.println("bytes 大小：" + bytes.length);
        System.out.println("bytes1大小：" + bytes1.length);
        System.out.println("bytes2大小："
            + Character.valueOf(x).toString().getBytes("utf-8").length);

    }

    public static byte[] charToByte(char c) {
        byte[] b = new byte[2];
        b[0] = (byte) ((c & 0xFF00) >> 8);
        b[1] = (byte) (c & 0xFF);
        return b;
    }
}
