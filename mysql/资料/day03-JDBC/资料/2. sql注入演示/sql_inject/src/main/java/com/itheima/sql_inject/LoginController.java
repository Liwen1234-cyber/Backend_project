package com.itheima.sql_inject;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginRequest request) {
        Map<String, Object> map = new HashMap<>();
        boolean isLogin = loginService.login(request.getUsername(), request.getPassword());
        map.put("success", isLogin);
        return map;
    }

    @Data
    static class LoginRequest {
        private String username;
        private String password;

    }
}
