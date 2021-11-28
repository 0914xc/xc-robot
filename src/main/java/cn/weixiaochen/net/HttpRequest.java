package cn.weixiaochen.net;

import lombok.Data;

import java.util.*;

/**
 * @author 魏小宸 2021/11/21
 */
@Data
public class HttpRequest {

    public static final String METHOD_POST = "POST";
    public static final String METHOD_GET = "GET";

    private String url;
    private Map<String, String> header;
    private Map<String, String> query;
    private Map<String, String> body;

    private HttpRequest(Builder builder) {
        this.url = builder.url;
        this.header = builder.header;
        this.query = builder.query;
        this.body = builder.body;
    }

    public String getMethod() {
        // 判断请求方式：如果存在body，则视为post请求
        if (!this.body.isEmpty()) {
            return METHOD_POST;
        }
        // 否则，视为get请求
        return METHOD_GET;
    }

    public String getUrl() {
        // 如果是get请求，则需要拼接上查询字符串
        if (METHOD_GET.equals(getMethod())) {
            return this.url + "?" + getQuery();
        }
        return this.url;
    }

    public String getQuery() {
        List<String> params = new ArrayList<>();
        // 如果是get请求，则需要拼接上查询字符串
        query.forEach((key, value) -> params.add(key + "=" + value));
        return String.join("&", params);
    }

    public void header(String key, String value) {
        this.header.put(key, value);
    }

    public static class Builder {

        private final String url;
        private final Map<String, String> header;
        private final Map<String, String> query;
        private final Map<String, String> body;

        public Builder(String url) {
            this.url = url;
            this.header = new HashMap<>();
            this.query = new LinkedHashMap<>();
            this.body = new LinkedHashMap<>();
        }

        public Builder header(String key, String value) {
            this.header.put(key, value);
            return this;
        }

        public Builder header(Map<String, String> header) {
            this.header.putAll(header);
            return this;
        }

        public Builder query(String key, String value) {
            this.query.put(key, value);
            return this;
        }

        public Builder query(Map<String, String> query) {
            this.query.putAll(query);
            return this;
        }

        public Builder body(String key, String value) {
            this.body.put(key, value);
            return this;
        }

        public Builder body(Map<String, String> body) {
            this.body.putAll(body);
            return this;
        }

        public HttpRequest build() {
            return new HttpRequest(this);
        }
    }
}
