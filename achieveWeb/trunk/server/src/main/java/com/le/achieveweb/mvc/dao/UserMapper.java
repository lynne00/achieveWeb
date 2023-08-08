package com.le.achieveweb.mvc.dao;

import com.le.achieveweb.mvc.model.entity.User;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

// 将类标识为bean
@Repository
@Mapper
public interface UserMapper {
    List<User> queryAll();
    User queryByName(String username);
    User queryByNameRole(String username, String role);
    String queryHashPswByName(String username);
    int updateUserInfo(HashMap<String,Object> map);
    void save(User user);
}