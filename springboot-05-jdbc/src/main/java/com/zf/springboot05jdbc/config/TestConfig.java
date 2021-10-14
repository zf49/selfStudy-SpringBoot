package com.zf.springboot05jdbc.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.apache.catalina.manager.StatusManagerServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
public class TestConfig {



     @ConfigurationProperties(prefix = "spring.datasource")
     @Bean
      public DataSource druidDataSource(){
         return new DruidDataSource();
      }

      

//      // 后台监控
//    @Bean
//    public ServletRegistrationBean statViewServlet(){
//        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
//
//      // 后台需要有人登录
//        HashMap<String, String> initParameters = new HashMap<>();
//
//        //增加配置
//        initParameters.put("loginUsername","admin");  //登陆的key是固定的loginUsername
//        initParameters.put("loginPassword","123456");
//
//        //允许谁能访问
//        initParameters.put("allow","");
//
//        //禁止谁能访问
////        initParameters.put("zf","192.168.0.1");
//
//        bean.setInitParameters(initParameters);
//        return bean;
//    }
//
//
//    //filter
//      @Bean
//    public FilterRegistrationBean webStatFilter(){
//
//        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
//         bean.setFilter(new WebStatFilter());
//
//        HashMap<String, String> map = new HashMap<>();
//
//        //这些东西，不进行统计
//         map.put("exclusions","*.js,*.css,/druid/**");
//
//         bean.setInitParameters(map);
//
//        return bean;
//
//    }
//
//
//


}
