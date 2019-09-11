package com.xander.mdblog.config;

import com.xander.mdblog.constant.Constants;
import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author 叶振东
 * @date 2019/9/10
 */
@Configuration
public class EhcacheConfig {

    @Bean
    public CacheManager cacheManager() {

        // 配置默认缓存属性
        CacheConfiguration<String, String> cacheConfiguration = CacheConfigurationBuilder.newCacheConfigurationBuilder(
                // 缓存数据K和V的数值类型
                // 在ehcache3.3中必须指定缓存键值类型,如果使用中类型与配置的不同,会报类转换异常
                String.class, String.class,
                ResourcePoolsBuilder.newResourcePoolsBuilder()
                        //设置缓存堆容纳元素个数(JVM内存空间)超出个数后会存到offheap中
                        .heap(100L, EntryUnit.ENTRIES)
                        //设置堆外储存大小(内存存储) 超出offheap的大小会淘汰规则被淘汰
                        .offheap(10L, MemoryUnit.MB)

        ).withExpiry(
                //设置缓存距离上次访问24小时后自动过期
                Expirations.timeToIdleExpiration(Duration.of(30, TimeUnit.MINUTES))
        ).build();


        // CacheManager管理缓存
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                // 设置一个默认缓存配置
                .withCache(Constants.EHCACHE_NAME, cacheConfiguration)
                //创建之后立即初始化
                .build(true);
        return cacheManager;
    }
}
