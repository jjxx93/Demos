package cn.xjx;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

/**
 * Created by jjxx9 on 2017/3/27.
 */
public class RedisJava {
    public static void main(String[] args) {
        //Connecting to Redis server on localhost
        Jedis jedis = new Jedis("219.223.240.197", 6379);
        jedis.auth("1226");

        //check whether server is running or not
        System.out.println("Server is running: " + jedis.ping());

        // 字符串
        jedis.set("tutorial-name", "Redis tutorial");
        // Get the stored data and print it
        System.out.println("tutorial-name : "+ jedis.get("tutorial-name"));

        // 列表
        jedis.lpush("tutorial-list", "Redis");
        jedis.lpush("tutorial-list", "Mongodb");
        jedis.lpush("tutorial-list", "Mysql");
        // Get the stored data and print it
        List<String> list = jedis.lrange("tutorial-list", 0 ,5);

        for(int i = 0; i < list.size(); i++) {
            System.out.println("tutorial-list: " + list.get(i));
        }

        //store data in redis list
        // Get the stored data and print it
        Set<String> set = jedis.keys("*");

        for (String aSet : set) System.out.println(aSet);
    }
}
