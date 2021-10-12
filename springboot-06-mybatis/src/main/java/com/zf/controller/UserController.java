package com.zf.controller;


import com.zf.mapper.UserMapper;
import com.zf.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {


    
    @Autowired
     private UserMapper userMapper;
    

    @GetMapping("/allUser")
    public List<User> allUser(){
        List<User> allUser = userMapper.getAllUser();

        return allUser;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") int id){
        User userById = userMapper.getUserById(id);
        return userById;
    }





}

