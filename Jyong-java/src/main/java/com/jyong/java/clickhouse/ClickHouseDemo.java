package com.jyong.java.clickhouse;


import java.sql.*;

/**
 * @author jyong
 * @date 2022年08月16日 21:14
 * @desc:
 */
public class ClickHouseDemo {

    public static void main(String[] args) throws SQLException {

        //定义变量
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;

        try {

            //step1:加载Clickhouse驱动类
            Class.forName("ru.yandex.clickhouse.ClickHouseDriver");

            //setp2:获取链接Connection
            conn = DriverManager.getConnection("");

            //step3:创建PreparedStatement sql预处理对象
            String sql = "select count(1) from table_name";
            pstmt = conn.prepareStatement(sql);

            //step4: 执行查询
            result = pstmt.executeQuery();

            //step5:获取结果数据
            while (result.next()) {
                System.out.println(result.getString(0));
            }


        } catch (Exception e) {
            e.printStackTrace();

        } finally {

            //step6:关闭连接
            if (result != null)
                result.close();

            if (pstmt != null)
                pstmt.close();

            if (conn != null)
                conn.close();

        }
    }
}
