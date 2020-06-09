package com.springboot_test.util;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class RedisUtil {

    private static final Log logger = LogFactory.get(RedisUtil.class);

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 写入缓存
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            logger.error(e);
        }
        closeConnection(redisTemplate);
        return result;
    }

    /**
     * 写入缓存设置时效时间
     */
    public boolean set(final String key, Object value, Integer expireTime, TimeUnit data) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, data);
            result = true;
        } catch (Exception e) {
            logger.error(e);
        }
        closeConnection(redisTemplate);
        return result;
    }

    /**
     * 批量删除对应的value
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0) {
            redisTemplate.delete(keys);
        }
        closeConnection(redisTemplate);
    }

    /**
     * 删除对应的value
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
        closeConnection(redisTemplate);
    }

    /**
     * 判断缓存中是否有对应的value
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     */
    public Object get(final String key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        closeConnection(redisTemplate);
        return result;
    }

    public Set getKeys(String pattern) {
        Set set = redisTemplate.keys(pattern);
        closeConnection(redisTemplate);
        return set;
        // return stringRedisTemplate.keys("?" + pattern);
        // return stringRedisTemplate.keys("[" + pattern + "]");
    }

    /**
     * 哈希 添加
     */
    public void hmSet(String key, Object hashKey, Object value, Integer expireTime, TimeUnit data) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put(key, hashKey, value);
        redisTemplate.expire(key, expireTime, data);
        closeConnection(redisTemplate);
    }

    /**
     * 绑定哈希 添加
     */
    public void bSet(String key, String clientId, Object value) {
        redisTemplate.boundHashOps(key).put(clientId, value);
        closeConnection(redisTemplate);
    }

    /**
     * 哈希获取数据
     */
    public Object hmGet(String key, Object hashKey) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        Object object = hash.get(key, hashKey);
        closeConnection(redisTemplate);
        return object;
    }

    /**
     * 绑定哈希获取数据
     */
    public Object bGet(String key, String clientId) {
        Object object = redisTemplate.boundHashOps(key).get(clientId);
        closeConnection(redisTemplate);
        return object;
    }

    /**
     * 列表添加
     */
    public void lPush(String k, Object v) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.rightPush(k, v);
        closeConnection(redisTemplate);
    }

    /**
     * 列表获取
     */
    public List<Object> lRange(String k, long l, long l1) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        List<Object> list1 = list.range(k, l, l1);
        closeConnection(redisTemplate);
        return list1;
    }

    /**
     * 集合添加
     */
    public void add(String key, Object value) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        set.add(key, value);
        closeConnection(redisTemplate);
    }

    /**
     * 集合获取
     */
    public Set<Object> setMembers(String key) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        Set<Object> set1 = set.members(key);
        closeConnection(redisTemplate);
        return set1;
    }

    /**
     * 有序集合添加
     */
    public void zAdd(String key, Object value, double scoure) {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        zset.add(key, value, scoure);
        closeConnection(redisTemplate);
    }

    /**
     * 有序集合添加
     */
    public Long zcout(String key, double min, double max) {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        long count = zset.count(key, min, max);
        closeConnection(redisTemplate);
        return count;
    }

    /**
     * 有序集合获取
     */
    public Set<Object> rangeByScore(String key, double scoure, double scoure1) {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        Set<Object> set = zset.rangeByScore(key, scoure, scoure1);
        closeConnection(redisTemplate);
        return set;
    }

    public boolean expire(String key, long time, TimeUnit data) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, data);
            }
            closeConnection(redisTemplate);
            return true;
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public long getExpire(String key) {
        long time = redisTemplate.getExpire(key, TimeUnit.SECONDS);
        closeConnection(redisTemplate);
        return time;
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean hasKey(String key) {
        boolean flag = false;
        try {
            flag = redisTemplate.hasKey(key);
        } catch (Exception e) {
            logger.error(e);
        }
        closeConnection(redisTemplate);
        return flag;
    }

    public void bdel(String key, String clientId) {
        redisTemplate.boundHashOps(key).delete(clientId);
        closeConnection(redisTemplate);
    }

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
            closeConnection(redisTemplate);
        }
    }
    //============================String=============================

    /**
     * 普通缓存放入并设置时间
     *
     * @param key 键
     * @param value 值
     * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            closeConnection(redisTemplate);
            return true;
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
    }

    /**
     * 递增
     *
     * @param key 键
     */
    public long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        long count = redisTemplate.opsForValue().increment(key, delta);
        closeConnection(redisTemplate);
        return count;
    }

    /**
     * 递减
     *
     * @param key 键
     */
    public long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        long count = redisTemplate.opsForValue().increment(key, -delta);
        closeConnection(redisTemplate);
        return count;
    }
    //================================Map=================================

    /**
     * HashGet
     *
     * @param key 键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public Object hget(String key, String item) {
        Object object = redisTemplate.opsForHash().get(key, item);
        closeConnection(redisTemplate);
        return object;
    }

    /**
     * 获取hashKey对应的所有键值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<Object, Object> hmget(String key) {
        Map<Object, Object> map = redisTemplate.opsForHash().entries(key);
        closeConnection(redisTemplate);
        return map;
    }

    /**
     * HashSet
     *
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    public boolean hmset(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            closeConnection(redisTemplate);
            return true;
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
    }

    /**
     * HashSet 并设置时间
     *
     * @param key 键
     * @param map 对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    public boolean hmset(String key, Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time, TimeUnit.SECONDS);
            }
            closeConnection(redisTemplate);
            return true;
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key 键
     * @param item 项
     * @param value 值
     * @return true 成功 false失败
     */
    public boolean hset(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            closeConnection(redisTemplate);
            return true;
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key 键
     * @param item 项
     * @param value 值
     * @param time 时间(秒)  注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public boolean hset(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time, TimeUnit.SECONDS);
            }
            closeConnection(redisTemplate);
            return true;
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
    }

    /**
     * 删除hash表中的值
     *
     * @param key 键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
        closeConnection(redisTemplate);
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key 键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public boolean hHasKey(String key, String item) {
        boolean flag = redisTemplate.opsForHash().hasKey(key, item);
        closeConnection(redisTemplate);
        return flag;
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key 键
     * @param item 项
     * @param by 要增加几(大于0)
     */
    public double hincr(String key, String item, double by) {
        double number = redisTemplate.opsForHash().increment(key, item, by);
        closeConnection(redisTemplate);
        return number;
    }

    /**
     * hash递减
     *
     * @param key 键
     * @param item 项
     * @param by 要减少记(小于0)
     */
    public double hdecr(String key, String item, double by) {
        double number = redisTemplate.opsForHash().increment(key, item, -by);
        closeConnection(redisTemplate);
        return number;
    }
    //============================set=============================

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     */
    public Set<Object> sGet(String key) {
        try {
            Set<Object> set = redisTemplate.opsForSet().members(key);
            closeConnection(redisTemplate);
            return set;
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key 键
     * @param value 值
     * @return true 存在 false不存在
     */
    public boolean sHasKey(String key, Object value) {
        try {
            boolean flag = redisTemplate.opsForSet().isMember(key, value);
            closeConnection(redisTemplate);
            return flag;
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
    }

    /**
     * 将数据放入set缓存
     *
     * @param key 键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSet(String key, Object... values) {
        try {
            long count = redisTemplate.opsForSet().add(key, values);
            closeConnection(redisTemplate);
            return count;
        } catch (Exception e) {
            logger.error(e);
            return 0;
        }
    }

    /**
     * 将set数据放入缓存
     *
     * @param key 键
     * @param time 时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSetAndTime(String key, long time, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0) {
                expire(key, time, TimeUnit.SECONDS);
            }
            closeConnection(redisTemplate);
            return count;
        } catch (Exception e) {
            logger.error(e);
            return 0;
        }
    }

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     */
    public long sGetSetSize(String key) {
        try {
            long size = redisTemplate.opsForSet().size(key);
            closeConnection(redisTemplate);
            return size;
        } catch (Exception e) {
            logger.error(e);
            return 0;
        }
    }

    /**
     * 移除值为value的
     *
     * @param key 键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public long setRemove(String key, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);
            closeConnection(redisTemplate);
            return count;
        } catch (Exception e) {
            logger.error(e);
            return 0;
        }
    }
    //===============================list=================================

    /**
     * 获取list缓存的内容
     *
     * @param key 键
     * @param start 开始
     * @param end 结束  0 到 -1代表所有值
     */
    public List<Object> lGet(String key, long start, long end) {
        try {
            List<Object> list = redisTemplate.opsForList().range(key, start, end);
            closeConnection(redisTemplate);
            return list;
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    /**
     * 获取list缓存的长度
     *
     * @param key 键
     */
    public long lGetListSize(String key) {
        try {
            long length = redisTemplate.opsForList().size(key);
            closeConnection(redisTemplate);
            return length;
        } catch (Exception e) {
            logger.error(e);
            return 0;
        }
    }

    /**
     * 通过索引 获取list中的值
     *
     * @param key 键
     * @param index 索引  index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     */
    public Object lGetIndex(String key, long index) {
        try {
            Object object = redisTemplate.opsForList().index(key, index);
            closeConnection(redisTemplate);
            return object;
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key 键
     * @param value 值
     */
    public boolean lSet(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            closeConnection(redisTemplate);
            return true;
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key 键
     * @param value 值
     * @param time 时间(秒)
     */
    public boolean lSet(String key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0) {
                expire(key, time, TimeUnit.SECONDS);
            }
            closeConnection(redisTemplate);
            return true;
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key 键
     * @param value 值
     */
    public boolean lSet(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            closeConnection(redisTemplate);
            return true;
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
    }

    public boolean lSet(String key, List<?> value, long expireTime, TimeUnit timeUnit) {
        try {
            this.redisTemplate.opsForList().rightPushAll(key, value);
            if (expireTime > 0L) {
                redisTemplate.expire(key, TimeUnit.MILLISECONDS.convert(expireTime, timeUnit), TimeUnit.MILLISECONDS);
            }
            this.closeConnection(this.redisTemplate);
            return true;
        } catch (Exception var6) {
            logger.error(var6);
            return false;
        }
    }
    /**
     * 将list放入缓存
     *
     * @param key 键
     * @param value 值
     * @param time 时间(秒)
     */
    public boolean lSet(String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0) {
                expire(key, time, TimeUnit.SECONDS);
            }
            closeConnection(redisTemplate);
            return true;
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
    }

    /**
     * 根据索引修改list中的某条数据
     *
     * @param key 键
     * @param index 索引
     * @param value 值
     */
    public boolean lUpdateIndex(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            closeConnection(redisTemplate);
            return true;
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
    }

    /**
     * 移除N个值为value
     *
     * @param key 键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public long lRemove(String key, long count, Object value) {
        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            closeConnection(redisTemplate);
            return remove;
        } catch (Exception e) {
            logger.error(e);
            return 0;
        }
    }

    /**
     * 移除N个值为value
     *
     * @param key 键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public Cursor hscan(String key) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        Cursor<Entry<Object, Object>> entry = hashOperations.scan(key, ScanOptions.NONE);
        closeConnection(redisTemplate);
        return entry;
    }

    /**
     * 关闭连接
     */
    public void closeConnection(RedisTemplate redisTemplate) {
        redisTemplate.getConnectionFactory().getConnection().close();
    }




}