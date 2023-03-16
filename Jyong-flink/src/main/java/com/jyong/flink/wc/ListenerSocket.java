package com.jyong.flink.wc;

import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class ListenerSocket {

    public static void main(String[] args) throws Exception {

        ParameterTool parameterTool = ParameterTool.fromArgs(args);
        System.out.println("--------- the task is start runing --------- ");
        String host = parameterTool.get("host");
        int port = parameterTool.getInt("port");
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStreamSource<String> dataStreamSource = env.socketTextStream(host, port);

        dataStreamSource.flatMap(new StreamWordCount.myFlatMapper())
                .keyBy(0)
                .sum(1)
                .print();


        env.execute();


    }

}
