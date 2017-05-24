package org.jks.utils;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by liaojian on 2017/5/17.
 */
public class RedisUtil {
    private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);
    public static final String UNDERLINE = "_";
    public static final long SLEEP_TIME = 3000;
    public static int times = 0;
    public static Map<String, Boolean> testMap = Maps.newHashMap();

    public static void redisCluster(Set<HostAndPort> clusterNodes, int count) {
        JedisCluster jc = new JedisCluster(clusterNodes, 50, clusterNodes.size());
        System.out.println("cluster:" + count);
        long start = System.currentTimeMillis();

        for (int i = 0; i < 1000; i++) {
            try {
                jc.set("foo" + i, "bar");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("set value in " + (System.currentTimeMillis() - start) + " ms");

        start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            try {
                jc.get("foo" + i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("get value in " + (System.currentTimeMillis() - start) + " ms");
    }

    public static void sentinels() {
        Set<String> sentinels = new HashSet<String>();
        sentinels.add("192.168.8.231:26379");

        /*JedisPoolConfig jedisConfig = new JedisPoolConfig();
        jedisConfig.setMaxActive(2);
        jedisConfig.setMaxIdle(2);
        jedisConfig.setMaxWait(5);
        jedisConfig.setTestOnBorrow(true);*/
        //JedisSentinelPool pool = new JedisSentinelPool("redismaster", sentinels, jedisConfig);
        JedisSentinelPool pool = new JedisSentinelPool("redismaster", sentinels);
        String hostAndPort = pool.getCurrentHostMaster() + "";
        System.out.println(hostAndPort);

        // String[] params = hostAndPort.split(":");

        //Jedis jedis = new Jedis("192.168.8.231", Integer.valueOf(params[1]));

        //jedis.set("hello", "world");

        //System.out.println(jedis.get("hello"));
    }

    public static void testStandalone(int count) {
        Jedis jedis = new Jedis("192.168.8.231", 7379);

        System.out.println("standalone: " + count);
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            try {
                jedis.set("hello" + i, "bbb" + i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("set value in " + (System.currentTimeMillis() - start) + " ms");

        start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            try {
                jedis.get("hello" + i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("get value in " + (System.currentTimeMillis() - start) + " ms");
    }

    public static void testFailover(Set<HostAndPort> clusterNodes) {
        JedisCluster jc = new JedisCluster(clusterNodes, 15000, clusterNodes.size());

        String prefix = "test";

        Thread writeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    String name = prefix + UNDERLINE + times;
                    testMap.put(name, Boolean.FALSE);
                    int failTimes = 0;
                    for (int i = 0; i < 1000; i++) {
                        try {
                            jc.set(name + UNDERLINE + i, "value");
                        } catch (Exception e) {
                            failTimes++;
                            logger.error(e.getMessage(), e);
                        }
                    }
                    testMap.put(name, Boolean.TRUE);
                    System.out.println("write value , the " + (times++)+ " times fail:" + failTimes);
                    try {
                        Thread.sleep(SLEEP_TIME);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread readThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Map<String, Boolean> bakMap = Maps.newHashMap();
                    if(testMap.isEmpty()){
                        try {
                            Thread.sleep(SLEEP_TIME);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        continue;
                    }

                    for(String key : testMap.keySet()){
                        Boolean ret = testMap.get(key);
                        if(ret){
                            bakMap.put(key, ret);
                            String name = key;
                            int failTimes = 0;
                            for (int i = 0; i < 1000; i++) {
                                try {
                                    jc.get(name + UNDERLINE + i);
                                } catch (Exception e) {
                                    failTimes++;
                                }
                            }
                            System.out.println("read value, " + name + " fail:" + failTimes);
                            try {
                                Thread.sleep(SLEEP_TIME);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    bakMap.entrySet().stream().forEach(ele -> {
                        testMap.remove(ele.getKey());
                    });
                }
            }
        });

        writeThread.start();
        readThread.start();
    }

    public static void main(String[] args) throws InterruptedException {
        Set<HostAndPort> clusterNodes = new HashSet<HostAndPort>();
        clusterNodes.add(new HostAndPort("192.168.8.231", 7001));
        clusterNodes.add(new HostAndPort("192.168.8.231", 7002));
        clusterNodes.add(new HostAndPort("192.168.8.231", 7003));
        clusterNodes.add(new HostAndPort("192.168.8.231", 7004));
        clusterNodes.add(new HostAndPort("192.168.8.231", 7005));
        clusterNodes.add(new HostAndPort("192.168.8.231", 7006));
        int count = 1000;
        //testStandalone(count);
        //redisCluster(clusterNodes, count);
        testFailover(clusterNodes);
    }

}
