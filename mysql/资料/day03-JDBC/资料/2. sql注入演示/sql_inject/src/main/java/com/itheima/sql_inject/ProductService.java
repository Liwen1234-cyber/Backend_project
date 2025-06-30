package com.itheima.sql_inject;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {

    private JdbcTemplate template;

    public ProductService(JdbcTemplate template) {
        this.template = template;
    }

    public List<ProductController.Product> list(String name) {
        String sql = "select * from test.product where name like '%" + name + "%'";
        log.info(sql);
        return template.query(sql, new BeanPropertyRowMapper<>(ProductController.Product.class));
    }
}
