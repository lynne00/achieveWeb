package com.le.achieveweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
@MapperScan("com.le.achieveweb.mvc.dao")
public class AchieveWebApplication {

    public static void main(String[] args) {

        SpringApplication.run(AchieveWebApplication.class, args);

    }
}

