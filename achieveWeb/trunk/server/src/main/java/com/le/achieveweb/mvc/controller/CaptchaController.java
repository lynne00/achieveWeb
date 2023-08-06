package com.le.achieveweb.mvc.controller;

import cn.hutool.core.lang.UUID;
import com.le.achieveweb.util.Constants;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static com.wf.captcha.utils.CaptchaUtil.setHeader;
//跨域
@CrossOrigin
@RestController
public class CaptchaController {
    @ResponseBody
    @RequestMapping("/captcha")
    public String captcha(HttpSession session) throws Exception {
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 40, 5);
        String verCode = specCaptcha.text().toLowerCase();
        session.setAttribute(Constants.CAPTCHACODE, verCode);
        return specCaptcha.toBase64();
    }
}

