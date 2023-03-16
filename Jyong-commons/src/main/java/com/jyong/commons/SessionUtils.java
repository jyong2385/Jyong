package com.jyong.commons;

import cn.hutool.core.util.StrUtil;
import cn.hutool.db.DbUtil;
import cn.hutool.db.Session;
import cn.hutool.db.ds.simple.SimpleDataSource;
import cn.hutool.http.HttpRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by jyong on 2021/1/23 16:12
 */
public class SessionUtils{


    public static Properties getProperties() {
        InputStream resourceAsStream = SessionUtils.class.getResourceAsStream("config.properties");
        Properties properties = new Properties();
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }


    public static Session initDbSession() {
        SimpleDataSource simpleDataSource = new SimpleDataSource(getProperties().getProperty("mysql.url"), getProperties().getProperty("mysql.user"), getProperties().getProperty("mysql.password"), getProperties().getProperty("mysql.driver"));
        return DbUtil.newSession(simpleDataSource);
    }

    public static String getAvailableEsUrl() {

        String url = "http://{}:{}";
        for (String node : getProperties().getProperty("es.nodes").split(",")) {
            url = StrUtil.format(url, node, getProperties().getProperty("es.port"));
            int status = HttpRequest.get(url)
                    .timeout(1000 * 5)
                    .execute()
                    .getStatus();
            if (status == 200) {
                return url;
            }

        }
        return "ERROR CLASTER NODES";
    }

}
