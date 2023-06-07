package com.jyong.java.juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Author jyong
 * @Date 2023/6/4 18:22
 * @desc CompletableFuture
 */

public class CompletableFutureExample {


    public static void main(String[] args) throws Exception {
        demo();
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
