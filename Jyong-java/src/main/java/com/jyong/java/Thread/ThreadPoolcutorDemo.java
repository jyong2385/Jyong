package com.jyong.java.Thread;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolcutorDemo {


    public static void main(String[] args) {
        /**
         * ThreadPoolExecutor(int corePoolSize,                     核心线程池大小
         *                    int maximumPoolSize,                  最大线程池大小
         *                    long keepAliveTime,                   线程最大空闲时间
         *                    TimeUnit unit,                        时间单位
         *                    BlockingQueue<Runnable> workQueue,    线程等待队列
         *                    ThreadFactory threadFactory,          线程创建工厂
         *                    RejectedExecutionHandler handler      拒绝策略
         *                    )
         *
         *1.corePoolSize    核心线程池大小
         *      指定了线程池中的线程数量，它的数量决定了添加的新任务时开辟新的线程去执行，还是放到workQueue队列中去；
         *2.maximumPoolSize 最大线程池大小
         *      指定了线程池中最大的线程数量，这个参数会根据你使用的workQueue任务队列的类型，决定线程池会开辟的最大线程数量；
         *3.keepAliveTime   线程最大空闲时间
         *      当线程池中空闲的线程数量超过corePoolSize时，多余的线程会在多长时间内被销毁；
         *4.unit TimeUnit   时间单位
         *5.workQueue 线程等待队列
         *      任务队列，被添加到线程池中，但尚未被执行的任务；它一般分为直接提交到队列、有届任务队列、无界任务队列、有限任务队列几种；
         *6.threadFactory 线程创建工厂
         *      线程工厂，用于创建线程，一般默认即可；
         *7.handler 拒绝策略
         *      当任务太多来不及处理时，如何拒绝任务；
         *
         */
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2, 3, TimeUnit.SECONDS, new LinkedBlockingDeque<>(3));


    }


}
