package com.xander.mdblog.controller;

import com.xander.mdblog.util.VerifyCodeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 叶振东
 * @date 2019/8/27
 */
@Api(value="验证码controller",tags={"验证码操作接口"})
@RequestMapping
public class CaptchaController {

    /**
     * 验证码
     *
     * @param session
     * @param response
     * @return void
     * @author yezhendong
     * @date 2019-07-11
     */
    @ApiOperation(value="获取验证码",notes="不需要登录，直接输出图片")
    @GetMapping("captcha")
    public void captcha(HttpSession session, HttpServletResponse response) throws Exception {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        String code = VerifyCodeUtils.generateVerifyCode(4);
        session.removeAttribute("captcha");
        session.setAttribute("captcha", code);
        int w = 100, h = 30;
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), code);
    }
}
