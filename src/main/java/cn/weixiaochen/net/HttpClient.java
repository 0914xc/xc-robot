package cn.weixiaochen.net;

import com.alibaba.fastjson.JSON;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.stream.Collectors;

/**
 * @author 魏小宸 2021/11/21
 */
public class HttpClient {

    public static HttpResponse execute(HttpRequest request) {
        try {
            return new HttpClient().doExecute(request);
        } catch (Exception e) {
            throw new NetException("请求" + request.getUrl() + "失败！");
        }
    }

    protected HttpResponse doExecute(HttpRequest request) throws Exception {
        // 获取connection
        HttpsURLConnection connection = getConnection(new URL(request.getUrl()));
        // 设置请求方式
        connection.setRequestMethod(request.getMethod());
        // 填充header
        request.getHeader().forEach(connection::setRequestProperty);
        // 如果是post请求，则需要写入body
        if (HttpRequest.METHOD_POST.equals(connection.getRequestMethod())) {
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.write(JSON.toJSONString(request.getBody()).getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            outputStream.close();
        }
        // 连接connection
        connection.connect();
        // 获取响应结果
        String response = "";
        if (HttpsURLConnection.HTTP_OK == connection.getResponseCode()) {
            InputStream inputStream = connection.getInputStream();
            response = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining());
        }
        return new HttpResponse(response);
    }

    protected HttpsURLConnection getConnection(URL url) {
        HttpsURLConnection connection = null;
        try {
            connection = (HttpsURLConnection) url.openConnection();
        } catch (IOException e) {
            System.out.println("获取connection失败！");
        }
        return connection;
    }

    static {
        try {
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, MyX509TrustManager.getMyX509TrustManager(), new SecureRandom());
            HostnameVerifier ignoreHostnameVerifier = (s, sslSession) -> true;
            HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier);
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        } catch (Exception e) {
            System.out.println("忽略SSL证书验证失败！");
        }
    }

    private static class MyX509TrustManager implements X509TrustManager {

        private static MyX509TrustManager[] myX509TrustManager;

        private MyX509TrustManager() {
        }

        public static MyX509TrustManager[] getMyX509TrustManager() {
            if (null == myX509TrustManager) {
                myX509TrustManager = new MyX509TrustManager[]{new MyX509TrustManager()};
            }
            return myX509TrustManager;
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
            // TODO Auto-generated method stub
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) {
            // TODO Auto-generated method stub
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            // TODO Auto-generated method stub
            return null;
        }
    }
}
