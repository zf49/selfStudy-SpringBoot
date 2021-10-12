package com.zf.springboot05jdbc.JDBCController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class JDBCController {

    @Autowired
    JdbcTemplate jdbcTemplate;
    

    //  查询所有数据连接
    @GetMapping("/emp")
    public List<Map<String, Object>> userList(){

        String sql = "select * from Employee.EMPLOYEE";

        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);

        return maps;
    }




}
