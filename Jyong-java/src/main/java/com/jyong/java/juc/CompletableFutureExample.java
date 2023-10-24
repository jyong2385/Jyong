package com.jyong.java.juc;

import com.alibaba.fastjson.JSON;

import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @Author jyong
 * @Date 2023/6/4 18:22
 * @desc CompletableFuture
 */

public class CompletableFutureExample {


    public static void main(String[] args) throws Exception {
        //自定义线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        whenCompleteDemo2(threadPool);

        threadPool.shutdown();
    }
    private static void whenCompleteDemo2(ExecutorService threadPool) throws ExecutionException, InterruptedException {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {

            System.out.println(Thread.currentThread().getName() + " step1 running 1");
            try {

                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
//            int a = 1/0;

            return "one";
        },threadPool).whenComplete(new BiConsumer<String, Throwable>() {
            //当上一步完成后，携带返回结果到下一步
            @Override
            public void accept(String s, Throwable throwable) {
                System.out.println(Thread.currentThread().getName() + " step2 running 2");

                if(throwable != null){
                    System.out.println("step2 receive error "+ throwable.getCause());
                }else{
                    System.out.println("receive one result :"+s);
                }
            }
        }).exceptionally(new Function<Throwable, String>() {
            //这里是专门处理异常
            @Override
            public String apply(Throwable throwable) {
                throwable.printStackTrace();
                return Thread.currentThread().getName() +"获取到异常信息 原因是："+throwable.getMessage()+" "+ JSON.toJSONString(throwable.getCause());
            }
        });

        System.out.println(Thread.currentThread().getName() +" step3 running main result ——> "+future.get());


    }


    private static void whenCompleteDemo1() throws ExecutionException, InterruptedException {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {

            System.out.println(Thread.currentThread().getName() +" step1 running 1");
            try {

                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int a = 1/0;

            return "one";
        }).whenComplete(new BiConsumer<String, Throwable>() {
            //当上一步完成后，携带返回结果到下一步
            @Override
            public void accept(String s, Throwable throwable) {
                System.out.println(Thread.currentThread().getName() +" step2 running 2");

                if(throwable != null){
                    System.out.println("step2 receive error "+ throwable.getCause());
                }else{
                    System.out.println(Thread.currentThread().getName() + " receive one result :"+s);
                }
            }
        }).exceptionally(new Function<Throwable, String>() {
            //这里是专门处理异常
            @Override
            public String apply(Throwable throwable) {
                throwable.printStackTrace();
                return Thread.currentThread().getName() +" 获取到异常信息 原因是："+throwable.getMessage()+" "+ JSON.toJSONString(throwable.getCause());
            }
        });

        System.out.println(Thread.currentThread().getName() + " step3 running main result ——> "+future.get());


    }

    public static void demo() throws Exception {
        CompletableFuture.runAsync(() -> {
            System.out.println("runAsync ......");
        });

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync ...... ");
            return "success";
        });
        String s = completableFuture.get();
        System.out.println(s);
    }

}
