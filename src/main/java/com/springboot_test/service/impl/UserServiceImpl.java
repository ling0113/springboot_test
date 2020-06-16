package com.springboot_test.service.impl;

import com.springboot_test.dao.UserDao;
import com.springboot_test.entity.User;

import com.springboot_test.service.UserService;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @Override
    public List<User> selAll() {
        List<User> list = userDao.selectAll();
        return list;
    }

    /**
     * 测试事务
     */
    @Override
    public void TransactionalTest() throws Exception {
        String Str1 = UUID.randomUUID().toString().replace("-", "");
        int j = Str1.hashCode();
        String orderId = "aaaa";
        String s = UUID.randomUUID().toString();
        redisTemplate.opsForValue().setIfAbsent(orderId, s);
        User user = new User();
        user.setId(j);
        user.setUserName("事务1");
        user.setUserPhone("123456");
        user.setUserPassword("123456");
        user.setCreateTime(new Date());

        int i = userDao.insertSelective(user);
        System.out.println(i);
        int a = 1 / 0;
        System.out.println(i);

    }

    /**
     * 大致流程
     * 1.用setnx给我们的key加锁
     * 2.在设置过期时间
     * 3.加一个唯一id(防止业务处理时间大于过期时间)
     * 当查询不到信息显示系统繁忙重新发起请求.
     */
    @Override
    public synchronized void redis() {
        //订单id  前端所传参数
        String orderId = "54164613154631546";
        //每个线程的唯一id
        String s = UUID.randomUUID().toString();
        try {
            //执行命令 setnx()  //1.当key不存在的时候,直接赋值存入redis
                               //2.当key存在的时候,不做任何动作.
            Boolean ling = redisTemplate.opsForValue().setIfAbsent(orderId, s);
            //设置过期时间
            redisTemplate.expire(orderId,10, TimeUnit.SECONDS);

            //上面两条命令一起使用
            Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent(orderId, s, 10, TimeUnit.SECONDS);
            if (!aBoolean){
                System.out.println("系统繁忙");
            }

            /*
            //总库存
            int stock = (int) redisTemplate.opsForValue().get("stock");

            if (stock > 0) {
                //剩余库存
                int realStock = stock - 1;
                redisTemplate.opsForValue().set("stock", realStock);
                System.out.println("扣减成功,剩余库存:" + realStock);
            } else {
                System.out.println("扣减失败,库存不足");
            }*/
        }finally {
            //唯一id限制 防止锁永久失效
            Object o = redisTemplate.opsForValue().get(orderId);
            if (s.equals(o)){
                //解锁,删除锁
                redisTemplate.delete(orderId);
            }
        }

    }
}
