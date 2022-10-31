package com.yhtx.forms.model;

import org.hibernate.annotations.Table;

import javax.persistence.*;

@Entity
@Table(appliesTo = "template",comment = "表单模版视图表")
public class TemplateVo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint(11) COMMENT 'ID'")
    private Long templateVoId;

    @Column(columnDefinition = "bigint(11) COMMENT '模版ID'")
    private Long templateId;

    @Column(columnDefinition = "bigint(11) COMMENT '组件ID'")
    private Long assemblyId;

    @Column(columnDefinition = "varchar(50) COMMENT '组件名称'")
    private String assemblyName;

    @Column(columnDefinition = "varchar(50) COMMENT '表单列名称'")
    private String label;

    @Column(columnDefinition = "bit(1) DEFAULT b'0' COMMENT '是否必填'")
    private Boolean required;

    @Column(columnDefinition = "int(5) COMMENT '排序'")
    private Long sort;

    public Long getTemplateVoId() {
        return templateVoId;
    }

    public void setTemplateVoId(Long templateVoId) {
        this.templateVoId = templateVoId;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public Long getAssemblyId() {
        return assemblyId;
    }

    public void setAssemblyId(Long assemblyId) {
        this.assemblyId = assemblyId;
    }

    public String getAssemblyName() {
        return assemblyName;
    }

    public void setAssemblyName(String assemblyName) {
        this.assemblyName = assemblyName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }
}
