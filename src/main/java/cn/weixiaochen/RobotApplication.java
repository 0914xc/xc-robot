package cn.weixiaochen;

import cn.weixiaochen.schedule.GenShinSignSchedule;
import cn.weixiaochen.utils.TimeUtils;
import cn.weixiaochen.utils.YamlReader;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.utils.BotConfiguration;

import java.util.Timer;


/**
 * @author 魏小宸 2021/11/1
 */
public class RobotApplication {

    public static void main(String[] args) {
        RobotApplication.run();
    }


    public static void run() {
        new RobotApplication().init();
    }

    private void init() {
        /* 1. 启动Robot */
        startRobot();
        /* 2. 启动定时调度任务 */
        startSchedule();
    }

    private void startRobot() {
        long qq = Long.parseLong(YamlReader.get("robot").get("qq"));
        String password = YamlReader.get("robot").get("password");

        Bot bot = BotFactory.INSTANCE.newBot(qq, password, new BotConfiguration() {{
            fileBasedDeviceInfo();
        }});

        bot.login();
    }

    private void startSchedule() {
        new Timer().scheduleAtFixedRate(new GenShinSignSchedule(), TimeUtils.calDelay(0, 10), TimeUtils.ONE_DAY);
    }
}
