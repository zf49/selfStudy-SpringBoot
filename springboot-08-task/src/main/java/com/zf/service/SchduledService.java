package com.zf.service;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SchduledService {

    
     // 在一个特定的时间去执行这个方法
    // cron 表达式 百度搜生成器
    // 秒 分 时 日 月 礼拜几

    

    @Scheduled(cron = "50 24 23 * * ?")
     public void hello(){
         System.out.println("SchduledService被执行了");
     }



}
