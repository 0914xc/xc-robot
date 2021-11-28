package cn.weixiaochen;

import cn.weixiaochen.net.HttpClient;
import cn.weixiaochen.net.HttpRequest;
import cn.weixiaochen.spider.genshin.CnGenShin;
import cn.weixiaochen.spider.genshin.OsGenShin;
import cn.weixiaochen.utils.TimeUtils;
import cn.weixiaochen.utils.YamlReader;
import org.junit.jupiter.api.Test;

/**
 * @author 魏小宸 2021/11/17
 */
public class UnitTest {

    @Test
    public void testHttp() {
        HttpRequest request = new HttpRequest.Builder("https://www.baidu.com/").build();
        String response = HttpClient.execute(request).toString();
        System.out.println(response);
    }

    @Test
    public void testTimeUtils() {
        System.out.println(TimeUtils.calDelay(21, 11));
    }

    @Test
    public void testYamlUtils() {
        System.out.println(YamlReader.get("robot").get("password"));
    }

    @Test
    public void testGenDailyNote() {
        System.out.println(new CnGenShin().dailyNote());
    }

    @Test
    public void testSignInfo() {
        System.out.println(new OsGenShin().signInfo());
    }

    @Test
    public void testSign() {
        System.out.println(new OsGenShin().sign());
    }

    @Test
    public void testApplication() {
        RobotApplication.run();
    }
}
