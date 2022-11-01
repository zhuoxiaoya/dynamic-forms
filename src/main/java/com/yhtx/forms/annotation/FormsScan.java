package com.yhtx.forms.annotation;

import com.yhtx.forms.service.FormsApplication;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 核心启动注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({FormsApplication.class})
@Comment("Erupt项目包扫描核心注解")
public @interface FormsScan {

    @Comment("需要被扫描的包名")
    String[] value() default {};

}
