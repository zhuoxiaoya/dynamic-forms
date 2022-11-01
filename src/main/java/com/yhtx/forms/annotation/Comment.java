package com.yhtx.forms.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Target;

/**
 * 注释注解
 */
@Repeatable(Comments.class)
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.FIELD, ElementType.TYPE_PARAMETER, ElementType.PARAMETER})
@Documented
public @interface Comment {

    String value() default "";

    Language language() default Language.ZH;

    enum Language {
        ZH, EN
    }

}