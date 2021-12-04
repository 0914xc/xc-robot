package cn.weixiaochen.robot;

import cn.weixiaochen.robot.bot.Robot;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class RobotApplication implements CommandLineRunner {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(RobotApplication.class, args);
    }

    /**
     * 继承CommandLineRunner, 以非web方式运行Springboot
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        // 启动QQ机器人
        new Robot().login();
    }
}
