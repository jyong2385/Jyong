package com.jyong.java.designpattern;

/**
 * @Auther: wangjunyong
 * @Date: 2021/2/20 13:42
 * @Description: 单例模式-懒汉式
 * 懒汉式模式，主要思想是创建对象的时候我们无需提前创建好，当调用者开始调用的时候我们才开始进行创建
 */
public class LazySingleObj {


    /**
     * 声明对象--无需提前创建对象，当调用者调式时才创建
     */
    private static LazySingleObj lazySingleObj = null;

    private LazySingleObj() {
        try {
            Thread.sleep(1000);
            System.out.println("LazySingleObj懒汉式模式队对象创建了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //当调用者进行调用发方获取对象时，进行创建
    private static LazySingleObj getLazySingleObj() {
        //进行双重检查
        if (lazySingleObj == null)
            synchronized (LazySingleObj.class) {
                if (lazySingleObj == null)
                    lazySingleObj = new LazySingleObj();
            }
        return lazySingleObj;
    }


}
