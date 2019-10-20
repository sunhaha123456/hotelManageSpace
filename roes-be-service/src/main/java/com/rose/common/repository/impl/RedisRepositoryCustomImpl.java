package com.rose.common.repository.impl;

import com.rose.common.repository.RedisRepositoryCustom;
import com.rose.common.util.JsonUtil;
import com.rose.common.util.RedisKeyUtil;
import com.rose.data.entity.TbSysUser;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Repository
public class RedisRepositoryCustomImpl implements RedisRepositoryCustom {

    @Inject
    StringRedisTemplate template;

    public void save(String key, String value) {
        ValueOperations<String, String> ops = template.opsForValue();
        ops.set(key, value);
    }

    public void saveMinutes(String key, String value, long time) {
        ValueOperations<String, String> ops = template.opsForValue();
        ops.set(key, value, time, TimeUnit.MINUTES);
    }

    public void saveDays(String key, String value, long time) {
        ValueOperations<String, String> ops = template.opsForValue();
        ops.set(key, value, time, TimeUnit.DAYS);
    }

    public void expireMinutes(String key, long time) {
        template.expire(key, time, TimeUnit.MINUTES);
    }

    public void expireDays(String key, long time) {
        template.expire(key, time, TimeUnit.DAYS);
    }

    public <T> T getClassObj(String key, Class<T> clazz) {
        String value = getString(key);
        T t = null;
        if (value != null && value.length() > 0) {
            t = JsonUtil.jsonToObject(value, clazz);
        }
        return t;
    }

    public String getString(String key) {
        ValueOperations<String, String> ops = template.opsForValue();
        String value = ops.get(key);
        return value;
    }

    public void delete(String key) {
        template.delete(key);
    }

    @Override
    public void deleteKeys(List<String> keyList) {
        template.delete(keyList);
    }

    @Override
    public void deleteUserKeys(List<TbSysUser> userList) {
        if (userList != null && userList.size() > 0) {
            List<String> keyList = new ArrayList<>();
            for (TbSysUser user : userList) {
                keyList.add(RedisKeyUtil.getRedisUserInfoKey(user.getId()));
            }
            deleteKeys(keyList);
        }
    }
}