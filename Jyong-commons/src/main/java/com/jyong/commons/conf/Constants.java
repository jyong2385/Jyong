package com.jyong.commons.conf;

/**
 * @Auther: wangjunyong
 * @Date: 2021/2/5 11:07
 * @Description:
 */
public class Constants {
    /**
     * kafka配置信息
     */
    public static final String KAFKA_BOOTSTRAP_SERVERS = "127.0.0.1:9092";
    public static final String KAFKA_TOPIC = "test";
    public static final String KAFKA_GROUP_ID = "group-jyong";

    /**
     * mysql连接信息
     */
    public static final String MYSQL_V8_DRIVER="com.mysql.cj.jdbc.Driver";
    public static final String MYSQL_V8_URL="jdbc:mysql://127.0.0.1:3306/jyong?useSSL=false&serverTimezone=UTC";
    public static final String MYSQL_V8_USER="root";
    public static final String MYSQL_V8_PASSWORD="123456";

}
