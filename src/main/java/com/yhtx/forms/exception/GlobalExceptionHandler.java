package com.yhtx.forms.exception;

import com.yhtx.forms.model.api.FormsApiModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {


    // 拦截抛出的异常，@ResponseStatus：用来改变响应状态码
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public FormsApiModel handlerThrowable(Throwable e, HttpServletRequest request) {
        return FormsApiModel.errorApi(e.getMessage());
    }

    // 参数校验异常
    @ExceptionHandler(FormsNoLegalPowerException.class)
    public FormsApiModel handleBindExcpetion(FormsNoLegalPowerException e, HttpServletRequest request) {
        return FormsApiModel.errorApi(e);
    }

    @ExceptionHandler(FormsWebApiRuntimeException.class)
    public FormsApiModel handleMethodArgumentNotValidException(FormsWebApiRuntimeException e, HttpServletRequest request) {
        return FormsApiModel.errorApi(e);
    }
}