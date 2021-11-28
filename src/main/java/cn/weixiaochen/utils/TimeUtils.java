package cn.weixiaochen.utils;

import java.util.Calendar;

/**
 * 日期工具类
 * @author 魏小宸 2021/11/28
 */
public class TimeUtils {

    public static final long ONE_DAY = 60 * 60 * 24 * 1000; // 一天（以毫秒秒为单位）

    /**
     * 计算到调度时间的延迟时间（以毫秒秒为单位）
     * @return
     */
    public static long calDelay(int hour, int minute) {
        Calendar calendar = Calendar.getInstance();

        // 获取当时时间
        long cur = calendar.getTimeInMillis();

        // 获取下次执行时间
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        long next = calendar.getTimeInMillis();

        // 如果当前时间已经过了定时调度时间，则再往后延迟一天
        if (cur > next) {
            calendar.add(Calendar.DATE, 1);
            next = calendar.getTimeInMillis();
        }
        return next - cur;
    }

}
