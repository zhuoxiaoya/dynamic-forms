package com.yhtx.forms.model.api;

import lombok.Getter;
import lombok.Setter;

/**
 * 通用调取接口返回
 */
@Getter
@Setter
public class FormsApiModel {

    private Status status;

    private PromptWay promptWay;

    private String message;

    private Object data;

    private boolean errorIntercept = true;

    public FormsApiModel(Status status, String message, Object data, PromptWay promptWay) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.promptWay = promptWay;
    }

    public FormsApiModel(Status status, String message, PromptWay promptWay) {
        this.status = status;
        this.message = message;
        this.promptWay = promptWay;
    }

    public static FormsApiModel successApi() {
        return new FormsApiModel(Status.SUCCESS, null, null, PromptWay.MESSAGE);
    }

    public static FormsApiModel successApi(String message, Object data) {
        return new FormsApiModel(Status.SUCCESS, message, data, PromptWay.MESSAGE);
    }

    public static FormsApiModel successApi(Object data) {
        return new FormsApiModel(Status.SUCCESS, null, data, PromptWay.MESSAGE);
    }

    public static FormsApiModel errorApi(String message) {
        return new FormsApiModel(Status.ERROR, message, null, PromptWay.DIALOG);
    }

    public static FormsApiModel errorNoInterceptApi(String message) {
        FormsApiModel eruptApiModel = new FormsApiModel(Status.ERROR, message, null, PromptWay.DIALOG);
        eruptApiModel.errorIntercept = false;
        return eruptApiModel;
    }

    public static FormsApiModel errorNoInterceptMessage(String message) {
        FormsApiModel eruptApiModel = new FormsApiModel(Status.ERROR, message, null, PromptWay.MESSAGE);
        eruptApiModel.errorIntercept = false;
        return eruptApiModel;
    }

    public static FormsApiModel errorApi(Exception e) {
        e.printStackTrace();
        return new FormsApiModel(Status.ERROR, e.getMessage(), null, PromptWay.DIALOG);
    }

    public enum Status {
        SUCCESS, ERROR, INFO, WARNING
    }

    public enum PromptWay {
        DIALOG, MESSAGE, NOTIFY, NONE
    }

}


