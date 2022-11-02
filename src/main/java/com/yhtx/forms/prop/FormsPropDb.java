package com.yhtx.forms.prop;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;

/**
 * jpa数据源情况相关参数
 */
@Getter
@Setter
public class FormsPropDb {

    private FormsPropDataSource datasource;

    private JpaProperties jpa;

    private String[] scanPackages;

}
