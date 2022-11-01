package com.yhtx.forms.model;

import com.yhtx.forms.annotation.Title;
import com.yhtx.forms.model.base.BaseModel;
import lombok.Data;
import org.hibernate.annotations.Table;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Entity
@Component
@Table(appliesTo = "forms_template_vo",comment = "表单模版视图表")
public class FormsTemplateVo extends BaseModel {

    @Title("模版ID")
    @Column(columnDefinition = "bigint(11) COMMENT '模版ID'")
    private Long templateId;

    @Title("组件ID")
    @Column(columnDefinition = "bigint(11) COMMENT '组件ID'")
    private Long assemblyId;

    @Title("组件名称")
    @Column(columnDefinition = "varchar(50) COMMENT '组件名称'")
    private String assemblyName;

    @Title("表单列名称")
    @Column(columnDefinition = "varchar(50) COMMENT '表单列名称'")
    private String labelName;

    @Title("表列名")
    @Column(columnDefinition = "varchar(50) COMMENT '表列名'")
    private String columnName ;

    @Title("是否必填")
    @Column(columnDefinition = "bit(1) DEFAULT b'0' COMMENT '是否必填'")
    private Boolean required;

    @Title("排序")
    @Column(columnDefinition = "int(5) COMMENT '排序'")
    private Long sort;
}
