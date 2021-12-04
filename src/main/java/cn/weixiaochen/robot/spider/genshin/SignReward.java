package cn.weixiaochen.robot.spider.genshin;

import cn.weixiaochen.robot.spider.Spider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 魏小宸 2021/12/4
 */
@Component
public class SignReward extends Spider {

    private final GenShinConfig genShinConfig;

    @Autowired
    public SignReward(GenShinConfig genShinConfig) {
        this.genShinConfig = genShinConfig;
    }

    @Override
    public ResponseEntity<?> doExecute(RestTemplate restTemplate) {
        String url = "https://api-takumi.mihoyo.com/event/bbs_sign_reward/sign";

        // 请求头
        HttpHeaders headers = genShinConfig.osHeaders();
        headers.add("DS", genShinConfig.osSecret());

        // 请求体 (HashMap会被转换成Json格式)
        Map<String, String> body = new HashMap<>();
        body.put("uid", genShinConfig.uuid());
        body.put("region", genShinConfig.server());
        body.put("act_id", "e202009291139501");

        return restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(body, headers),
                String.class
        );
    }
}
