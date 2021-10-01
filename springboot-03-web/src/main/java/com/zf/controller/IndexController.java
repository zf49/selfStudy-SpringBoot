package com.zf.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;


// 在templete目录下的所有页面，只能通过controller来跳转
//需要模版引擎的支持
@Controller
public class IndexController {

    @RequestMapping("/test")
    public String index(Model model){
        model.addAttribute("msg","<h1>6666</h1>");

        model.addAttribute("users", Arrays.asList("111","222","333"));
        
        return "test";
    }


}
