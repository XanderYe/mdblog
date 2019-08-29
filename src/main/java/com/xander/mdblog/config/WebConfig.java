package com.xander.mdblog.config;

import com.xander.mdblog.filter.CorsFilter;
import com.xander.mdblog.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 叶振东
 * @date 2018-12-21
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Value("${static.path.pattern}")
    private String staticPathPattern;
    @Value("${static.resources.locations}")
    private String staticResourcesLocations;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.loginInterceptor).addPathPatterns("/**")
                // swagger页面
                .excludePathPatterns("/swagger-ui.html")
                .excludePathPatterns("/swagger-resources/**")
                .excludePathPatterns("/v2/api-docs")
                // swagger 静态资源
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/upload/**")
                .excludePathPatterns("/user/getOwner")
                .excludePathPatterns("/captcha")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/register")
                .excludePathPatterns("/topic/getRouters")
                .excludePathPatterns("/article/getRecentArticles")
                .excludePathPatterns("/topic/getAll")
                .excludePathPatterns("/topic/getById");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(staticPathPattern.split(",")).addResourceLocations(staticResourcesLocations.split(","));
    }

    /**
     * 过滤器
     * @param
     * @return org.springframework.boot.web.servlet.FilterRegistrationBean
     * @author yezhendong
     * @date 2019/8/27
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new CorsFilter());
        bean.addUrlPatterns("/*");
        return bean;
    }

}
