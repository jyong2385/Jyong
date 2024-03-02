package com.jyong.java.document;


import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFStyles;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

public class WordDocumentGenerator {

    public static void main(String[] args) throws Exception {
        readDocWord();
//        createDocWordWithTitle();
    }

    private static void readDocWord() throws Exception{
        XWPFDocument data = new XWPFDocument(new FileInputStream("test1.docx"));
        List<XWPFParagraph> paragraphs = data.getParagraphs();
        for (XWPFParagraph paragraph : paragraphs) {
            String text = paragraph.getText();
            String style = paragraph.getStyle();
            String styleID = paragraph.getStyleID();
            System.out.println(style+" "+styleID+" "+text);

        }


    }

    private static  void createDocWordWithTitle() throws Exception{

        // 新建的word文档对象
        XWPFDocument doc = new XWPFDocument();

        // word整体样式
        // 读取模板文档
        XWPFDocument template = new XWPFDocument(new FileInputStream("template.docx"));
        // 获得模板文档的整体样式
        CTStyles wordStyles = template.getStyle();
        // 获取新建文档对象的样式
        XWPFStyles newStyles = doc.createStyles();
        // 关键行// 修改设置文档样式为静态块中读取到的样式
        newStyles.setStyles(wordStyles);


        //标题
        XWPFParagraph title = doc.createParagraph();
        title.setStyle(WordTitleStyleEnum.LEVEL_TITLE.styleId);
        XWPFRun run0 = title.createRun();
        run0.setText("标题");

        //副标题
        XWPFParagraph subTitle = doc.createParagraph();
        subTitle.setStyle(WordTitleStyleEnum.SUB_LEVEL_TITLE.styleId);
        XWPFRun subTitleRun = subTitle.createRun();
        subTitleRun.setText("副标题");


        // 一级标题
        XWPFParagraph para1 = doc.createParagraph();
        // 关键行// 1级大纲
        para1.setStyle(WordTitleStyleEnum.FIRST_LEVEL_TITLE.styleId);
        XWPFRun run1 = para1.createRun();
        // 标题内容
        run1.setText("一级标题");

        // 标题2，2级大纲
        XWPFParagraph para2 = doc.createParagraph();
        // 关键行// 2级大纲
        para2.setStyle(WordTitleStyleEnum.SECOND_LEVEL_TITLE.styleId);
        XWPFRun run2 = para2.createRun();
        // 标题内容
        run2.setText("二级标题");

        //三级标题
        // 标题3，3级大纲
        XWPFParagraph para3 = doc.createParagraph();
        // 关键行// 3级大纲
        para3.setStyle(WordTitleStyleEnum.THIRD_LEVEL_TITLE.styleId);
        XWPFRun run3 = para3.createRun();
        // 标题内容
        run3.setText("三级标题");

        //四级标题
        // 标题4，4级大纲
        XWPFParagraph para4 = doc.createParagraph();
        // 关键行// 4级大纲
        para4.setStyle(WordTitleStyleEnum.FOUR_LEVEL_TITLE.styleId);
        XWPFRun run4 = para4.createRun();
        // 标题内容
        run4.setText("四级标题");

        // 正文
        XWPFParagraph paraX = doc.createParagraph();
        XWPFRun runX = paraX.createRun();
        // 正文内容
        runX.setText("这是正文。。。。。。");

        // word写入到文件
        FileOutputStream fos;
        try {
            fos = new FileOutputStream("test1.docx");
            doc.write(fos);
            fos.close();
        } catch (Exception e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }
}
