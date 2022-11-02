package com.yhtx.forms.prop;

import com.yhtx.forms.config.HikariCpConfig;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * 数据源
 */
@Getter
@Setter
public class FormsPropDataSource {

    private String name;

    private String driverClassName;

    private String url;

    private String username;

    private String password;

    @NestedConfigurationProperty
    private HikariCpConfig hikari = new HikariCpConfig();

}
