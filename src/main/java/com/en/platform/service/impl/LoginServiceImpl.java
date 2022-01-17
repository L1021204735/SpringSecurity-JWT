package com.en.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.en.platform.mapper.UmsAdminMapper;
import com.en.platform.model.dto.UmsAdmin;
import com.en.platform.service.LoginService;
import com.en.platform.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author liuxiaobai
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Resource
    JwtTokenUtil jwtTokenUtil;
    @Resource
    UmsAdminMapper umsAdminMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            //查库获取用户信息
            QueryWrapper<UmsAdmin> wrapper = new QueryWrapper<>();
            wrapper.lambda().eq(UmsAdmin::getUsername, username);
            UmsAdmin umsAdmin = umsAdminMapper.selectOne(wrapper);
            if(Objects.isNull(umsAdmin)){
                throw new UsernameNotFoundException("用户不存在，请重新输入");
            }
            //校验密码
            if (!passwordEncoder.matches(password, umsAdmin.getPassword())) {
                throw new UsernameNotFoundException("密码不正确");
            }
            //利用JWT工具类生成Token
            token = jwtTokenUtil.generateToken(username);
        } catch (AuthenticationException e) {
            log.warn("登录异常", e);
        }
        return token;
    }

}
