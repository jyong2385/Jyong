package com.jyong.java.mysql;

import cn.hutool.db.DbUtil;
import cn.hutool.db.Entity;
import cn.hutool.db.Session;
import cn.hutool.db.ds.simple.SimpleDataSource;
import com.jyong.commons.conf.Constants;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author jyong
 * @Date 2023/6/3 13:31
 * @desc
 */

public class MySqlClient {

    public static void main(String[] args) throws SQLException {

        SimpleDataSource dataSource = new SimpleDataSource(Constants.MYSQL_V8_URL, Constants.MYSQL_V8_USER, Constants.MYSQL_V8_PASSWORD,Constants.MYSQL_V8_DRIVER);
        Session session = DbUtil.newSession(dataSource);
        List<Entity> query = session.query("select * from personal_info");

        query.stream().forEach(System.out::println);


    }


}
