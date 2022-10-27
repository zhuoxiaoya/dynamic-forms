package com.yhtx.forms.model;

import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 组件
 */
@Entity
@Table(appliesTo = "assembly",comment = "表单组件表")
public class Assembly{

    /**
     * 组件ID
     */
    @Id
    @Column(columnDefinition = "bigint(11) COMMENT '组件ID'")
    private Long assemblyId;

    /**
     * 组件名称
     */
    @Column(columnDefinition = "varchar(50) COMMENT '组件名称'")
    private String name;

    /**
     * 是否必填
     */
    @Column(columnDefinition = "varchar(50) COMMENT '是否必填'")
    private boolean required;

    /**
     * 组件类型
     */
    @Column(columnDefinition = "varchar(50) COMMENT '组件类型'")
    private String editType;



    public Long getAssemblyId() {
        return assemblyId;
    }

    public void setAssemblyId(Long assemblyId) {
        this.assemblyId = assemblyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getEditType() {
        return editType;
    }

    public void setEditType(String editType) {
        this.editType = editType;
    }
}
