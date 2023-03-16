package com.jyong.java.elasicsearch;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.github.javafaker.Faker;
import com.google.common.collect.Maps;

import java.util.Locale;
import java.util.Map;

public class ElasticSearchOprtator {


    public static void main(String[] args) {

    }


    /**
     * 使用Faker造数据
     *
     * @return
     */
    public static String mockData() {
        Faker faker = new Faker(Locale.CHINA);
        Map<String, Object> hashMap = Maps.newHashMap();
        hashMap.put("name", faker.name().fullName());
        int randomDigit = faker.number().numberBetween(1, 99);
        hashMap.put("sex", randomDigit % 2 == 1 ? "男" : "女");
        hashMap.put("age", randomDigit);
        hashMap.put("birthday", DateUtil.format(faker.date().birthday(), "yyyyMMdd HH:mm:ss"));
        hashMap.put("address", faker.address().fullAddress());
        hashMap.put("education", faker.educator().secondarySchool());
        hashMap.put("job", faker.job().position());
        hashMap.put("phone", faker.phoneNumber().cellPhone());
        hashMap.put("amount", faker.random().nextInt(1, 10000000));
        hashMap.put("creditCard", faker.finance().creditCard());
        hashMap.put("marry", randomDigit % 3 == 1 ? true : false);
        return JSONUtil.toJsonStr(hashMap);
    }

    /***
     * 向指定索引写入数据
     * @param indexName
     * @param json
     * @return
     */
    public static boolean putDataToEs(String indexName, String json) {
        return HttpRequest.post(getAvailableEsUrl() + "/" + indexName)
                .timeout(5 * 1000)
                .body(JSONUtil.toJsonPrettyStr(json))
                .execute()
                .isOk();
    }

    /**
     * 获取Es数据
     *
     * @param indexName
     * @param query
     * @return
     */

    public static String getData(String indexName, String query) {
        return HttpRequest.post(getAvailableEsUrl() + "/" + indexName + "/_search")
                .timeout(8 * 1000)
                .body(query)
                .execute()
                .body();

    }

    /**
     * 删除指定索引
     *
     * @param indexName
     * @return
     */
    public static boolean deleteIndex(String indexName) {
        return HttpRequest.delete(getAvailableEsUrl() + "/" + indexName).execute().isOk();
    }
    
    private static String getAvailableEsUrl(){
        return "http://localhost:9200";
    }


}
