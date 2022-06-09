package com.springboot_test.service;


import com.springboot_test.entity.User;
import com.springboot_test.vo.UserAaVo;
import java.util.List;

/**
 * @ClassName: com.system.service.UserService
 * @Description:
 * @Author: lgrong
 * @CreateDate: 2020/3/25 23:24
 * @Version: 1.0
 */
public interface UserService {

    List<User> selAll();

    String TransactionalTest() ;

    void redisLock();

    List<UserAaVo> selList();

    String sk(String id);
}
