package cn.weixiaochen.robot.spider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author 魏小宸 2021/12/4
 */
@Component
public abstract class Spider {

    RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<?> execute() {
        return doExecute(this.restTemplate);
    }

    public abstract ResponseEntity<?> doExecute(RestTemplate restTemplate);
}
