package com.yhtx.forms.model;

import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(appliesTo = "forms_template",comment = "表单模版表")
public class FormsTemplate {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint(11) COMMENT 'ID'")
    private Long templateId;

    @Column(columnDefinition = "varchar(50) COMMENT '模版名称'")
    private String name;

    @Column(columnDefinition = "varchar(50) COMMENT '表名称'")
    private String tableName;

    @Column(columnDefinition = "datetime DEFAULT NULL COMMENT '创建时间'")
    private Date createTime;

    @Column(columnDefinition = "bigint(20) NOT NULL COMMENT '人员id'")
    private Long createUserId;

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }
}
