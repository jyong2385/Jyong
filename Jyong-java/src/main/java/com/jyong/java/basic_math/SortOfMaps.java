package com.jyong.java.basic_math;

import cn.hutool.core.map.MapUtil;
import com.google.common.collect.Maps;

import java.util.Map;

public class SortOfMaps {

    public static void main(String[] args) {

        Map<String,Integer> map = Maps.newHashMap();
        map.put("a",2);
        map.put("b",1);
        map.put("c",5);
        map.put("d",3);
//        MapUtil.sort(map).forEach((k,v)-> System.out.println(k+"--->"+v));
//        MapUtil.sort(map, (o1, o2) -> StrUtil.compare(o2,o1,false)).forEach((k, v)-> System.out.println(k+"-----------"+v));
        String s = MapUtil.joinIgnoreNull(map, ",", ":");
        System.out.println(s);

    }
}
