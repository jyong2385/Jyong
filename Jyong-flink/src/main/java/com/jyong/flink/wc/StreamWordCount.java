package com.jyong.flink.wc;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

public class StreamWordCount {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();


        DataStreamSource<String> dataStreamSource = env.readTextFile("D:\\codding\\Somnambulist\\Somnambulist-flink-streaming\\src\\main\\resources\\words");


        dataStreamSource.flatMap(new myFlatMapper()).keyBy(0)
                .sum(1)
                .print();

        env.execute();

    }

    public static class myFlatMapper implements  FlatMapFunction<String,Tuple2<String,Integer>>{

        @Override
        public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
            for (String v : s.split(" ")) {
                collector.collect(new Tuple2<>(v, 1));
            }
        }
    }

}
