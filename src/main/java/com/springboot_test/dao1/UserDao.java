package com.springboot_test.dao1;

import com.springboot_test.entity.User;
import com.springboot_test.vo.UserAaVo;
import java.util.List;
import tk.mybatis.mapper.common.Mapper;

public interface UserDao extends Mapper<User> {

    List<UserAaVo> selList();

    String sk(String id);
}