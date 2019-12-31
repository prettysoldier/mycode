package java_.util.zip.crc;

import java.util.zip.CRC32;

/**
 * CRC校验实用程序库 在数据存储和数据通讯领域，为了保证数据的正确，就不得不采用检错的手段。
 * 在诸多检错手段中，CRC是最著名的一种。CRC的全称是循环冗余校验。
 * 用于校验数据的完整性
 * @author Shuaijun He
 */
public class CRC32Test {

    public static void main(String[] args) {
        CRC32 crc32 = new CRC32();
        crc32.update("codding".getBytes());
        System.out.println(crc32.getValue());
        float a = 0f;
        System.out.println(a);
    }
}
