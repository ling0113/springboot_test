package com.springboot_test.controller;

import com.springboot_test.entity.User;
import com.springboot_test.service.UserService;
import com.sun.org.apache.regexp.internal.RE;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private UserService userService;

    @GetMapping("/a")
    public String a() {
        return "hello bbbbbbbbbbbbbb";
    }

    @GetMapping("/aa")
    public List<User>  aaa() {
        List<User> list = userService.selAll();
        return list;
    }



}