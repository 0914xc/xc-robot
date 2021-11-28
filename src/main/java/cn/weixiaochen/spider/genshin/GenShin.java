package cn.weixiaochen.spider.genshin;

import cn.weixiaochen.utils.YamlReader;

import java.util.*;

/**
 * 原神爬取接口
 *
 * @author 魏小宸 2021/11/13
 */
public abstract class GenShin {

    public static final String API_CLIENT_TYPE = "5";

    /** 请求头 */
    public static final Map<String, String> header = new HashMap<>();
    static {
        header.put("Host", "api-takumi.mihoyo.com");
        header.put("Origin", "https://webstatic.mihoyo.com");
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) miHoYoBBS/2.10.1");
        header.put("Referer", "https://webstatic.mihoyo.com/");
        header.put("X-Requested-With", "com.mihoyo.hyperion");
        header.put("x-rpc-client_type", API_CLIENT_TYPE);
    }

    protected final String uuid;
    protected final String server;

    public GenShin() {
        this.uuid = YamlReader.get("genshin").get("uuid");
        this.server = YamlReader.get("genshin").get("server");
        header.put("Cookie", YamlReader.get("genshin").get("cookie"));
    }


}



