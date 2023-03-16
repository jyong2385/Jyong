package com.jyong.java;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.text.ParseException;
import java.util.List;

public class Launcher {
    public static void main(String[] args) throws ParseException {


        String str = "[1,2]";
        List<Long> list = JSON.parseObject(str, new TypeReference<List<Long>>() {
        });
        System.out.println(list);


    }
}
