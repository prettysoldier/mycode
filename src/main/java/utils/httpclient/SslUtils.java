
package utils.httpclient;

import javax.net.ssl.*;
import java.security.cert.X509Certificate;

/**
 * @author Shuaijun He
 */
public class SslUtils {
    private static void trustAllHttpsCertificates() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[1];
        trustAllCerts[0] = new MyTrustManager();
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }

    static class MyTrustManager implements X509TrustManager {
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        /**
         * @param certs
         * @return
         */
        public boolean isServerTrusted(X509Certificate[] certs) {
            return true;
        }

        /**
         * @author Shuaijun He
         * @param certs
         * @return
         */
        public boolean isClientTrusted(X509Certificate[] certs) {
            return true;
        }

        @Override
        public void checkServerTrusted(X509Certificate[] certs, String authType) {
            return;
        }

        @Override
        public void checkClientTrusted(X509Certificate[] certs, String authType) {
            return;
        }
    }

    /**
     * 忽略HTTPS请求的SSL证书，必须在openConnection之前调用
     *
     * @throws Exception
     */
    public static void ignoreSsl() throws Exception {
        HostnameVerifier hv = (urlHostName, session) -> {
            System.out.println("Warning: URL Host: " + urlHostName + " vs. "
                + session.getPeerHost());
            return true;
        };
        SslUtils.trustAllHttpsCertificates();
        HttpsURLConnection.setDefaultHostnameVerifier(hv);
    }

}
