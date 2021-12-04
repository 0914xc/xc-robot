package cn.weixiaochen.robot;

import cn.weixiaochen.robot.spider.genshin.DailyNote;
import cn.weixiaochen.robot.spider.genshin.SignReward;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RobotApplicationTests {

    @Autowired
    DailyNote dailyNote;

    @Autowired
    SignReward signReward;

    @Test
    void contextLoads() {
    }

    @Test
    void testDailyNote() {
        dailyNote.execute();
    }

    @Test
    void testSignReward() {
        signReward.execute();
    }


}
