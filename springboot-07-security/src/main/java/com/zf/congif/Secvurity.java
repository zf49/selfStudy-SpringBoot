package com.zf.congif;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableWebSecurity
public class Secvurity extends WebSecurityConfigurerAdapter {
    // 授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       // 首页所有人可以访问
        //请求授权的规则
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")               .antMatchers("/level3/**").hasRole("vip3");

        //没有权限会默认跳到登陆页面,需要开启登录页面
//        http.formLogin();
         //定制
        http.formLogin().loginPage("/toLogin");

        // 注销
        http.logout().logoutSuccessUrl("/");

        http.rememberMe();

    }

     //认证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("123").password(new BCryptPasswordEncoder().encode("123")).roles("vip2","vip3")
                .and()
                .withUser("root").password(new BCryptPasswordEncoder().encode("123")).roles("vip1","vip2","vip3")
        .and()
        .withUser("guest").password(new BCryptPasswordEncoder().encode("123")).roles("vip1");


    }
}

