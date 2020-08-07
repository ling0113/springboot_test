package com.springboot_test.controller;

import com.springboot_test.entity.User;
import com.springboot_test.service.UserService;
import com.sun.org.apache.regexp.internal.RE;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private UserService userService;

    /**
     * 测试mybatis
     * @return
     */
    @GetMapping("/aa")
    public List<User>  aaa() {
        List<User> list = userService.selAll();
        return list;
    }

    /**
     * 测试事务
     * @return
     */
    @GetMapping("/a")
    public String  a() {
          return   userService.TransactionalTest();

    }

    /**
     * 测试分布式锁
     * @return
     */
    @GetMapping("/redis")
    public void  redis() {
        try {
            userService.redis();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    /**
     * 测试分布式锁
     * @return
     */
    @PostMapping("/post")
    public String  post(@RequestParam("id") String id) {
        System.out.println(id);
        return id;
    }





}