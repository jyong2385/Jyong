package com.jyong.java.designpattern;

/**
 * @Auther: wangjunyong
 * @Date: 2021/2/20 13:51
 * @Description:单例模式-饿汉式 所谓饿汉式，和懒汉式不同，见名思意，不管调用者有没有调用对象，都会提前创建好一个对象，当对象被调用时，直接返回被创建的对象。
 */
public class HungrySingleObj {

    //提前创建对象
    private static HungrySingleObj hungrySingleObj = new HungrySingleObj();

    private HungrySingleObj() {
        try {
            Thread.sleep(1000);
            System.out.println("HungrySingleObj.HungrySingleObj(饿汉式模式对象创建了)");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //当调用者调用方法获取对象时直接返回对象
    public static HungrySingleObj getHungrySingleObj() {
        return hungrySingleObj;
    }

}
