package com.jyong.java.ziputils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ZipUtil;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: jyong
 * @Date: 2021/04/21/10:02
 * @Description: 文件压缩
 */
public class ZipTask {

    private static Logger logger = LoggerFactory.getLogger(ZipTask.class);

    public static void main(String[] args) {
        long one = System.currentTimeMillis();
        zipFilePip();
        long two = System.currentTimeMillis();
        System.out.println(two - one);

        ZipUtil.zip("D:\\data\\sourcedata\\20201216", "D:\\data\\2.zip");
        long three = System.currentTimeMillis();
        System.out.println(three - two);
    }

    private static Long FILE_SIZE = FileUtil.size(new File("D:\\data\\sourcedata\\20201216"));


    public static void zipFilePip() {
        try (WritableByteChannel out = Channels.newChannel(new FileOutputStream("D:\\data\\1.zip"))) {
            Pipe pipe = Pipe.open();
            //异步任务
            CompletableFuture.runAsync(() -> runTask(pipe));

            //获取读通道
            ReadableByteChannel readableByteChannel = pipe.source();
            ByteBuffer buffer = ByteBuffer.allocate(FILE_SIZE.intValue());
            while (readableByteChannel.read(buffer) >= 0) {
                buffer.flip();
                out.write(buffer);
                buffer.clear();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //异步任务
    public static void runTask(Pipe pipe) {

        try (ZipOutputStream zos = new ZipOutputStream(Channels.newOutputStream(pipe.sink()));
             WritableByteChannel out = Channels.newChannel(zos)) {
            System.out.println("Begin");
            String path = "D:\\data\\sourcedata\\20201216";
            for (String fileName : FileUtil.listFileNames("D:\\data\\sourcedata\\20201216")) {
                zos.putNextEntry(new ZipEntry(fileName));

                File file = new File(path + "/" + fileName);

                FileChannel jpgChannel = new FileInputStream(file).getChannel();

                jpgChannel.transferTo(0, FileUtil.size(file), out);

                jpgChannel.close();


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void zipFiles(String filePath, String zipPath, FileSystem fileSystem, String zipFileName) throws Exception {

        String zipFile = zipPath + "/" + zipFileName + ".ZIP";

        //获取一个文件下所有需要压缩的文件
        Collection<String> srcFiles = Arrays.stream(fileSystem.listStatus(new Path(filePath))).map(ele -> ele.getPath().toString()).collect(Collectors.toList());
        // 判断压缩后的文件存在不，不存在则创建
        if (!fileSystem.exists(new Path(zipFile))) {
            fileSystem.createNewFile(new Path(zipFile));
        }
        // 实例化 FileOutputStream，ZipOutputStream 对象
        FSDataOutputStream fileOutputStream = fileSystem.create(new Path(zipFile));
        ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
        // 遍历源文件数组
        for (String srcFile : srcFiles) {
            FSDataInputStream fsDataInputStream = fileSystem.open(new Path(srcFile));
            ZipEntry zipEntry = new ZipEntry(zipFileName + "/" + new File(srcFile).getName());
            zipOutputStream.putNextEntry(zipEntry);
            int len;
            byte[] buffer = new byte[4096];
            while ((len = fsDataInputStream.read(buffer)) != -1) {
                zipOutputStream.write(buffer, 0, len);
            }
            fsDataInputStream.close();
            zipOutputStream.flush();
            zipOutputStream.closeEntry();
        }
        zipOutputStream.close();
        fileOutputStream.close();
        logger.warn("=================================压缩文件完毕:" + zipFile + "==============================");
    }

    public static void zipFiles2(String filePath, String zipPath, FileSystem fileSystem, String zipFileName) throws Exception {

        String zipFile = zipPath + "/" + zipFileName + ".ZIP";

        //获取一个文件下所有需要压缩的文件
        Collection<String> srcFiles = Arrays.stream(fileSystem.listStatus(new Path(filePath))).map(ele -> ele.getPath().toString()).collect(Collectors.toList());
        // 判断压缩后的文件存在不，不存在则创建
        if (!fileSystem.exists(new Path(zipFile))) {
            fileSystem.createNewFile(new Path(zipFile));
        }
        // 实例化 FileOutputStream，ZipOutputStream 对象
        FSDataOutputStream fileOutputStream = fileSystem.create(new Path(zipFile));
        ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
        // 遍历源文件数组
        for (String srcFile : srcFiles) {
            FSDataInputStream fsDataInputStream = fileSystem.open(new Path(srcFile));
            ZipEntry zipEntry = new ZipEntry(zipFileName + "/" + new File(srcFile).getName());
            zipOutputStream.putNextEntry(zipEntry);
            int len;
            byte[] buffer = new byte[4096];
            while ((len = fsDataInputStream.read(buffer)) != -1) {
                zipOutputStream.write(buffer, 0, len);
            }
            fsDataInputStream.close();
            zipOutputStream.flush();
            zipOutputStream.closeEntry();
        }
        zipOutputStream.close();
        fileOutputStream.close();
        logger.warn("=================================压缩文件完毕:" + zipFile + "==============================");
    }

}
