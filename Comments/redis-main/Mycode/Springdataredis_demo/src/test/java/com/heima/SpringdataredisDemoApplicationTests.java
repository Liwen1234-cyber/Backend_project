package com.heima;

import com.heima.redis.pojo.User;
import com.heima.redis.config.RedisConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class SpringdataredisDemoApplicationTests {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void testString(){
        redisTemplate.opsForValue().set("name", "heima1asf23");
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println(name);
    }

    @Test
    void testServeUser(){
        redisTemplate.opsForValue().set("user:100", new User("张四", 21));
        User user = (User) redisTemplate.opsForValue().get("user:100");
        System.out.println("o:" + user);
    }

}

