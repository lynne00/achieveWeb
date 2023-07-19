package com.le.achieveweb.util;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class PasswordUtilTest {
    @Test
    public void testHashPassword() {
        String password = "testpassword";
        String hashedPassword = PasswordUtil.hashPassword(password);
        // 确保加密后的密码不等于原始密码
        assertFalse(password.equals(hashedPassword));
    }
    @Test
    public void testCheckPassword() {
        String password = "testpassword";
        String hashedPassword = PasswordUtil.hashPassword(password);
        // 验证密码正确性
        assertTrue(PasswordUtil.checkPassword(password, hashedPassword));
    }
    @Test
    public void testCheckPasswordIncorrect() {
        String password = "testpassword";
        String wrongPassword = "wrongpassword";
        String hashedPassword = PasswordUtil.hashPassword(password);
        // 验证错误的密码
        assertFalse(PasswordUtil.checkPassword(wrongPassword, hashedPassword));
    }
}
