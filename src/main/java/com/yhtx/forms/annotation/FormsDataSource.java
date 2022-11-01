package com.yhtx.forms.annotation;


import java.lang.annotation.*;

/**
 * 多数据源情况下DB选择
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Comment("多数据源注解")
public @interface FormsDataSource {

    @Comment("多数据源名称：forms.dbs[i].datasource.name")
    String value();
}
