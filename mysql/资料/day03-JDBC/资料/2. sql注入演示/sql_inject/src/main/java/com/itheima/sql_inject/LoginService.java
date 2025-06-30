package com.itheima.sql_inject;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoginService {

    private JdbcTemplate template;

    public LoginService(JdbcTemplate template) {
        this.template = template;
    }

    public boolean login(String username, String password) {
        String sql = "select count(1) from test.user where username='" + username + "' and password='" + password + "'";
        log.info(sql);
        int count = template.queryForObject(sql, int.class);
        log.info(count+"");
        return count >= 1;
    }
}
