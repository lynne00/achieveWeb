package com.le.achieveweb.MVC.entity;

public class UserLogin {
    private String id;
    private String username;
    private String password;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return id;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return username;
    }
    public void setHashPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
}