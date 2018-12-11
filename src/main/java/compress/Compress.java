
package compress;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author Shuaijun He
 */

/// 对文件和字符串压缩及解压缩类
/// </summary>
public class Compress {   // 压缩
    public static String compress(String filePath) throws IOException {
        File file = new File(filePath);
        FileInputStream in = new FileInputStream(file);

        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(out);

        byte[] buffer = new byte[256];

        int n;
        while ((n = in.read(buffer)) >= 0) {
            outBuffer.write(buffer, 0, n);
        }
        System.out.println("原文件大小：" + file.length());
        gzip.write(outBuffer.toByteArray());
        gzip.flush();
        gzip.close();
        return out.toString("ISO-8859-1");
    }

    // 解压缩
    public static String uncompress(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return str;
        }
        System.out.println("压缩后：" + str.length());
        ByteArrayInputStream in = new ByteArrayInputStream(
            str.getBytes("ISO-8859-1"));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPInputStream gunzip = new GZIPInputStream(in);
        byte[] buffer = new byte[256];
        int n;
        while ((n = gunzip.read(buffer)) >= 0) {
            out.write(buffer, 0, n);
        }
        System.out.println("解压后：" + out.toString("ISO-8859-1").length());
        System.out.println("压缩比：" + (float) out.toString("ISO-8859-1").length()
            / str.length());
        // toString()使用平台默认编码，也可以显式的指定如toString("GBK")
        return out.toString("ISO-8859-1");
    }

    public static void main(String[] args) {
        try {
            String path = "C:/Users/admin/Pictures/";
            new FileOutputStream(path + "a123(2).png").write(Compress
                .uncompress(Compress.compress(path + "a123.png")).getBytes(
                    "ISO-8859-1"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}