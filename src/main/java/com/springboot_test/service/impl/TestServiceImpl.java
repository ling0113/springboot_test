package com.springboot_test.service.impl;

import com.springboot_test.service.TestService;
import com.springboot_test.util.ResponseResult;
import com.springboot_test.utils.OssClientUtil;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: com.springboot_test.service.impl.TestServiceImpl
 * @Description:
 * @Author: lgrong
 * @CreateDate: 2020/7/30 15:32
 * @Version: 1.0
 */
@Service
@Slf4j
@Transactional
public class TestServiceImpl implements TestService {

    @Autowired
    private OssClientUtil ossClient3;



    @Override
    public ResponseResult uploadOss(MultipartFile file, String fileSource)  {
        String s = null;
        try {
            s = ossClient3.uploadFileToOSS(file);
        } catch (IOException e) {
            System.out.println(e);
        }
        String imgUrl = ossClient3.getImgUrl(s);
        return ResponseResult.success(imgUrl);
    }

    @Override
    public void redisson() throws IOException {
        Config config = new Config();
        config.useClusterServers()
                // use "rediss://" for SSL connection
                .addNodeAddress("perredis://115.28.138.45:6379");
        RedissonClient redisson = Redisson.create(config);
        System.out.println(redisson.getConfig().toJSON().toString());
    }

    public static void main(String[] args) throws IOException {
        Config config = new Config();
        /*config.useClusterServers()
                // use "rediss://" for SSL connection
                .addNodeAddress("perredis://115.28.138.45:6379");
        RedissonClient redisson = Redisson.create(config);
        try {
            System.out.println(redisson.getConfig().toJSON().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        config.useSingleServer().setAddress("redis://115.28.138.45:6379").setPassword("redispassword");
        RedissonClient redissonClient = Redisson.create(config);
        System.out.println(redissonClient.getConfig().toJSON().toString());
        RBucket<String> lcc = redissonClient.getBucket("lcc");
        System.out.println(lcc.get());

    }


}
