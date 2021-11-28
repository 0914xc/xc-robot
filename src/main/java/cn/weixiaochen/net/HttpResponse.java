package cn.weixiaochen.net;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author 魏小宸 2021/11/21
 */
public class HttpResponse {

    private final String response;

    public HttpResponse(String response) {
        this.response = response;
    }

    public JSONObject toJson() {
        return JSON.parseObject(response);
    }

    public String toString() {
        return this.response;
    }
}
