package com.springboot_test.service.impl;

import com.springboot_test.entity.User;
import com.springboot_test.service.UsersService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @ClassName: com.springboot_test.service.impl.UsersServiceImpl2
 * @Description:
 * @Author: linggr
 * @CreateDate: 2021/12/16 11:08
 * @Version: 1.0
 */
@Service("usersServiceImpl2")
public class UsersServiceImpl2 implements UsersService {

    @Override
    public String selAll() {
        return "123";
    }
}
