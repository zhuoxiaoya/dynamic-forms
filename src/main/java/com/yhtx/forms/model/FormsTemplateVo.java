package com.yhtx.forms.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yhtx.forms.annotation.FormsProxy;
import com.yhtx.forms.annotation.Title;
import com.yhtx.forms.model.base.BaseModel;
import com.yhtx.forms.repository.FormsTemplateVoRepository;
import lombok.Data;
import org.hibernate.annotations.Table;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Entity
@FormsProxy(FormsTemplateVoRepository.class)
@Component
@Table(appliesTo = "forms_template_vo",comment = "表单模版视图表")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class FormsTemplateVo {

    @Id
    @Title("ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint(11) COMMENT 'ID'")
    private Long id;

    @Title("组件ID")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "assembly_id",columnDefinition = "bigint(11) COMMENT '组件ID'")
    private FormsAssembly formsAssembly;

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
