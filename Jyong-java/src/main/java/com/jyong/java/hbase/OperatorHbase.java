package com.jyong.java.hbase;

import com.jyong.java.entity.Person;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OperatorHbase {

    public static void main(String[] args) throws IOException, SQLException {
        //删除表
//        deleteTable("person");
        //创建person表
//        String[] cols={"info"};
//        createTable("person",cols);
//        //插入数据
//        SimpleDataSource simpleDataSource = new SimpleDataSource("jdbc:mysql://192.168.92.103/jyong", "root", "123456");
//        Db db = DbUtil.use(simpleDataSource);

//        List<Entity> entities = db.findAll("person");
//
//        for (Entity entity : entities) {
//            Person person = new Person();
//            person.setId(entity.getStr("id"));
//            person.setName(entity.getStr("name"));
//            person.setSex(entity.getStr("sex"));
//            insertData("person",person);
//        }
        getNoDealData("person");
    }



    //连接集群
    public  static Connection initBbaseConnn() throws IOException {
        Configuration configuration = HBaseConfiguration.create();

        configuration.set(HConstants.ZOOKEEPER_CLIENT_PORT,"2181");
        configuration.set(HConstants.ZOOKEEPER_QUORUM,"node01,node02,node03");
        Connection connection = ConnectionFactory.createConnection(configuration);
        return connection;
    }

    //创建表
    public  static void  createTable(String tableName,String[] colFamilys) throws IOException {
        TableName tableName1 = TableName.valueOf(tableName);
        Admin admin = initBbaseConnn().getAdmin();
        if(admin.tableExists(tableName1)){
            System.out.println("=====hbase表已存在====："+tableName1);
        }else{
            HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName1);
            for (String col : colFamilys) {
                HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(col);
                hTableDescriptor.addFamily(hColumnDescriptor);
            }
            admin.createTable(hTableDescriptor);
        }
        System.out.println("执行成功！！！");

    }

    //插入数据
    public static void insertData(String tableName, Person person) throws IOException {
        TableName tableName1 = TableName.valueOf(tableName);
        Put put = new Put(("person-" + person.getId()).getBytes());
        //参数：1.列族名 2.列名 3.值
        put.addColumn("info".getBytes(),"id".getBytes(),person.getId().getBytes());
        put.addColumn("info".getBytes(),"name".getBytes(),person.getName().getBytes());
        put.addColumn("info".getBytes(),"sex".getBytes(),person.getSex().getBytes());
        Table table = initBbaseConnn().getTable(tableName1);
        table.put(put);
        System.out.println("执行成功！！！");
    }

    //获取原始数据
    public  static  void getNoDealData(String tablename) throws IOException {
        Table table = initBbaseConnn().getTable(TableName.valueOf(tablename));
        Scan scan = new Scan();

        ResultScanner resultScanner = table.getScanner(scan);
        for (Result result : resultScanner) {
            System.out.println("scan: "+result);
        }

    }

    //根据rowKey进行查询
    public  static Person getDataByRowkey(String rowkey, String tablename) throws IOException {
        Table table = initBbaseConnn().getTable(TableName.valueOf(tablename));
        Get get = new Get(rowkey.getBytes());
        Person person = new Person();
        person.setId(rowkey);
        //判断是否有此条数据
        if(!get.isCheckExistenceOnly()){
            Result result = table.get(get);
            for (Cell cell : result.rawCells()) {
                String colName = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
                String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                if(colName.equals("id")){
                    person.setId(value);
                }
                if(colName.equals("name")){
                    person.setId(value);
                }
                if(colName.equals("sex")){
                    person.setId(value);
                }
            }
        }
        return person;
    }

    //查询指定cell数据
    public static String getCellData(String tablename,String rowkey,String colFamily,String col){
        try {
            Table table = initBbaseConnn().getTable(TableName.valueOf(tablename));
            String result = null;
            Get get = new Get(rowkey.getBytes());

            //判断rowkey是否存在
            if(!get.isCheckExistenceOnly()){
                get.addColumn(Bytes.toBytes(colFamily),Bytes.toBytes(col));
                Result result1 = table.get(get);
                byte[] value = result1.getValue(Bytes.toBytes(colFamily), Bytes.toBytes(col));
                return Bytes.toString(value);
            }else{
                System.out.println("查询结果不存在！！！");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Error!!!";
    }

    //查詢指定表明中的所有數據
    public  static List<Person> getAllData(String tableName) throws IOException {

        ArrayList<Person> list = new ArrayList<>();

        Table table = initBbaseConnn().getTable(TableName.valueOf(tableName));

        ResultScanner resultScanner = table.getScanner(new Scan());
        for (Result result : resultScanner) {
            String id = new String(result.getRow());
            System.out.println("====用戶名==="+new String(result.getRow()));
            Person person = new Person();
            for (Cell cell : result.rawCells()) {
                String colunm = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
                String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                if(colunm.equals("id")){
                    person.setId(value);
                }
                if(colunm.equals("name")){
                    person.setId(value);
                }
                if(colunm.equals("sex")){
                    person.setId(value);
                }

            list.add(person);
            }

        }
        return list;
    }

    //删除指定cell的数据
    public  static  void delByRowkey(String tableName,String rowkey) throws IOException {
        Table table = initBbaseConnn().getTable(TableName.valueOf(tableName));
        Delete delete = new Delete(Bytes.toBytes(rowkey));
        //删除指定列
//        Delete delete1 = delete.addColumn(Bytes.toBytes("sex"), Bytes.toBytes("id"));
        table.delete(delete);
        System.out.println("删除成功！！！");
    }

    //删除表
    public  static  void deleteTable(String tableName) throws IOException {
        TableName tableName1 = TableName.valueOf(tableName);
        Admin admin = initBbaseConnn().getAdmin();
        admin.disableTable(tableName1);
        admin.deleteTable(tableName1);
        System.out.println("删除成功！！！");
    }

}
