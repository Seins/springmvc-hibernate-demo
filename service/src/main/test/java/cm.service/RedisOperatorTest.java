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
 * ������cm.service.RedisOperatorTest
 * �����ߣ� CM .
 * ����ʱ�䣺2016/4/19
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
