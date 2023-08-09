package com.le.achieveweb.mvc.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.time.LocalDateTime;
//不需要再写getter、setter、equals、toString方法
//alt+insert快捷键 构建方法
@Data
public class User {
    private String id;
    private String username;
    private String password;
    private String role;
    private String sex;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private String email;
}
