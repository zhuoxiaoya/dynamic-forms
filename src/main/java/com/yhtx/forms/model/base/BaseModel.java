package com.yhtx.forms.model.base;

import com.yhtx.forms.annotation.Title;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 最顶层组件父类
 */
@Data
@MappedSuperclass
public class BaseModel implements Serializable {

    @Id
    @Title("ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint(11) COMMENT 'ID'")
    private Long id;

}
