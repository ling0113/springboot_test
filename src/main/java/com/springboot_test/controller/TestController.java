package com.springboot_test.controller;

import com.springboot_test.service.TestService;
import com.springboot_test.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: com.springboot_test.controller.TestController
 * @Description:
 * @Author: linggr
 * @CreateDate: 2021/11/11 16:32
 * @Version: 1.0
 */

@RestController
@RequestMapping("/test")
@Api(tags = "hello模块")
public class TestController {

    @Autowired
    private TestService testService;

    /**
     * 测试分布式框架 redisson
     */
    @ApiOperation(value = "测试redisson")
    @GetMapping("/redisson")
    public void redisson() {
        try {
            testService.redisson();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

}
