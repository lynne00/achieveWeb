package com.le.achieveweb.mvc.dao;

import com.le.achieveweb.mvc.model.entity.UserLogin;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

// 将类标识为bean
@Repository
@Mapper
public interface UserMapper {
    List<UserLogin> queryAll();
    UserLogin queryByName(String username);

    UserLogin queryByNameRole(String username,String role);

    String  queryHashPswByName(String username);

    void save(UserLogin user);
}