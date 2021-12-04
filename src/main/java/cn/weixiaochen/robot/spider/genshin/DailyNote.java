package cn.weixiaochen.robot.spider.genshin;

import cn.weixiaochen.robot.spider.Spider;
import cn.weixiaochen.robot.utils.NetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.TreeMap;

/**
 * @author 魏小宸 2021/12/4
 */
@Component
public class DailyNote extends Spider {

    private final GenShinConfig genShinConfig;

    @Autowired
    public DailyNote(GenShinConfig genShinConfig) {
        this.genShinConfig = genShinConfig;
    }

    @Override
    public ResponseEntity<?> doExecute(RestTemplate restTemplate) {
        // 请求地址
        String url = "https://api-takumi.mihoyo.com/game_record/app/genshin/api/dailyNote";

        // 查询参数
        TreeMap<String, String> query = new TreeMap<>();
        query.put("role_id", genShinConfig.uuid());
        query.put("server", genShinConfig.server());

        // 请求头
        HttpHeaders headers = genShinConfig.cnHeaders();
        headers.add("DS", genShinConfig.cnSecret(NetUtils.contactQueryString(query), ""));

        return restTemplate.exchange(
                url + "?role_id={role_id}&server={server}",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                String.class,
                query
        );
    }
}
