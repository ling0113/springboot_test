package com.springboot_test.controller;

import com.springboot_test.entity.User;
import com.springboot_test.service.UserService;
import com.springboot_test.service.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: com.springboot_test.controller.UserController
 * @Description:
 * @Author: linggr
 * @CreateDate: 2021/12/16 10:59
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
@Api(tags = "User")
@Slf4j
public class UserController {
    @Autowired
    @Qualifier("usersServiceImpl1")
    private UsersService userService;

    /**
     * 测试mybatis
     */
    @ApiOperation(value = "测试mybatis")
    @GetMapping("/aa")
    public String aaa(@RequestParam("id") String id) {
        log.info("测试日志");

        System.out.println(id);
        //List<User> list = userService.selAll();
        String list2 = userService.selAll();
        return list2;
    }
}
