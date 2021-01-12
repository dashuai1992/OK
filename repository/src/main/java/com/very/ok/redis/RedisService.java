package com.very.ok.redis;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

public class RedisService {
	
    private RedisTemplate<Object, Object> redisTemplate;
	
	public RedisService(RedisTemplate<Object, Object> redisTemplate){
		this.redisTemplate = redisTemplate;
	}

    /**
     * 获取
     * 2020-05-11 17:17
     * yds
     **/
    public Object get(Object key){
        return StringUtils.isEmpty(key) ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 添加
     * 2020-05-11 16:17
     * yds
     **/
    public void set(Object key, Object value){
        if(StringUtils.isEmpty(key) || null == value)
            return ;
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 添加,同时设置时效
     * 2020-05-11 16:17
     * yds
     **/
    public void set(Object key, Object value, long time){
        if(StringUtils.isEmpty(key) || null == value)
            return ;
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }

    /**
     * 根据key，和hashmap中的key获取hashMap中key对应的值
     * 2020-05-12 10:23
     * yds
     **/
    public Object hget(Object key, Object item){
        if(StringUtils.isEmpty(key) || StringUtils.isEmpty(item))
            return null;
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 根据key获取hashmap
     * 2020-05-12 10:26
     * yds
     **/
    public Map<Object, Object> hmget(Object key){
        if(StringUtils.isEmpty(key))
            return null;
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 添加
     * 2020-05-12 10:31
     * yds
     **/
    public void hmset(Object key, Map<Object, Object> map){
        if(StringUtils.isEmpty(key))
            return ;
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 添加
     * 2020-05-12 10:31
     * yds
     **/
    public void hmset(Object key, Map<Object, Object> map, long time){

        hmset(key, map);

        if(time > 0){
            expire(key, time, TimeUnit.SECONDS);
        }
    }

    /**
     * 向hashmap中添加项
     * 2020-05-12 10:37
     * yds
     **/
    public void hmset(Object key, Object itemKey, Object itemValue){
        redisTemplate.opsForHash().put(key, itemKey, itemValue);
    }

    /**
     * 向hashmap中添加项
     * 2020-05-12 10:37
     * yds
     **/
    public void hmset(Object key, Object itemKey, Object itemValue, long time){
        redisTemplate.opsForHash().put(key, itemKey, itemValue);

        if(time > 0){
            expire(key, time, TimeUnit.SECONDS);
        }
    }

    /**
     * 删除hashmap中的项
     * 2020-05-12 10:42
     * yds
     **/
    public void hmdelete(Object key, Object ...itemKey){
        redisTemplate.opsForHash().delete(key, itemKey);
    }

    /**
     * 判断hashmap中是否有itemkey对应的值
     * 2020-05-12 10:47
     * yds
     **/
    public boolean hmhas(Object key, String itemKey){
        return redisTemplate.opsForHash().hasKey(key, itemKey);
    }

    /**
     * 根据key获取Set中的所有值
     * @param key 键
     * @return
     */
    public Set<Object> sGet(Object key){
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据value从一个set中查询,是否存在
     * @param key 键
     * @param value 值
     * @return true 存在 false不存在
     */
    public boolean sHasKey(Object key,Object value){
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将数据放入set缓存
     * @param key 键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSet(Object key, Object...values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 将set数据放入缓存
     * @param key 键
     * @param time 时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSetAndTime(Object key,long time,Object...values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if(time>0) expire(key, time, TimeUnit.SECONDS);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取set缓存的长度
     * @param key 键
     * @return
     */
    public long sGetSetSize(Object key){
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 移除值为value的
     * @param key 键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public long setRemove(Object key, Object ...values) {
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取list缓存的内容
     * @param key 键
     * @param start 开始
     * @param end 结束  0 到 -1代表所有值
     * @return
     */
    public List<Object> lGet(Object key, long start, long end){
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取list缓存的长度
     * @param key 键
     * @return
     */
    public long lGetListSize(Object key){
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 通过索引 获取list中的值
     * @param key 键
     * @param index 索引  index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    public Object lGetIndex(Object key,long index){
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值
     * @return
     */
    public boolean lSet(Object key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值
     * @param time 时间(秒)
     * @return
     */
    public boolean lSet(Object key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0) expire(key, time, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值
     * @return
     */
    public boolean lSet(Object key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值
     * @param time 时间(秒)
     * @return
     */
    public boolean lSet(Object key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0) expire(key, time, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据索引修改list中的某条数据
     * @param key 键
     * @param index 索引
     * @param value 值
     * @return
     */
    public boolean lUpdateIndex(Object key, long index,Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 移除N个值为value
     * @param key 键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public long lRemove(Object key,long count,Object value) {
        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 判断key是否存在
     *
     * @return boolean
     * 2020-05-11 11:35
     * yds
     * @Param String
     **/
    public boolean has(Object key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 重命名key，如果neo已经存在，则neo的原值将会被覆盖
     *
     * @return 2020-05-11 11:37
     * yds
     * @Param old
     * @Param neo
     **/
    public void rename(Object old, Object neo) {
        redisTemplate.rename(old, neo);
    }

    /**
     * neo不存在时重命名
     *
     * @return 2020-05-11 14:25
     * yds
     * @Param old
     * @Param neo
     **/
    public Boolean renameIfAbsent(Object old, Object neo) {
        return redisTemplate.renameIfAbsent(old, neo);
    }

    /**
     * 删除
     *
     * @return 2020-05-11 14:27
     * yds
     * @Param key
     **/
    public Boolean delete(Object key) {
        return redisTemplate.delete(key);
    }

    /**
     * 删除多个key
     * @Param keys
     * @return Boolean
     * 2020-05-11 14:33
     * yds
     **/
    public Long delete(Object ...keys) {
        Set<Object> collect = Stream.of(keys).collect(Collectors.toSet());
        return redisTemplate.delete(collect);
    }

    /**
     * 删除多个key
     * @Param keys
     * @return Boolean
     * 2020-05-11 14:38
     * yds
     **/
    public Long delete(Collection<Object> keys) {
        Set<Object> collect = keys.stream().collect(Collectors.toSet());
        return redisTemplate.delete(collect);
    }

    /**
     * 设置过期时间
     * 2020-05-11 14:42
     * yds
     **/
    public Boolean expire(Object key, long timeOut, TimeUnit timeUnit){
        return redisTemplate.expire(key, timeOut, timeUnit);
    }

    /**
     * 指定过期时间点
     * 2020-05-11 14:51
     * yds
     **/
    public Boolean expireAt(Object key, Date date){
        return redisTemplate.expireAt(key, date);
    }

    /**
     * 获取超时时间
     * 2020-05-11 14:52
     * yds
     **/
    public Long getExpire(Object key){
        return redisTemplate.getExpire(key);
    }

    /**
     * 设置永久保存
     * 2020-05-11 14:52
     * yds
     **/
    public Boolean persist(Object key){
        return redisTemplate.persist(key);
    }

}
