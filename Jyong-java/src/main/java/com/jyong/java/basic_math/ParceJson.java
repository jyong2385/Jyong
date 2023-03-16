package com.jyong.java.basic_math;

import cn.hutool.json.JSONUtil;

import java.util.List;
import java.util.Map;

public class ParceJson {

    public static void main(String[] args) {
        parseJsonToMap();

    }

    public  static void parseJsonToMap(){
        String json="{\n" +
                "  \"hits\" : {\n" +
                "    \"hits\" : [\n" +
                "      {\n" +
                "        \"_source\" : {\n" +
                "          \"user\" : \"bill\",\n" +
                "          \"age\" : 30,\n" +
                "          \"country\" : \"FR\",\n" +
                "          \"category\" : \"A\"\n" +
                "        }\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}\n";
        System.out.println();

        List<Map<String,Map<String,Object>>> body = (List<Map<String,Map<String,Object>>>)JSONUtil.parse(json).getByPath("hits.hits");
        Map<String, Object> source = body.get(0).getOrDefault("_source", null);
        source.forEach((k,v)-> System.out.println(k+"-----"+v));

    }

}
