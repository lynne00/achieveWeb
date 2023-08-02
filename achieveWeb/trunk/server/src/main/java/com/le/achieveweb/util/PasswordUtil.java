package com.le.achieveweb.util;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    // 加密密码

    /**
     * 加密密码
     * @param password 前端密码
     * @return 哈希后的密码
     */
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * 验证密码
     * @param plainPassword 前端密码
     * @param hashedPassword 哈希后的密码
     * @return bool
     */
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}