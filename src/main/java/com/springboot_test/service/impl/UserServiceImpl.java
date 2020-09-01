package com.springboot_test.service.impl;

import com.springboot_test.dao1.UserDao;
import com.springboot_test.entity.User;

import com.springboot_test.service.UserService;
import com.springboot_test.util.SnowflakeUtil;
import com.springboot_test.vo.UserAaVo;
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
    public String TransactionalTest() {
        String Str1 = UUID.randomUUID().toString().replace("-", "");
        int j = Str1.hashCode();
        String orderId = "aaaa";
        String s = UUID.randomUUID().toString();

        //redisTemplate.opsForValue().setIfAbsent(orderId, s);
        User user = new User();
        user.setId(SnowflakeUtil.getSnowflakeId());
        user.setUserName("事务9999");
        user.setUserPhone("8888");
        user.setUserPassword("99999");
        user.setCreateTime(new Date());

        int i = userDao.insertSelective(user);
            a();
        return orderId;
        //throw new GlobalException("100", "事务回滚");

    }
    @Transactional
    public void a() {

        User user = new User();
        user.setId(SnowflakeUtil.getSnowflakeId());
        user.setUserName("事务9999");
        user.setUserPhone("8888");
        user.setUserPassword("99999");
        user.setCreateTime(new Date());

        int i = userDao.insertSelective(user);
        int m = 1/0;
    }

    /**
     * 大致流程
     * 1.用setnx给我们的key加锁
     * 2.在设置过期时间
     * 3.加一个唯一id(防止业务处理时间大于过期时间)
     * 当查询不到信息显示系统繁忙重新发起请求.
     */
    @Override
    public  void redisLock() {
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

    /**
     * 测试mybatis  一对多 在同一个对象
     * @return
     */
    @Override
    public List<UserAaVo> selList() {
        List<UserAaVo> list = userDao.selList();
        return list;
    }
}
