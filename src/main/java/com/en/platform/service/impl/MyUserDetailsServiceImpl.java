package com.en.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.en.platform.mapper.UmsAdminMapper;
import com.en.platform.model.dto.UmsAdmin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author liuxiaobai
 */
@Service("userDetailsService")
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Resource
    UmsAdminMapper umsAdminMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询数据库获取用户信息
        QueryWrapper<UmsAdmin> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UmsAdmin::getUsername, username);
        UmsAdmin umsAdmin = umsAdminMapper.selectOne(wrapper);
        if(Objects.isNull(umsAdmin)){
            throw new UsernameNotFoundException("用户不存在，请重新输入");
        }
        //todo 这里需要用上面从数据库中查询出的权限信息进行赋值，本DEMO直接写死
        List<GrantedAuthority> auths = AuthorityUtils
                .commaSeparatedStringToAuthorityList("role");
        return new User(umsAdmin.getUsername(),umsAdmin.getPassword(),auths);
    }
}
