package com.zf.service;


import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

          // 只需要告诉 spring 这是一个异步方法
            @Async
           public void hello(){
               try {
                   Thread.sleep(3000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }

               System.out.println("数据正在处理");
           }



}
