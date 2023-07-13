package com.le.achieveweb.dao;

import com.le.achieveweb.entity.UserLogin;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

// 将类标识为bean
@Repository
@Mapper
public interface UserMapper {
    List<UserLogin> queryAll();

    UserLogin queryByName(String username);

    String  queryPswByName(String userName);

    void save(UserLogin user);
}