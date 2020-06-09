package com.springboot_test.service.impl;

import com.springboot_test.dao.UserDao;
import com.springboot_test.entity.User;
import com.springboot_test.service.UserService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName: com.springboot_test.service.impl.UserServiceImpl
 * @Description:
 * @Author: lgrong
 * @CreateDate: 2020/6/5 17:58
 * @Version: 1.0
 */
@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;


    @Override
    public List<User> selAll() {
        List<User> list = userDao.selectAll();
        return list;
    }
}
