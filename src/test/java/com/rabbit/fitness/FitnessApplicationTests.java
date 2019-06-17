package com.rabbit.fitness;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FitnessApplicationTests {

    @Test
    public void contextLoads() {
    }
    @Test
    public void getOs(){
        String os = System.getProperty("os.name");
        System.out.println("操作系统名:"+os);
        if (os.contains("Win")){
            System.out.println("现在正在使用windows操作系统");
        }else{
            System.out.println("不是windows操作系统");
        }
    }

    @Test
    public void getIP() throws UnknownHostException {
        String ip=InetAddress.getLocalHost().getHostAddress();
        System.out.println("ip地址:"+ip);
    }

}
