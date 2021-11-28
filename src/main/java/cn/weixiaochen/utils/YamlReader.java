package cn.weixiaochen.utils;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 读取Yaml的工具类
 * @author 魏小宸 2021/11/28
 */
public class YamlReader {

    public static Map<String, Map<String, Object>> config;

    static {
        InputStream inputStream = YamlReader.class.getClassLoader().getResourceAsStream("application.yml");
        config = new Yaml().load(inputStream);
    }

    public static Map<String, String> get(String key) {
        Map<String, String> params = new HashMap<>();
        config.get(key).forEach((index, value) -> {
            params.put(index, String.valueOf(value));
        });
        return params;
    }

}
