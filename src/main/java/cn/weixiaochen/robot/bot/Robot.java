package cn.weixiaochen.robot.bot;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.utils.BotConfiguration;

/**
 * @author 魏小宸 2021/12/4
 */
public class Robot {

    public void login() {
        Bot bot = BotFactory.INSTANCE.newBot(327084752, "wxc0914..", new BotConfiguration() {{
            fileBasedDeviceInfo("cache/device.json");
        }});

        bot.login();
    }
}
