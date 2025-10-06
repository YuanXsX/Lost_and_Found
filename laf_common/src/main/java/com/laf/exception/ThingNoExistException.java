package com.laf.exception;

/**
 * 事物不存在异常
 */
public class ThingNoExistException extends BaseException {
    public ThingNoExistException(String msg) {
        super(msg);
    }
}
