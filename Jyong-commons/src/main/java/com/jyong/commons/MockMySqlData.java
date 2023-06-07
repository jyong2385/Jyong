package com.jyong.commons;

/**
 * @Author jyong
 * @Date 2023/6/3 14:15
 * @desc
 */

import com.jyong.commons.conf.Constants;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.math.BigInteger;
import java.sql.*;
import java.security.*;
import java.util.*;
import java.util.Date;

public class MockMySqlData {
    public static void main(String[] args) {
        String url = Constants.MYSQL_V8_URL;
        String user = Constants.MYSQL_V8_USER;
        String password = Constants.MYSQL_V8_PASSWORD;
        int numRecords = 100000;

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // 生成随机数据
            List<String[]> records = new ArrayList<>();
            for (int i = 0; i < numRecords; i++) {
                String firstName = md5(String.valueOf(Math.random())).substring(0, 5);
                String lastName = md5(String.valueOf(Math.random())).substring(0, 5);
                String email = md5(String.valueOf(Math.random())).substring(0, 10) + "@example.com";
                String phone = "1" + String.format("%010d", new Random().nextInt(1000000000));
                Date birthdate = new Date(System.currentTimeMillis() - (long)(Math.random() * 365 * 50 * 24 * 60 * 60 * 1000));
                String gender = Math.random() < 0.5 ? "M" : "F";
                String address = md5(String.valueOf(Math.random())).substring(0, 10) + "号楼";
                String city = md5(String.valueOf(Math.random())).substring(0, 5);
                String state = md5(String.valueOf(Math.random())).substring(0, 5);
                String zipCode = String.format("%06d", new Random().nextInt(1000000));
                String country = "中国";
                records.add(new String[]{firstName, lastName, email, phone, DateFormatUtils.format(birthdate,"yyyy-MM-dd HH:mm:ss"), gender, address, city, state, zipCode, country});
            }

            // 插入数据
            String sql = "INSERT INTO personal_info (first_name, last_name, email, phone, birthdate, gender, address, city, state, zip_code, country) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                for (String[] record : records) {
                    for (int i = 0; i < record.length; i++) {
                        stmt.setString(i + 1, record[i]);
                    }
                    stmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashText = no.toString(16);
            while (hashText.length() < 32) {
                hashText = "0" + hashText;
            }
            return hashText;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
