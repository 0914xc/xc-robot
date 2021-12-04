package cn.weixiaochen.robot.spider.genshin;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.util.Random;
import java.util.UUID;

/**
 * 原神配置类，提供常用请求参数
 * @author 魏小宸 2021/11/13
 */
@Component
public class GenShinConfig {

    public static final String API_CN_SALT = "xV8v4Qu54lUKrEYFZkJhB8cuOh9Asafs";
    public static final String API_CN_APP_VERSION = "2.17.1";

    public static final String API_OS_SALT = "4a8knnbk5pbjqsrudp3dq484m9axoc5g";
    public static final String API_OS_APP_VERSION = "2.10.1";

    protected String uuid() {
        return "159459946";
    }

    protected String server() {
        return "cn_gf01";
    }

    protected HttpHeaders cnHeaders() {
        HttpHeaders headers = headers();
        headers.add("x-rpc-app_version", API_CN_APP_VERSION);
        return headers;
    }


    protected HttpHeaders osHeaders() {
        HttpHeaders headers = headers();
        headers.add("x-rpc-app_version", API_OS_APP_VERSION);
        headers.add("x-rpc-device_id", UUID.randomUUID().toString());
        return headers;
    }

    private HttpHeaders headers() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Host", "api-takumi.mihoyo.com");
        headers.add("Origin", "https://webstatic.mihoyo.com");
        headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) miHoYoBBS/2.10.1");
        headers.add("Referer", "https://webstatic.mihoyo.com/");
        headers.add("X-Requested-With", "com.mihoyo.hyperion");
        headers.add("x-rpc-client_type", "5");
        headers.add("Cookie", "_ga=GA1.2.1161788630.1635204915; _gat=1; _gid=GA1.2.2123403113.1638021000; ltoken=l4GfMTtUxpCaP4L5qNSYlbgWjvdGcdPA54XNbFJ3; ltuid=262562718; account_id=262562718; cookie_token=Qtn1BWLOgwC0uZ94oerLDr1hRdj4hbweUH9jGXOq; login_ticket=Nf6byD0AgHtaaE0GgqYxH585f1Pq1tiqm4gWR6mV; aliyungf_tc=2b0e0e4cf9b5ab152f4b2be8e1021a76947af8f579768655eba3f78af5177d5f; _ga_KJ6J9V9VZQ=GS1.1.1637925060.18.0.1637925060.0; _MHYUUID=1a42ed9c-ba12-44cf-825b-fc25ff936004; _ga_CXN1FSHKS4=GS1.1.1637511094.1.1.1637511279.0; mi18nLang=zh-cn; _ga_HKTGWLY8PN=GS1.1.1637511065.2.1.1637511076.0; mihoyo-survey-4154=1");
        return headers;
    }

    /**
     *
     * @param query get请求查询参数，要按照字母顺序排序
     * @param body
     * @return
     */
    protected String cnSecret(String query, String body) {
        long time = System.currentTimeMillis() / 1000;
        int random = new Random().nextInt(899999) + 100000;

        String salt = "salt=" + API_CN_SALT + "&t=" + time + "&r=" + random + "&b=" + body + "&q=" + query;
        String check = DigestUtils.md5DigestAsHex(salt.getBytes());

        return time + "," + random + "," + check;
    }


    protected String osSecret() {
        long time = System.currentTimeMillis() / 1000;
        int random = new Random().nextInt(899999) + 100000;

        String salt = "salt=" + API_OS_SALT + "&t=" + time + "&r=" + random;
        String check = DigestUtils.md5DigestAsHex(salt.getBytes());

        return time + "," + random + "," + check;
    }
}



