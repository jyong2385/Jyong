package com.jyong.java.juc;

import java.util.concurrent.TimeUnit;

/**
 * @Author jyong
 * @Date 2023/7/5 21:33
 * @desc
 */

public class CaseOfLock {

    /**
     * 资源加锁的8中种案列
     * 线程操作资源类
     * 1.标准访问有ab两个线程,同一部手机，打印邮件还是短信？  A sendEmail ......
     * 2.sendEmail 加入暂停3秒中，请问先打印邮件还是先打印短信？ A sendEmail ......
     * 3。添加一个普通的hello方法，请问先打印邮件还是hello？ hello ....
     * 4。有两部手机，请问先打印邮件还是先打印短信？  B sendSms ......
     * 5。有两个静态同步方法，有1部手机，请问先打印邮件还是先打印短信？ A sendEmail ......
     * 6。有两个静态同步方法，有2部手机，请问先打印邮件还是短信？   A sendEmail .....
     * 7.有1个静态同步方法，有1个普通同步方法，有1部手机，请问先打印邮件（静态）还是先打印短信？B sendSms ......
     * 8。有1个静态同步方法，有1个普通同步方法，有2部手机，请问先打印邮件（静态）还是先打印短信？B sendSms ......
     */

    public static void main(String[] args) {
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            phone1.sendEmail();
        },"a").start();

        new Thread(() -> {
            phone2.sendSms();
//            phone1.hello();
        },"b").start();

    }


}

//资源类
class Phone {

    public static synchronized void sendEmail() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("A sendEmail ......");
    }

    public synchronized void sendSms() {
        System.out.println("B sendSms ......");
    }

    public void hello(){
        System.out.println("hello ....");
    }

}