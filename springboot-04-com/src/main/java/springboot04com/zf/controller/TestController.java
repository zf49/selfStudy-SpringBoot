package springboot04com.zf.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {



    @RequestMapping("/test")
    public String test1(){
        return "dashboard";
    }


}
