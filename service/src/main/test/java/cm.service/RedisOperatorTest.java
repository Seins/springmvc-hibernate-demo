package cm.service;

import cm.redis.ConfigRedisOperator;
import cm.redis.CustomJedisSentinelPool;
import cm.redis.RedisKeyCenter;
import cm.redis.RedisOperator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

/**
 * 类名：cm.service.RedisOperatorTest
 * 创建者： CM .
 * 创建时间：2016/4/19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:applicationContext.xml"
})
@WebAppConfiguration
public class RedisOperatorTest {


    @Test
    public void testCommonOperator() {

    }

}
