package com.yhtx.forms.annotation;
import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FormsProxy {
    Class value();
}
