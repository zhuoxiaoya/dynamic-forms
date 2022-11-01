package com.yhtx.forms.exception;

import lombok.Getter;

import java.util.Map;

/**
 * runtime exception
 */
@Getter
public class FormsWebApiRuntimeException extends RuntimeException {

    private boolean printStackTrace;

    //异常信息扩展
    private Map<String, Object> extraMap;

    public FormsWebApiRuntimeException(String message) {
        this(message, true);
    }

    public FormsWebApiRuntimeException(String message, boolean printStackTrace) {
        super(message);
        this.printStackTrace = printStackTrace;
    }

    public FormsWebApiRuntimeException(Map<String, Object> extraMap, boolean printStackTrace) {
        this.printStackTrace = printStackTrace;
        this.extraMap = extraMap;
    }

    public FormsWebApiRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

}
