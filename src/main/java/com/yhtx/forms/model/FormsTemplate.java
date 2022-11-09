package com.yhtx.forms.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yhtx.forms.annotation.FormsProxy;
import com.yhtx.forms.annotation.Title;
import com.yhtx.forms.model.base.BaseModel;
import com.yhtx.forms.repository.FormsTemplateRepository;
import lombok.Data;
import org.hibernate.annotations.Table;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@FormsProxy(FormsTemplateRepository.class)
@Component
@Table(appliesTo = "forms_template",comment = "表单模版表")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class FormsTemplate {

    @Id
    @Title("ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint(11) COMMENT 'ID'")
    private Long id;

    @Title("模版名称")
    @Column(columnDefinition = "varchar(50) COMMENT '模版名称'")
    private String name;

    @Title("表名称,名称唯一")
    @Column(unique = true,columnDefinition = "varchar(50) COMMENT '表名称'")
    private String tableName;

    @Title("创建时间")
    @Column(columnDefinition = "datetime DEFAULT NULL COMMENT '创建时间'")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Title("人员id")
    @Column(columnDefinition = "bigint(20) NOT NULL COMMENT '人员id'")
    private Long createUserId;

    @Title("模版视图")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "forms_template_id",columnDefinition = "bigint(11) COMMENT '模版ID'")
    private Set<FormsTemplateVo> formsTemplateVoSet;
}
