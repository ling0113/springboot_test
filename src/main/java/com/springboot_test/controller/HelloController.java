package com.springboot_test.controller;

import com.springboot_test.dao1.UserDao;
import com.springboot_test.dao2.NoteDao;
import com.springboot_test.entity.Note;
import com.springboot_test.entity.User;
import com.springboot_test.service.NoteService;
import com.springboot_test.service.UserService;
import com.springboot_test.vo.UserAaVo;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/hello")
@Api(tags = "hello模块")
public class HelloController {

    @Autowired
    private UserService userService;

    @Autowired
    private NoteService noteService;

    @Autowired
    private NoteDao noteDao;


    private UserDao userDao;

    /**
     * 测试mybatis
     */
    @ApiOperation(value = "测试mybatis")
    @GetMapping("/aa")
    public List<User> aaa(@RequestParam("id") String id) {
        log.info("测试日志");

        System.out.println(id);
        //List<User> list = userService.selAll();
        List<User> list2 = userService.selAll();
        return list2;
    }

    @ApiOperation(value = "测试mybatis2")
    @GetMapping("/aa2")
    public String aaa2(@RequestParam("id") String id) {
        System.out.println(id);
        //List<User> list = userService.selAll();
        String a = userService.sk(id);
        return a;
    }

    /**
     * 测试事务
     */
    @ApiOperation(value = "测试事务")
    @GetMapping("/testTransaction")
    public String a() {
        return userService.TransactionalTest();

    }

    /**
     * 测试分布式锁
     */
    @ApiOperation(value = "测试分布式锁")
    @GetMapping("/redisLock")
    public void redisLock() {
        try {
            userService.redisLock();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    /**
     * 测试多数据源及其事务
     */
    @ApiOperation(value = "测试多数据源及其事务")
    @PostMapping("/majorityDataSource")
    @Transactional(value = "data1TransactionManager")
    public String majorityDataSource(@RequestParam(value = "id",required = false) String id) {
        System.out.println(id+5);
        Note note = new Note();
        note.setId(1);
        note.setTitle("22attt2");
        note.setBody("3");
        noteDao.updateByPrimaryKeySelective(note);

        aa();
        return id;
    }
    @Transactional(value = "data2TransactionManager")
    public void aa() {
        //User user = new User();
        //user.setId("99aaaaa");
        //user.setUserName("张飞");
        //user.setCreateTime(new Date());
        //userDao.insertSelective(user);
        //List<User> x = userDao.selectAll();
        //System.out.println(x+"11111");
        Note note = new Note();
        note.setId(8877);
        note.setTitle("22aaa22");
        note.setBody("3");
        noteDao.insertSelective(note);
        int a=1/0;
    }
}





