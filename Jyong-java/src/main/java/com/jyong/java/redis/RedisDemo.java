package com.jyong.java.redis;

import redis.clients.jedis.Jedis;

public class RedisDemo {

    private   static  Jedis jedis = new Jedis("127.0.0.1", 6379);

    public static void main(String[] args) {
//        putData("name","20200918");
        System.out.println(getStrKey("name"));
    }








    //String API
    private static String getStrKey(String key){

        return  jedis.get(key);
    }

    public static String putData(String key,String value){
        return  jedis.set(key,value);
    }



}

