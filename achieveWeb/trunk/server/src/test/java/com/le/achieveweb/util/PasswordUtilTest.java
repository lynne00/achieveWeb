package com.le.achieveweb.util;
import cn.hutool.core.util.RandomUtil;
import org.junit.jupiter.api.RepeatedTest;
import static org.junit.jupiter.api.Assertions.*;
public class PasswordUtilTest {
    @RepeatedTest(500)
    public void testHashPassword() {
        int length = RandomUtil.randomInt(9, 25); // 生成随机长度，范围在9到25之间
        String randomPassword = RandomUtil.randomString(length);// 生成随机密码
        String hashedPassword = PasswordUtil.hashPassword(randomPassword);
        // 确保加密后的密码不等于原始密码
        assertFalse(randomPassword.equals(hashedPassword));
    }
    @RepeatedTest(500)
    public void testCheckPassword() {
        int length = RandomUtil.randomInt(9, 25); // 生成随机长度，范围在9到25之间
        String randomPassword = RandomUtil.randomString(length);// 生成随机密码
        String hashedPassword = PasswordUtil.hashPassword(randomPassword);
        // 验证密码正确性
        assertTrue(PasswordUtil.checkPassword(randomPassword, hashedPassword));
    }
    @RepeatedTest(500)
    public void testCheckPasswordIncorrect() {
        int length = RandomUtil.randomInt(9, 25); // 生成随机长度，范围在9到25之间
        String randomPassword = RandomUtil.randomString(length);// 生成随机密码
        String wrongPassword =  RandomUtil.randomString(length);// 生成随机错误密码
        String hashedPassword = PasswordUtil.hashPassword(randomPassword);
        // 验证错误的密码
        assertFalse(PasswordUtil.checkPassword(wrongPassword, hashedPassword));
    }
}
