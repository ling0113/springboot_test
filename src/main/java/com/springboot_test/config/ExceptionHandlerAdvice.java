package com.springboot_test.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName: com.springboot_test.config.ExceptionHandlerAdvice
 * @Description: 全局异常捕获
 * @Author: lgrong
 * @CreateDate: 2020/7/16 11:21
 * @Version: 1.0
 */
//@RestControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

    @ExceptionHandler({Exception.class})
    public String badRequestException(Exception exception) {
        System.out.println(exception);
        return "错误";
    }



}
