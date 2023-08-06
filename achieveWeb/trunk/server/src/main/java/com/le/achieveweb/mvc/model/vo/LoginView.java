package com.le.achieveweb.mvc.model.vo;

import lombok.*;
@Data
public class LoginView {
    private String username;
    private String password;
    private String captchaCode;
    private String role;
}
