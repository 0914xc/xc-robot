package cn.weixiaochen.schedule;

import cn.weixiaochen.spider.genshin.OsGenShin;
import com.alibaba.fastjson.JSONObject;
import net.mamoe.mirai.Bot;

import java.util.TimerTask;

/**
 * 原神自动签到定时调度：每日凌晨过10分
 * @author 魏小宸 2021/11/28
 */
public class GenShinSignSchedule extends TimerTask {

    @Override
    public void run() {
        JSONObject signResult = new OsGenShin().sign();
        Bot.getInstance(327084752).getFriend(1020361621).sendMessage(signResult.getString("message"));
    }
}
