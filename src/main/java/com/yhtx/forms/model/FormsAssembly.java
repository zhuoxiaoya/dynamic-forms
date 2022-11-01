package com.yhtx.forms.model;

import com.yhtx.forms.annotation.Title;
import lombok.Data;
import org.hibernate.annotations.Table;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 组件
 */
@Data
@Entity
@Component
@Table(appliesTo = "forms_assembly",comment = "表单组件表")
public class FormsAssembly {

    @Id
    @Title("组件ID")
    @Column(columnDefinition = "bigint(11) COMMENT '组件ID'")
    private Long assemblyId;

    @Title("组件名称")
    @Column(columnDefinition = "varchar(50) COMMENT '组件名称'")
    private String assemblyName;

    @Title("是否必填")
    @Column(columnDefinition = "bit(1) DEFAULT b'0' COMMENT '是否必填'")
    private boolean required;

    @Title("组件类型")
    @Column(columnDefinition = "varchar(50) COMMENT '组件类型'")
    private String editType;
}
