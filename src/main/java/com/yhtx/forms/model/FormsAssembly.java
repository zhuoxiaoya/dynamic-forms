package com.yhtx.forms.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yhtx.forms.annotation.FormsProxy;
import com.yhtx.forms.annotation.Title;
import com.yhtx.forms.model.base.BaseModel;
import com.yhtx.forms.repository.FormsAssemblyRepository;
import lombok.Data;
import org.hibernate.annotations.Table;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * 组件
 */
@Data
@Entity
@FormsProxy(FormsAssemblyRepository.class)
@Component
@Table(appliesTo = "forms_assembly",comment = "表单组件表")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class FormsAssembly {

    @Id
    @Title("ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint(11) COMMENT 'ID'")
    private Long id;

    @Title("组件名称")
    @Column(columnDefinition = "varchar(50) COMMENT '组件名称'")
    private String assemblyName;

    @Title("是否必填")
    @Column(columnDefinition = "bit(1) DEFAULT b'0' COMMENT '是否必填'")
    private Boolean required;

    @Title("组件类型")
    @Column(columnDefinition = "varchar(50) COMMENT '组件类型'")
    private String editType;
}
