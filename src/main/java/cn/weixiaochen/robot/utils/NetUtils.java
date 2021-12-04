package cn.weixiaochen.robot.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 魏小宸 2021/12/4
 */
public class NetUtils {

    public static String contactQueryString(Map<String, String> params) {
        List<String> contactedStrList = new ArrayList<>();
        params.forEach((key, value) -> contactedStrList.add(key + "=" + value));
        return String.join("&", contactedStrList);
    }
}