package com.jyong.java.basic;

import org.apache.commons.lang3.tuple.MutablePair;

/**
 * @Author jyong
 * @Date 2024/2/1 18:14
 * @desc
 */

public class MethodReturnTwoResult {

    public static void main(String[] args) {
        MutablePair<String, String> result = getResult();
        System.out.println("left: "+result.getLeft());
        System.out.println("right: "+result.getRight());
    }

    public static MutablePair<String,String> getResult(){
        MutablePair<String,String> mutablePair = new MutablePair<>();
        mutablePair.setLeft("left-value");
        mutablePair.setRight("right-value");
        return mutablePair;
    }
}
