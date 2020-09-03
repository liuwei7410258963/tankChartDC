package com.oket.tankchartdc.upload;

/**
 * 全局异常
 *
 * @author dukunbiao(null)  2018-09-23
 * https://github.com/dkbnull/SpringBootDemo
 */
public class GlobalException extends Exception {

    private static final long serialVersionUID = -238091758285157331L;

    private String code;
    private String message;
    public GlobalException(String message) {
        super(message);
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return code + ": " + message;
    }
}
