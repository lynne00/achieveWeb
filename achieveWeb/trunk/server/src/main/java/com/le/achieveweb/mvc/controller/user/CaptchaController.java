package com.le.achieveweb.mvc.controller.user;

import com.le.achieveweb.util.Constants;
import com.wf.captcha.SpecCaptcha;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


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

