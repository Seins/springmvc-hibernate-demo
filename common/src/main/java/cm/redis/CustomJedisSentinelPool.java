package cm.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.Protocol;

import java.util.HashSet;
import java.util.Set;


public class CustomJedisSentinelPool {
    private JedisSentinelPool jedisPool;

    public CustomJedisSentinelPool(String masterName, String sentinels,
                                   final GenericObjectPoolConfig poolConfig) {
        this(masterName, sentinels, poolConfig, Protocol.DEFAULT_TIMEOUT, null,
                Protocol.DEFAULT_DATABASE);
    }

    public CustomJedisSentinelPool(String masterName, String sentinels,
                                   GenericObjectPoolConfig poolConfig, String password) {
        this(masterName, sentinels, poolConfig, Protocol.DEFAULT_TIMEOUT, password, Protocol.DEFAULT_DATABASE);
    }

    public CustomJedisSentinelPool(String masterName, String sentinels) {
        this(masterName, sentinels, new GenericObjectPoolConfig(), Protocol.DEFAULT_TIMEOUT, null,
                Protocol.DEFAULT_DATABASE);
    }

    public CustomJedisSentinelPool(String masterName, String sentinels, String password) {
        this(masterName, sentinels, new GenericObjectPoolConfig(), Protocol.DEFAULT_TIMEOUT, password);
    }

    public CustomJedisSentinelPool(String masterName, String sentinels,
                                   final GenericObjectPoolConfig poolConfig, int timeout, final String password) {
        this(masterName, sentinels, poolConfig, timeout, password, Protocol.DEFAULT_DATABASE);
    }

    public CustomJedisSentinelPool(String masterName, String sentinels,
                                   final GenericObjectPoolConfig poolConfig, final int timeout) {
        this(masterName, sentinels, poolConfig, timeout, null, Protocol.DEFAULT_DATABASE);
    }

    public CustomJedisSentinelPool(String masterName, String sentinels,
                                   final GenericObjectPoolConfig poolConfig, int timeout, final String password,
                                   final int database) {
        Set<String> sentinelSet = getSentinelSet(sentinels);
        jedisPool = new JedisSentinelPool(masterName, sentinelSet, poolConfig, timeout, password, database);
    }

    private Set<String> getSentinelSet(String sentinels) {
        Set<String> sentinelSet = new HashSet<String>();
        String[] sentinelInfo = sentinels.split(",");
        for (String sentinel : sentinelInfo) {
            sentinelSet.add(sentinel);
        }
        return sentinelSet;
    }

    public JedisSentinelPool instance() {
        return jedisPool;
    }
}
