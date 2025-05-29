package com.heima;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.heima.redis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Map;

@SpringBootTest
public class RedisStringTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void testString(){
        stringRedisTemplate.opsForValue().set("name", "虎哥");
        Object name = stringRedisTemplate.opsForValue().get("name");
        System.out.println(name);
    }

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    void testServeUser() throws JsonProcessingException {
        User user = new User("张三", 20);

        String json = mapper.writeValueAsString(user);


        stringRedisTemplate.opsForValue().set("user:100", json);
        String jsonUser = stringRedisTemplate.opsForValue().get("user:100");
        User user1 = mapper.readValue(jsonUser, User.class);
        System.out.println("o:" + user);
    }


    @Test
    void testHash() {
        stringRedisTemplate.opsForHash().put("user:200", "name", "张三");
        stringRedisTemplate.opsForHash().put("user:200", "age", "20");
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("user:200");
        System.out.println(entries);

    }
}
