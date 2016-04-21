package cm.redis;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.ScanResult;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 类名：cm.redis.RedisOperator
 * 创建者： CM .
 * 创建时间：2016/4/19
 */
@Component
public class RedisOperator {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisOperator.class);

    public Jedis getJedis() {
        return null;
    }

    public void closeJedis(Jedis jedis) {
    }

    public void delete(String... key) {
        Jedis jedis = getJedis();
        try {
            jedis.del(key);
        } finally {
            closeJedis(jedis);
        }
    }

    public void set(String key, String value) {
        Jedis jedis = getJedis();
        try {
            jedis.set(key, value);
        } finally {
            closeJedis(jedis);
        }
    }

    public String setex(String key, int seconds, String value) {
        Jedis jedis = getJedis();
        String result = null;
        try {
            result = jedis.setex(key, seconds, value);
        } finally {
            closeJedis(jedis);
        }

        return result;
    }

    public void expire(String key, int second) {
        Jedis jedis = getJedis();
        try {
            jedis.expire(key, second);
        } finally {
            closeJedis(jedis);
        }
    }

    public void rpush(String key, String value) {
        Jedis jedis = getJedis();
        try {
            jedis.rpush(key, value);
        } finally {
            closeJedis(jedis);
        }
    }

    public String lpop(String key) {
        Jedis jedis = getJedis();
        String value;
        try {
            value = jedis.lpop(key);
        } finally {
            closeJedis(jedis);
        }
        return value;
    }

    public String blpop(String key) {
        Jedis jedis = getJedis();
        String value;
        try {
            List<String> values = jedis.blpop(1, key);
            if (CollectionUtils.isNotEmpty(values)) {
                value = values.get(1);
            } else {
                value = null;
            }
        } finally {
            closeJedis(jedis);
        }
        return value;
    }

    public Long hincrby(String key, String field, Long value) {
        Jedis jedis = getJedis();
        try {
            return jedis.hincrBy(key, field, value);
        } finally {
            closeJedis(jedis);
        }
    }

    public Double hincrByFloat(String key, String field, double value) {
        Jedis jedis = getJedis();
        try {
            return jedis.hincrByFloat(key, field, value);
        } finally {
            closeJedis(jedis);
        }
    }

    public void hincrby(String key, Map<String, Long> fieldToValue) {
        Jedis jedis = getJedis();
        try {
            for (Map.Entry<String, Long> entry : fieldToValue.entrySet()) {
                String field = entry.getKey();
                Long value = entry.getValue();
                jedis.hincrBy(key, field, value);
            }
        } finally {
            closeJedis(jedis);
        }
    }

    public Set<String> keys(String keyPattern) {
        Set<String> keys = null;
        Jedis jedis = getJedis();
        try {
            keys = jedis.keys(keyPattern);
        } finally {
            closeJedis(jedis);
        }
        return keys;
    }

    public Map<String, String> hGetAll(String key) {
        Jedis jedis = getJedis();
        Map<String, String> result = null;
        try {
            result = jedis.hgetAll(key);
        } catch (Exception e) {
            LOGGER.error("hGetAll error:", e);
        } finally {
            closeJedis(jedis);
        }
        return result;
    }

    public String hmSet(String key, Map<String, String> hash) {
        String result = null;
        Jedis jedis = getJedis();
        try {
            result = jedis.hmset(key, hash);

        } finally {
            closeJedis(jedis);
        }

        return result;
    }

    public List<String> hmGet(String key, String... keys) {
        Jedis jedis = getJedis();
        List<String> result = null;
        try {
            result = jedis.hmget(key, keys);

        } finally {
            closeJedis(jedis);
        }

        return result;
    }

    /**
     * 分批进行hmget
     *
     * @param key
     * @param fieldList
     * @param batchGetNum : 每次hmget的最大数量
     * @return
     */
    public List<String> batchHmget(String key, List<String> fieldList, int batchGetNum) {
        List<String> getResult = Lists.newArrayList();

        List<Object> result = null;

        Jedis jedis = getJedis();
        int indexFrom = 0;
        int indexEnd;
        boolean flag = true;

        try {
            Pipeline pipeline = jedis.pipelined();
            pipeline.multi();

            if (fieldList.size() < batchGetNum) {
                String[] fields = new String[fieldList.size()];
                fieldList.toArray(fields);

                pipeline.hmget(key, fields);
            } else {
                while (flag) {
                    if (indexFrom + batchGetNum >= fieldList.size()) {
                        indexEnd = fieldList.size();
                        flag = false;
                    } else {
                        indexEnd = indexFrom + batchGetNum;
                    }
                    List<String> subList = fieldList.subList(indexFrom, indexEnd);
                    indexFrom = indexEnd;

                    String[] fields = new String[subList.size()];
                    subList.toArray(fields);

                    pipeline.hmget(key, fields);

                }

            }

            pipeline.exec();
            result = pipeline.syncAndReturnAll();

            List<List<String>> operResult = (List<List<String>>) result.get(result.size() - 1);

            for (List<String> list : operResult) {
                getResult.addAll(list);
            }

        } finally {
            closeJedis(jedis);
        }

        return getResult;
    }

    /**
     * 分批进行hmset
     *
     * @param key
     * @param map
     * @param batchSetNum : 每次hmset的最大数量
     */
    public List<Object> batchHmset(String key, Map<String, String> map, int batchSetNum) {
        List<Object> result = null;
        Jedis jedis = getJedis();

        try {
            Pipeline pipeline = jedis.pipelined();
            pipeline.multi();

            if (map.size() < batchSetNum) {
                pipeline.hmset(key, map);
            } else {
                Map<String, String> temp = Maps.newConcurrentMap();
                int index = 0;
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    temp.put(entry.getKey(), entry.getValue());
                    if (((++index) % batchSetNum) == 0) {
                        pipeline.hmset(key, temp);
                        temp.clear();
                    }

                }

                if (map.size() % batchSetNum != 0) {
                    pipeline.hmset(key, temp);
                }
            }

            pipeline.exec();
            result = pipeline.syncAndReturnAll();

        } catch (Exception e) {
            return null;
        } finally {
            closeJedis(jedis);
        }

        return result;
    }


    public boolean hmSet(String key1, Map<String, String> map1, String key2, Map<String, String> map2) {
        Jedis jedis = getJedis();

        try {
            Pipeline pipeline = jedis.pipelined();
            pipeline.multi();

            pipeline.hmset(key1, map1);
            pipeline.hmset(key2, map2);

            pipeline.exec();
            pipeline.syncAndReturnAll();

        } catch (Exception e) {
            return false;
        } finally {
            closeJedis(jedis);
        }

        return true;
    }

    /**
     * @param key
     * @param fields
     * @return List<Boolean>
     */
    public List<Boolean> hexists(String key, Set<String> fields) {
        List<Boolean> hexistsResult = Lists.newArrayList();

        Jedis jedis = getJedis();

        try {
            Pipeline pipeline = jedis.pipelined();

            pipeline.multi();

            for (String field : fields) {
                pipeline.hexists(key, field);
            }

            pipeline.exec();
            List<Object> result = pipeline.syncAndReturnAll();

            hexistsResult = (List<Boolean>) result.get(result.size() - 1);

        } finally {
            closeJedis(jedis);
        }
        return hexistsResult;
    }


    /**
     * 使用Hscan获取Hash键值对
     *
     * @param key hashKey
     * @return
     */
    public Set<Map.Entry<String, String>> hScan(String key) {
        int cursor = 0;
        Set<Map.Entry<String, String>> resultSet = Sets.newHashSet();
        Jedis jedis = getJedis();
        try {
            ScanResult<Map.Entry<String, String>> scanResult;
            do {
                scanResult = jedis.hscan(key, String.valueOf(cursor));
                for (Map.Entry<String, String> entry : scanResult.getResult()) {
                    if (resultSet.contains(entry)) {
                        continue;
                    }
                    resultSet.add(entry);
                }
                cursor = Integer.parseInt(scanResult.getStringCursor());
            } while (cursor > 0);
        } catch (Exception ex) {
            LOGGER.error("hscan error key:{}, error:", key, ex);
            resultSet = Collections.emptySet();
        } finally {
            closeJedis(jedis);
        }
        return resultSet;
    }

    public boolean rpushAndHdel(String listKey, String listVal, String hashKey, String hashField) {
        Jedis jedis = getJedis();

        try {
            Pipeline pipeline = jedis.pipelined();
            pipeline.multi();

            jedis.rpush(listKey, listVal);
            jedis.hdel(hashKey, hashField);

            pipeline.exec();
            pipeline.sync();

        } catch (Exception e) {
            return false;
        } finally {
            closeJedis(jedis);
        }

        return true;
    }

    public Long hdel(String key, String field) {
        Long result = null;
        Jedis jedis = getJedis();
        try {
            result = jedis.hdel(key, field);

        } finally {
            closeJedis(jedis);
        }

        return result;
    }

    public List<Long> hdel(String key, Set<String> fields) {
        List<Long> delResult = Lists.newArrayList();

        Jedis jedis = getJedis();

        try {
            Pipeline pipeline = jedis.pipelined();

            pipeline.multi();

            for (String field : fields) {
                pipeline.hdel(key, field);
            }

            pipeline.exec();
            List<Object> result = pipeline.syncAndReturnAll();

            delResult = (List<Long>) result.get(result.size() - 1);

        } finally {
            closeJedis(jedis);
        }
        return delResult;
    }

    public Long zadd(String key, double score, String member) {
        Long result = null;
        Jedis jedis = getJedis();
        try {
            result = jedis.zadd(key, score, member);

        } finally {
            closeJedis(jedis);
        }

        return result;
    }

    public void hset(String key, String field, String value) {
        Jedis jedis = getJedis();
        try {
            jedis.hset(key, field, value);
        } finally {
            closeJedis(jedis);
        }
    }

    public Long hsetnx(String key, String field, String value) {
        Jedis jedis = getJedis();
        try {
            return jedis.hsetnx(key, field, value);
        } finally {
            closeJedis(jedis);
        }
    }

    public boolean hexist(String key, String field) {
        boolean exist = false;
        Jedis jedis = getJedis();
        try {
            exist = jedis.hexists(key, field);
        } finally {
            closeJedis(jedis);
        }
        return exist;
    }

    public String hget(String key, String field) {
        String value = null;
        Jedis jedis = getJedis();
        try {
            value = jedis.hget(key, field);
        } finally {
            closeJedis(jedis);
        }
        return value;
    }

    public Map<String, String> hGetAllAndExpired(String key, int expired) {
        Jedis jedis = getJedis();
        Map<String, String> result = null;
        try {
            result = jedis.hgetAll(key);
            jedis.expire(key, expired);
        } finally {
            closeJedis(jedis);
        }
        return result;
    }

    public void closeJedisPool() {
    }

    public String get(String key) {
        String value = null;
        Jedis jedis = getJedis();
        try {
            value = jedis.get(key);
        } finally {
            closeJedis(jedis);
        }
        return value;
    }

    public void lpush(String key, String value) {
        Jedis jedis = getJedis();
        try {
            jedis.lpush(key, value);
        } finally {
            closeJedis(jedis);
        }
    }

    public void subscribe(JedisPubSub jedisPubSub, String... channels) {
        Jedis jedis = getJedis();
        try {
            jedis.subscribe(jedisPubSub, channels);
        } finally {
            closeJedis(jedis);
        }
    }

    public Long llen(String key) {
        Jedis jedis = getJedis();
        Long result;
        try {
            result = jedis.llen(key);

        } finally {
            closeJedis(jedis);
        }
        return result;
    }

    public Long hlen(String key) {
        Jedis jedis = getJedis();
        Long result;
        try {
            result = jedis.hlen(key);
        } finally {
            closeJedis(jedis);
        }
        return result;
    }

    public String rpoplpush(String srckey, String dstkey) {
        Jedis jedis = getJedis();
        String result;
        try {
            result = jedis.rpoplpush(srckey, dstkey);
        } finally {
            closeJedis(jedis);
        }
        return result;
    }

    public List<String> lrange(String key, long start, long end) {
        List<String> result = null;
        Jedis jedis = getJedis();
        try {
            result = jedis.lrange(key, start, end);
        } finally {
            closeJedis(jedis);
        }
        return result;
    }

    public Long lrem(String key, long count, String value) {
        Jedis jedis = getJedis();
        Long result;
        try {
            result = jedis.lrem(key, count, value);
        } finally {
            closeJedis(jedis);
        }
        return result;
    }

    public void lremFromList(String key, long count, List<String> list) {
        for (String value : list) {
            lrem(key, count, value);
        }
    }

    public List<String> getAllFromList(String key) {
        long len = llen(key);
        return lrange(key, 0, len);
    }

    public Set<String> zrange(String key, int start, int end) {
        Jedis jedis = getJedis();
        Set<String> result;
        try {
            result = jedis.zrange(key, start, end);
        } finally {
            closeJedis(jedis);
        }
        return result;
    }

    public Boolean sismember(String key, String member) {
        Jedis jedis = getJedis();
        try {
            return jedis.sismember(key, member);
        } finally {
            closeJedis(jedis);
        }
    }

    public Long sadd(String key, List<String> valueList) {
        Jedis jedis = getJedis();
        try {
            String[] values = new String[valueList.size()];
            valueList.toArray(values);
            return jedis.sadd(key, values);
        } finally {
            closeJedis(jedis);
        }
    }

    public Long sadd(String key, String value) {
        Jedis jedis = getJedis();
        try {
            return jedis.sadd(key, value);
        } finally {
            closeJedis(jedis);
        }
    }

    public Long srem(String key, List<String> valueList) {
        Jedis jedis = getJedis();
        try {
            String[] values = new String[valueList.size()];
            valueList.toArray(values);
            return jedis.srem(key, values);
        } finally {
            closeJedis(jedis);
        }
    }

    public Long srem(String key, String value) {
        Jedis jedis = getJedis();
        try {
            return jedis.srem(key, value);
        } finally {
            closeJedis(jedis);
        }
    }

    public Set<String> smembers(String key) {
        Jedis jedis = getJedis();
        try {
            return jedis.smembers(key);
        } finally {
            closeJedis(jedis);
        }
    }

    public Set<String> sScan(String key) {
        int cursor = 0;
        Set<String> resultSet = Sets.newHashSet();
        Jedis jedis = getJedis();
        try {
            ScanResult<String> scanResult;
            do {
                scanResult = jedis.sscan(key, String.valueOf(cursor));
                for (String value : scanResult.getResult()) {
                    if (resultSet.contains(value)) {
                        continue;
                    }
                    resultSet.add(value);
                }
                cursor = Integer.parseInt(scanResult.getStringCursor());
            } while (cursor > 0);
        } catch (Exception ex) {
            LOGGER.error("sscan error key:{}, error:", key, ex);
            resultSet = Collections.emptySet();
        } finally {
            closeJedis(jedis);
        }
        return resultSet;
    }

    public Long zadd(String key, Map<String, Double> scoreMembers) {
        Jedis jedis = getJedis();
        try {
            return jedis.zadd(key, scoreMembers);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            closeJedis(jedis);
        }
    }

    public void publish(String chanel, String message) {
        Jedis jedis = getJedis();
        try {
            jedis.publish(chanel, message);
        } finally {
            closeJedis(jedis);
        }
    }
}
