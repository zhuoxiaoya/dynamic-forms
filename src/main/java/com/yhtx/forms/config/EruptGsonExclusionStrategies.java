package com.yhtx.forms.config;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.yhtx.forms.annotation.Comment;
import lombok.SneakyThrows;


/**
 * Gson初始化变量参数
 */
public class EruptGsonExclusionStrategies implements ExclusionStrategy {

    @Comment("判断是否需要跳过字段")
    @Override
    @SneakyThrows
    public boolean shouldSkipField(FieldAttributes f) {
        return false;
    }

    @Override
    public boolean shouldSkipClass(Class<?> incomingClass) {
        return false;
    }

}
