package com.jyong.java.document;

/**
 * @Author jyong
 * @Date 2024/3/2 15:27
 * @desc
 */

public enum WordTitleStyleEnum {


    LEVEL_TITLE("a3","标题"),
    SUB_LEVEL_TITLE("a5","副标题"),
    FIRST_LEVEL_TITLE("1","一级标题"),
    SECOND_LEVEL_TITLE("2","二级标题"),

    THIRD_LEVEL_TITLE("3","三级标题"),
    FOUR_LEVEL_TITLE("4","四级标题"),
    FIVE_LEVEL_TITLE("5","五级标题");

    public String styleId;
    public String title;

    WordTitleStyleEnum(String styleId, String title){
        this.styleId = styleId;
        this.title = title;
    }
}
