package com.le.achieveweb.util;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    // 加密密码
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    // 验证密码
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}