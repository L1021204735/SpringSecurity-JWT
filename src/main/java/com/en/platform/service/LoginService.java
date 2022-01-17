package com.en.platform.service;

/**
 * @author liuxiaobai
 */
public interface LoginService {
    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username,String password);
}
