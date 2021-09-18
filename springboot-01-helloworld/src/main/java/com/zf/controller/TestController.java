package com.zf.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@Controller
public class TestController {

    @RequestMapping("/hello")
    @ResponseBody
    public String testController(){
           return "测试：Helloworld";
    }
    


}
