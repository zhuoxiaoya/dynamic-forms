package com.yhtx.forms.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yhtx.forms.annotation.FormsProxy;
import com.yhtx.forms.annotation.Title;
import com.yhtx.forms.repository.FormsTemplateVoValueRepository;
import lombok.Data;
import org.hibernate.annotations.Table;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Entity
@FormsProxy(FormsTemplateVoValueRepository.class)
@Component
@Table(appliesTo = "forms_template_vo_value",comment = "表单模版组件对应value")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class FormsTemplateVoValue {

    @Id
    @Title("ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint(11) COMMENT 'ID'")
    private Long id;

    @Title("模版组件视图")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "forms_template_vo_id",columnDefinition = "bigint(11) COMMENT '模版组件视图ID'")
    private FormsTemplateVo formsTemplateVo;

    @Title("表单组件列收集值")
    @Column(columnDefinition = "text COMMENT '表单组件列收集值'")
    private String value;
}
