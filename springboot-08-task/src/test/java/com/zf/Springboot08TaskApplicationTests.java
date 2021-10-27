package com.zf;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class Springboot08TaskApplicationTests {


    @Autowired
    JavaMailSenderImpl mailSender;


    @Test
    void contextLoads() {

        // 一个简单的邮件发送
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("Chris 你好");     // 标题
        mailMessage.setText("测试一下使用邮件发送");   //内容
        mailMessage.setTo("zwan686@aucklanduni.ac.nz"); //发送给谁
        mailMessage.setFrom("164932606@qq.com");     // 谁发送的
        mailSender.send(mailMessage);

    }





    @Test
    void contextLoads2() throws MessagingException {
        // 在项目中放在controller 中
        // 复杂邮件的邮件发送
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // 组装
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);

        helper.setSubject("复杂邮件发送");
        helper.setText("<p style='color:red'>复杂邮件发送测试</p>",true);   // 正常是无法发送html标签的，如果参数中加入true,则可支持html
        // 附件

        helper.addAttachment("1.jpg",new File("/Users/chris/Desktop/1.jpg"));
        helper.addAttachment("2.jpg",new File("/Users/chris/Desktop/2.jpg"));

        helper.setTo("zwan686@aucklanduni.ac.nz");
        helper.setFrom("164932606@qq.com");

        mailSender.send(mimeMessage);

    }





}
