package com.en.platform.login;

import com.en.platform.model.vo.UmsAdminLoginParam;
import com.en.platform.service.LoginService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author liuxiaobai
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/loginIn")
    public String loginIn(@RequestBody UmsAdminLoginParam umsAdminLoginParam){
        String token = loginService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        return "token:"+token;
    }

    @GetMapping("/role")
    @PreAuthorize("hasAuthority('role')")
    public String role(){
        return "role";
    }

    @GetMapping("/role1")
    @PreAuthorize("hasAuthority('role1')")
    public String role1(){
        return "role1";
    }
}
