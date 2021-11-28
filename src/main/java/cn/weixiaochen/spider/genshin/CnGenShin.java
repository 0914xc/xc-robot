package cn.weixiaochen.spider.genshin;

import cn.weixiaochen.net.HttpClient;
import cn.weixiaochen.net.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.DigestUtils;

import java.util.Random;

/**
 * @author 魏小宸 2021/11/28
 */
public class CnGenShin extends GenShin {

    public static final String API_CN_SALT = "xV8v4Qu54lUKrEYFZkJhB8cuOh9Asafs";
    public static final String API_CN_APP_VERSION = "2.17.1";

    static {
        header.put("x-rpc-app_version", API_CN_APP_VERSION);
    }
    /**
     * 原神每日提醒（树脂、日常、周本次数）
     */
    public JSONObject dailyNote() {
        HttpRequest request = new HttpRequest.Builder("https://api-takumi.mihoyo.com/game_record/app/genshin/api/dailyNote")
                .header(header)
                .query("role_id", uuid)
                .query("server", server)
                .build();
        request.header("DS", createCnSecret(request.getQuery()));

        return HttpClient.execute(request).toJson();
    }

    protected static String createCnSecret(String query) {
        return createCnSecret(query, "");
    }

    /**
     *
     * @param query get请求查询参数，要按照字母顺序排序
     * @param body
     * @return
     */
    protected static String createCnSecret(String query, String body) {
        long time = System.currentTimeMillis() / 1000;
        int random = new Random().nextInt(899999) + 100000;

        String salt = "salt=" + API_CN_SALT + "&t=" + time + "&r=" + random + "&b=" + body + "&q=" + query;
        String check = DigestUtils.md5DigestAsHex(salt.getBytes());

        return time + "," + random + "," + check;
    }
}
