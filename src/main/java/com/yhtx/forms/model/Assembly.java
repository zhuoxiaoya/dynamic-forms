package com.yhtx.forms.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 组件
 */
@Entity
public class Assembly {

    /**
     * 组件ID
     */
    @Id
    private Long assemblyId;

    /**
     * 组件名称
     */
    private String name;

    /**
     * 是否必填
     */
    private boolean required;

    /**
     * 组件类型
     */
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
