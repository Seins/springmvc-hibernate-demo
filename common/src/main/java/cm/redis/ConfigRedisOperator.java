package cm.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.exceptions.JedisConnectionException;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhangj
 * @date 2013-11-8
 */
@Component
public class ConfigRedisOperator extends RedisOperator implements DisposableBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigRedisOperator.class);

    @Resource(name = "configJedisPool")
    private CustomJedisSentinelPool configJedisPool;

    public void batchLpush(String key, List<String> list) {
        Jedis jedis = getJedis();
        try {
            jedis.lpush(key, list.toArray(new String[list.size()]));
        } finally {
            closeJedis(jedis);
        }
    }

    public Pipeline getPipeline() {
        Jedis jedis = getJedis();
        try {
            return jedis.pipelined();
        } finally {
            closeJedis(jedis);
        }
    }

    public void closePipeline(Pipeline pipeline) {
        pipeline.sync();
    }

    public List<Object> closePipelineAndReturnAll() {
        return getPipeline().syncAndReturnAll();
    }

    public Boolean exists(String key) {
        Jedis jedis = getJedis();
        try {
            return jedis.exists(key);
        } finally {
            closeJedis(jedis);
        }
    }

    public long incrby(String key, long increment) {
        Jedis jedis = getJedis();
        try {
            return jedis.incrBy(key, increment);
        } finally {
            closeJedis(jedis);
        }
    }

    public void expire(String key, int second) {
        Jedis jedis = getJedis();
        try {
            jedis.expire(key, second);
        } finally {
            closeJedis(jedis);
        }
    }

    public void pexpire(String key, long milliseconds) {
        Jedis jedis = getJedis();
        try {
            jedis.pexpire(key, milliseconds);
        } finally {
            closeJedis(jedis);
        }
    }

    @Override
    public Jedis getJedis() {
        Jedis jedis = null;
        try {
            jedis = configJedisPool.instance().getResource();
        } catch (JedisConnectionException e) {
            jedis = configJedisPool.instance().getResource();
            LOGGER.error("get config redis error", e);
        } catch (IllegalStateException e) {
            jedis = configJedisPool.instance().getResource();
            LOGGER.error("get config redis error:", e);
        }
        return jedis;
    }

    public void closeJedis(Jedis jedis) {
        if (jedis != null && jedis.isConnected()) {
            try {
                configJedisPool.instance().returnResource(jedis);
            } catch (Exception e) {
                LOGGER.error("error when close jedis:", e);
            }
        }
    }

    @Override
    public void closeJedisPool() {
        configJedisPool.instance().destroy();
    }

    @Override
    public void destroy() throws Exception {
        LOGGER.info("destory jedis pool...");
        closeJedisPool();
    }
}
