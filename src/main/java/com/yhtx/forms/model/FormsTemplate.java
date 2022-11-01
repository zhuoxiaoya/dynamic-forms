package com.yhtx.forms.model;

import com.yhtx.forms.annotation.Title;
import com.yhtx.forms.model.base.BaseModel;
import lombok.Data;
import org.hibernate.annotations.Table;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Component
@Table(appliesTo = "forms_template",comment = "表单模版表")
public class FormsTemplate extends BaseModel {

    @Title("模版名称")
    @Column(columnDefinition = "varchar(50) COMMENT '模版名称'")
    private String name;

    @Title("表名称")
    @Column(columnDefinition = "varchar(50) COMMENT '表名称'")
    private String tableName;

    @Title("创建时间")
    @Column(columnDefinition = "datetime DEFAULT NULL COMMENT '创建时间'")
    private Date createTime;

    @Title("人员id")
    @Column(columnDefinition = "bigint(20) NOT NULL COMMENT '人员id'")
    private Long createUserId;
}
