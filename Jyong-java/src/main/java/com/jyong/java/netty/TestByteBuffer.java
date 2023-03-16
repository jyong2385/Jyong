package com.jyong.java.netty;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author jyong
 * @date 2022年08月20日 12:45
 * @desc:
 */
public class TestByteBuffer {

    public static void main(String[] args) {
        // step1:输入输出流
        try (FileChannel channel = new FileInputStream("D:\\codding\\Datawaiter\\Datawaiter-Java\\src\\main\\resources\\data.txt").getChannel()) {

            //step2 准备缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(10);

            while (channel.read(buffer) > 0) {
                //step4 切换至读模式
                buffer.flip();
                while (buffer.hasRemaining()) {
                    byte b = buffer.get();
                    System.out.println((char) b);
                }
                //step5 切换写模式
                buffer.clear();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
