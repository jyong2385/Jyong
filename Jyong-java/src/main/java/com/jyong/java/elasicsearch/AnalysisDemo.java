package com.jyong.java.elasicsearch;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

import java.util.List;

/**
 * @Auther: wangjunyong
 * @Date: 2021/2/19 14:11
 * @Description:
 */
public class AnalysisDemo {

    public static void main(String[] args) {
        String str = "欢迎使用ansj_seg,(ansj中文分词)在这里如果你遇到什么问题都可以联系我.我一定尽我所能.帮助大家.ansj_seg更快,更准,更自由!";
        //分词结果的一个封装，主要是一个List<Term>的terms
        Result result = ToAnalysis.parse(str);
        System.out.println(result.getTerms());

        List<Term> terms = result.getTerms();
        for (int i = 0; i < terms.size(); i++) {

            String name = terms.get(i).getName();
            String natureStr = terms.get(i).getNatureStr();
            System.out.println("词："+name+"    词性："+natureStr);
        }
    }

}
