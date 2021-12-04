package cn.weixiaochen.robot.schedule;


import cn.weixiaochen.robot.spider.genshin.SignReward;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 原神定时任务
 * @author 魏小宸 2021/11/28
 */
@Component
public class GenShinSchedule {

    private final SignReward signReward;

    @Autowired
    public GenShinSchedule(SignReward signReward) {
        this.signReward = signReward;
    }

    /**
     * 每天早上7点，米游社原神签到
     */
    @Scheduled(cron = "0 0 7 * * ?")
    public void signRewardTask() {
        Object body = signReward.execute().getBody();
        if (null != body) {
            Friend friend = Bot.getInstance(327084752).getFriend(1020361621);
            if (null != friend) {
                friend.sendMessage(body.toString());
            }
        } else {
        }
    }
}
