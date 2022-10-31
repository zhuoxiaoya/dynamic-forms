package com.yhtx.forms.annotation;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({java.lang.annotation.ElementType.TYPE})
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Documented
@EntityScan(basePackages = {"com.yhtx"})
@ComponentScan(basePackages = {"com.yhtx"})
@EnableJpaRepositories(basePackages = {"com.yhtx"})
public @interface EnableDynamicForms {

}
