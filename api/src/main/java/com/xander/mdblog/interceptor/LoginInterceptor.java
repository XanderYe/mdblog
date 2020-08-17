package com.xander.mdblog.interceptor;

import com.xander.mdblog.base.RequestContextHolder;
import com.xander.mdblog.config.RedisService;
import com.xander.mdblog.entity.User;
import com.xander.mdblog.enums.ErrorCodeEnum;
import com.xander.mdblog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.xander.mdblog.util.CheckUtil.check;

/**
 * Created by Xander on 2018-11-05.
 */
@Slf4j
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String remoteAddr = request.getRemoteAddr();
        String method = request.getMethod();
        String uri = request.getRequestURI();

        //不拦截OPTION请求
        if (method.equals(RequestMethod.OPTIONS.name())) {
            return true;
        }

        String userToken = request.getHeader("md-token");
        log.info("remoteAddr={},  method={}, uri={}, userToken={}", remoteAddr, method, uri, userToken);

        // token不为空
        check(StringUtils.isNoneEmpty(userToken), ErrorCodeEnum.ACCOUNT_AUTH_ERROR,
                "remoteAddr={}, method={}, uri={}, userToken={}", remoteAddr, method, uri, userToken);

        // 查询用户
        User user = (User) redisService.get(userToken);
        check(user != null, ErrorCodeEnum.ACCOUNT_EXPIRED,
                "remoteAddr={}, method={}, uri={}, userToken={}", remoteAddr, method, uri, userToken);


        // 保存user
        RequestContextHolder.set(user);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        RequestContextHolder.reset();
    }
}