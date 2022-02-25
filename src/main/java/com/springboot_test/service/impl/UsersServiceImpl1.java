package com.springboot_test.service.impl;

import com.springboot_test.entity.User;
import com.springboot_test.service.UsersService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @ClassName: com.springboot_test.service.impl.UsersServiceImpl1
 * @Description:
 * @Author: linggr
 * @CreateDate: 2021/12/16 11:08
 * @Version: 1.0
 */
@Service("usersServiceImpl1")
public class UsersServiceImpl1 implements UsersService {

    @Override
    public String selAll() {
        if ("123".equals("123")) {
            System.out.println(123);
        }
        return "456";
    }
}
