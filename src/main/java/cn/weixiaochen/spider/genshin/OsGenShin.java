package cn.weixiaochen.spider.genshin;

import cn.weixiaochen.net.HttpClient;
import cn.weixiaochen.net.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.DigestUtils;

import java.util.Random;
import java.util.UUID;

/**
 * @author 魏小宸 2021/11/28
 */
public class OsGenShin extends GenShin{

    public static final String API_OS_SALT = "4a8knnbk5pbjqsrudp3dq484m9axoc5g";
    public static final String API_OS_APP_VERSION = "2.10.1";
    public static final String SIGN_ACT_ID = "e202009291139501";

    static {
        header.put("x-rpc-app_version", API_OS_APP_VERSION);
        header.put("x-rpc-device_id", UUID.randomUUID().toString());
    }

    /**
     * 米游社原神每日签到信息
     */
    public JSONObject signInfo() {
        HttpRequest request = new HttpRequest.Builder("https://api-takumi.mihoyo.com/event/bbs_sign_reward/info")
                .header(header)
                .query("act_id", SIGN_ACT_ID)
                .query("region", server)
                .query("uid", uuid)
                .build();

        return HttpClient.execute(request).toJson();

    }

    public JSONObject sign() {
        HttpRequest request = new HttpRequest.Builder("https://api-takumi.mihoyo.com/event/bbs_sign_reward/sign")
                .header(header)
                .header("DS", createOsSecret())
                .body("act_id", SIGN_ACT_ID)
                .body("region", server)
                .body("uid", uuid)
                .build();

        return HttpClient.execute(request).toJson();
    }

    protected static String createOsSecret() {
        long time = System.currentTimeMillis() / 1000;
        int random = new Random().nextInt(899999) + 100000;

        String salt = "salt=" + API_OS_SALT + "&t=" + time + "&r=" + random;
        String check = DigestUtils.md5DigestAsHex(salt.getBytes());

        return time + "," + random + "," + check;
    }
}
