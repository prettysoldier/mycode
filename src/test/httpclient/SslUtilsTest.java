/**
 * Copyright(c) 2011-2017 by YouCredit Inc.
 * All Rights Reserved
 */
package test.httpclient;

import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;

/**
 * @author Shuaijun He
 */
public class SslUtilsTest {
    public String getRequest(String url, int timeOut) throws Exception {
        URL u = new URL(url);
        if ("https".equalsIgnoreCase(u.getProtocol())) {
            SslUtils.ignoreSsl();
        }
        URLConnection conn = u.openConnection();
        conn.setConnectTimeout(timeOut);
        conn.setReadTimeout(timeOut);
        return IOUtils.toString(conn.getInputStream());
    }

    public String postRequest(String urlAddress, String args, int timeOut)
            throws Exception {
        URL url = new URL(urlAddress);
        if ("https".equalsIgnoreCase(url.getProtocol())) {
            SslUtils.ignoreSsl();
        }
        URLConnection u = url.openConnection();
        u.setDoInput(true);
        u.setDoOutput(true);
        u.setConnectTimeout(timeOut);
        u.setReadTimeout(timeOut);
        OutputStreamWriter osw = new OutputStreamWriter(u.getOutputStream(),
            "UTF-8");
        osw.write(args);
        osw.flush();
        osw.close();
        u.getOutputStream();
        return IOUtils.toString(u.getInputStream());
    }

    public static void main(String[] args) {
        try {
            SslUtilsTest st = new SslUtilsTest();
            String a = st.getRequest(
                "https://uatuat.cgnfex.com/third/api/user/login", 3000);
            System.out.println(a);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
