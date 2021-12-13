package com.springboot_test.test;

import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedissonConfigCase {
    /**
     * 单Redis节点模式
     */
    @Test
    public void singl() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://115.28.138.45:6379")
                .setPassword("redispassword")
                .setConnectionPoolSize(5)//连接池大小
                .setConnectionMinimumIdleSize(2);//最小空闲连接数
                //.setDatabase(1);
        RedissonClient client = Redisson.create(config);
        RBucket<String> name = client.getBucket("name");
        //name.set("123");
        System.out.println(name.get());
    }

    /**
     * 主从模式
     */
    @Test
    public void masterSlave() {
        Config config = new Config();
        config.useMasterSlaveServers()
                .setMasterAddress("redis://10.49.196.20:6379")
                .addSlaveAddress("redis://10.49.196.21:6379")
                .addSlaveAddress("redis://10.49.196.22:6379")
                .setPassword("123456")
                .setMasterConnectionPoolSize(5)//主节点连接池大小
                .setMasterConnectionMinimumIdleSize(2)//主节点最小空闲连接数
                .setSlaveConnectionPoolSize(5)//从节点连接池大小
                .setSlaveConnectionMinimumIdleSize(2)//从节点最小空闲连接数
                .setDatabase(0);
        RedissonClient client = Redisson.create(config);
        RBucket<String> name = client.getBucket("name");
        System.out.println(name.get());
    }

    /**
     * 哨兵模式
     */
    @Test
    public void sentinel() {
        Config config = new Config();
        config.useSentinelServers()
                .setMasterName("mymaster")
                .addSentinelAddress("redis://10.49.196.20:26379")
                .addSentinelAddress("redis://10.49.196.21:26379")
                .addSentinelAddress("redis://10.49.196.22:26379")
                .setPassword("123456")
                .setMasterConnectionPoolSize(5)//主节点连接池大小
                .setMasterConnectionMinimumIdleSize(3)//主节点最小空闲连接数
                .setSlaveConnectionPoolSize(5)//从节点连接池大小
                .setSlaveConnectionMinimumIdleSize(3)//从节点最小空闲连接数
                .setDatabase(0);
        System.out.println(config.useSentinelServers().getSentinelAddresses());

        RedissonClient client = Redisson.create(config);
        RBucket<String> name = client.getBucket("name");
        name.set("赵云");
        System.out.println(name.get());
    }

    /**
     * 集群
     */
    @Test
    public void cluster() {
        Config config = new Config();
        config.useClusterServers()
                .setScanInterval(1000 * 2)
                .addNodeAddress("redis://10.49.196.20:7000", "redis://10.49.196.20:7001")
                .addNodeAddress("redis://10.49.196.21:7000", "redis://10.49.196.21:7001")
                .addNodeAddress("redis://10.49.196.22:7000", "redis://10.49.196.22:7001")
                .setPassword("123456")
                .setMasterConnectionPoolSize(5)//主节点连接池大小
                .setMasterConnectionMinimumIdleSize(2)//主节点最小空闲连接数
                .setSlaveConnectionPoolSize(5)//从节点连接池大小
                .setSlaveConnectionMinimumIdleSize(2);//从节点最小空闲连接数
        RedissonClient client = Redisson.create(config);
        RBucket<String> name = client.getBucket("name");
        name.set("赵云");
        System.out.println(name.get());
    }
}