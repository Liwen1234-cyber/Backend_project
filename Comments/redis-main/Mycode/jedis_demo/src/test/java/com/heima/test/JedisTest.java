package com.heima.test;

import com.heima.jedis.util.JedisConnectionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

public class JedisTest {
    private Jedis jedis;

    @BeforeEach
    void setUp() {
//        jedis = new Jedis("127.0.0.1", 6379);
        jedis = JedisConnectionFactory.getJedis();
        jedis.auth("123456");

        jedis.select(0);

    }

    @Test
    void testString() {
        String redult = jedis.set("name", "heima");
        System.out.println(redult);

        String name = jedis.get("name");
        System.out.println(name);

    }

    @Test
    void testHash(){
        jedis.hset("user:1", "name", "heima");
        Long res = jedis.hset("user:1", "age", "25");
        System.out.println(res);

        String name = jedis.hget("user:1", "name");
        System.out.println(name);

        String age = jedis.hget("user:1", "age");
        System.out.println(age);
    }

    @AfterEach
    void tearDown() {
        if(jedis!= null){
            jedis.close();
        }
    }
}
