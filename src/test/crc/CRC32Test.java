/**
 * Copyright(c) 2011-2017 by YouCredit Inc.
 * All Rights Reserved
 */
package test.crc;

import java.util.zip.CRC32;

/**
 * @author Shuaijun He
 */
public class CRC32Test {

    public static void main(String[] args) {
        CRC32 crc32 = new CRC32();
        crc32.update("codding".getBytes());
        System.out.println(crc32.getValue());
    }
}
