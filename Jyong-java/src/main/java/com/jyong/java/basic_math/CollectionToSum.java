package com.jyong.java.basic_math;



import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//TODO 利用stream对集合中的数值元素进行求和
public class CollectionToSum {

    public static void main(String[] args) {
        System.out.println(sumOfLists());



    }

    public static Integer sumOfMaps(){
        Map<String, Integer> map = Maps.newHashMap();
            map.put("a",1);
            map.put("b",2);
            map.put("c",3);
         Integer sum=map.values().stream().collect(Collectors.summingInt(x->x));
        return sum;
    }

    public static Integer sumOfLists(){
        List<Integer> list = Lists.newArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
//        return list.stream().collect(Collectors.summingInt(x->x));
        return list.stream().mapToInt(x->x).sum();
    }



}
