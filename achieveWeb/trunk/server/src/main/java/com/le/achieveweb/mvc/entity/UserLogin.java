package com.le.achieveweb.mvc.entity;

import lombok.*;
//不需要再写getter、setter、equals、toString方法
//alt+insert快捷键 构建方法
@Data
public class UserLogin {
    private String id;
    private String username;
    private String password;
}
