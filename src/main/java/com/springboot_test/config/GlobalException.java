package com.springboot_test.config;

import lombok.Getter;

/**
 * 自定义业务异常
 *
 * @author Tanwei
 * @date 2019-06-20 09:56
 */
@Getter
public class GlobalException extends RuntimeException {

    String code;

    String message;

    public GlobalException() {
    }

    public GlobalException(String code) {
        this.code = code;
    }

    public GlobalException(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
