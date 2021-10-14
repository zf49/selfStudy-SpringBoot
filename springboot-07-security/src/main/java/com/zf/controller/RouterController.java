package com.zf.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class RouterController {


    @RequestMapping("/teste")
    @ResponseBody
    public String tetse(){
        return "qjkwhea wd";
    }


    @RequestMapping({"/","/index"})
   public String index(){
       return "index";
   }

   @RequestMapping("/toLogin")
   public String toLogin(){
        return "views/login";
   }

   

    @RequestMapping("/level1/{id}")
    public String l1(@PathVariable("id") int id){
      return "views/level1/"+id;
    }


    @RequestMapping("/level2/{id}")
    public String l2(@PathVariable("id") int id){
        return "views/level2"+id;
    }

    @RequestMapping("/level3/{id}")
    public String l3(@PathVariable("id") int id){
        return "views/level3/"+id;



    }



    

}
