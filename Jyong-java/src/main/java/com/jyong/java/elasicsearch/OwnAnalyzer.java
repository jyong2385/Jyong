package com.jyong.java.elasicsearch;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.library.DicLibrary;
import org.ansj.recognition.impl.StopRecognition;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.ansj.util.MyStaticValue;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: wangjunyong
 * @Date: 2021/2/19 14:31
 * @Description:
 */
public class OwnAnalyzer {

    public static void main(String[] args) {
        //关闭名字识别
        MyStaticValue.isNameRecognition = false;
        //配置自定义词典的位置
        MyStaticValue.ENV.put(DicLibrary.DEFAULT, "D:\\github\\Somnambulist\\Somnambulist-java\\src\\main\\resources\\userLibrary.dic");
        //去停用词
        List<String> stopWords = getStopWords("D:\\github\\Somnambulist\\Somnambulist-java\\src\\main\\resources\\stopLibrary.dic");
        StopRecognition filter = new StopRecognition();
        filter.insertStopWords(stopWords);

        String str = "欢迎使用ansj_seg,(ansj中文分词)在这里如果你遇到什么问题都可以联系我.我一定尽我所能.帮助大家.ansj_seg更快,更准,更自由!";
        Result result = ToAnalysis.parse(str).recognition(filter);
        List<Term> terms = result.getTerms();

        System.out.println(terms);
        for (int i = 0; i < terms.size(); i++) {
            String word = terms.get(i).getName(); //拿到词
            String natureStr = terms.get(i).getNatureStr(); //拿到词性
            System.out.println(word + ":" + natureStr);
        }
    }

    private static List<String> getStopWords(String url) {
        //使用一个字符串集合来存储文本中的路径，也可用String[] 数组
        ArrayList<String> list = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(url);
            //防止路径乱码
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);

            String line = "";
            while ((line = br.readLine()) != null) {
                //如果txt文件中不包含---字符串 这里是对里面内容的一个筛选
                if (line.lastIndexOf("---") < 0) {
                    list.add(line);
                }
            }
            br.close();
            isr.close();
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
