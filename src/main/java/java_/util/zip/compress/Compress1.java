
package java_.util.zip.compress;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author Shuaijun He
 */

/// 对文件和字符串压缩及解压缩类
/// </summary>
public class Compress1 {
    public static void main(String[] args) {
        try {
            String str = "asdfasdf31452345sdfgsasdffhgujjfgbnmfghjkkghjmjk56145615s6df4gsfdgsfdhdfhb1d56fg4hddfghdfgh4s5fd4g6s";

            String comp = Compress1.compress(str);
            System.out.println("压缩前：" + str.length());
            System.out.println("压缩后：" + comp.length());
            String umComp = Compress1.uncompress(comp);
            System.out.println("压缩后" + umComp.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 压缩
    public static String compress(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return str;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(out);
        gzip.write(str.getBytes());
        gzip.close();
        return out.toString("ISO-8859-1");
    }

    // 解压缩
    public static String uncompress(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return str;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(
            str.getBytes("ISO-8859-1"));
        GZIPInputStream gunzip = new GZIPInputStream(in);
        byte[] buffer = new byte[256];
        int n;
        while ((n = gunzip.read(buffer)) >= 0) {
            out.write(buffer, 0, n);
        }
        // toString()使用平台默认编码，也可以显式的指定如toString("GBK")
        return out.toString();
    }
}