package com.jyong.springboot.designpattern.factory;

import com.jyong.springboot.enumcommon.ProcessEnumType;

/**
 * @Author jyong
 * @Date 2023/10/18 19:25
 * @desc
 */

public interface ProcessService {

    void process();

    ProcessEnumType getType();

}
