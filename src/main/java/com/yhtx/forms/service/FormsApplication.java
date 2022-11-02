package com.yhtx.forms.service;

import com.yhtx.forms.constant.FormsConst;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * 数据启动选择数据源，目前暂时选择的jpa
 */
@Slf4j
public class FormsApplication implements ImportBeanDefinitionRegistrar {

    private static Class<?> primarySource;

    private static final Set<String> scanPackage = new HashSet<>();

    public static Class<?> getPrimarySource() {
        return primarySource;
    }

    public static String[] getScanPackage() {
        return scanPackage.toArray(new String[0]);
    }

    @SneakyThrows
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Class<?> clazz = ClassUtils.forName(importingClassMetadata.getClassName(), ClassUtils.getDefaultClassLoader());
        Optional.ofNullable(clazz.getAnnotation(SpringBootApplication.class)).ifPresent(it -> primarySource = clazz);
        try {
            Class.forName("org.springframework.boot.devtools.RemoteUrlPropertyExtractor");
        } catch (ClassNotFoundException ignored) {

        }
        scanPackage.add(FormsConst.BASE_PACKAGE);
    }
}
