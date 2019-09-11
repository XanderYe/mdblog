package com.xander.mdblog.config;

import com.xander.mdblog.constant.Constants;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VerCodeCache {

    @Autowired
    public CacheManager cacheManager;

    /**
     * 添加缓存
     *
     * @param key   关键字
     * @param value 值
     */
    public void put(String key, String value) {

        //获取缓存容器
        Cache<String, String> cache = cacheManager.getCache(Constants.EHCACHE_NAME, String.class, String.class);

        cache.put(key, value);
    }

    /**
     * 获取键值
     *
     * @param key
     * @return
     */
    public String get(String key) {

        try {

            //获取缓存容器
            Cache<String, String> cache = cacheManager.getCache(Constants.EHCACHE_NAME, String.class, String.class);

            return cache.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据key删除指定的cache中元素
     *
     * @throws Exception
     */

    public boolean remove(String key) throws Exception {
        try {

            //获取缓存容器
            Cache<String, String> cache = cacheManager.getCache(Constants.EHCACHE_NAME, String.class, String.class);

            //根据key删除指定元素
            cache.remove(key);
            return true;
        } catch (Exception e) {
            throw new Exception("remove element " + key + " failed!!!");
        }
    }

    /**
     * 根据删除容器中所有元素
     *
     * @throws Exception
     */

    public boolean removeAll() throws Exception {
        try {

            //获取缓存容器
            Cache<String, String> cache = cacheManager.getCache(Constants.EHCACHE_NAME, String.class, String.class);

            //根据key删除指定元素
            cache.clear();
            return true;
        } catch (Exception e) {
            throw new Exception("remove element all failed!!!");
        }
    }


}
