package com.yhtx.forms.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yhtx.forms.annotation.Title;
import com.yhtx.forms.util.CloneSupport;
import com.yhtx.forms.util.TypeUtil;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

@Data
@JsonIgnoreProperties(value = {"fieldReturnName","field"})
public class FormsFieldModel extends CloneSupport<FormsFieldModel> {

    private transient String fieldReturnName;

    private transient Field field;

    private String fieldName;

    private String title;

    private Long sort = 0L;

    //仅前端使用
    private List<VLModel> choiceList;

    //仅前端使用
    private List<String> tagList;

    public FormsFieldModel(Field field) {
        this.field = field;
        this.fieldName = field.getName();
        //数字类型转换
        if (TypeUtil.isNumberType(field.getType().getSimpleName())) {
            this.fieldReturnName = "number";
        } else {
            this.fieldReturnName = field.getType().getSimpleName();
        }

        Title title = field.getAnnotation(Title.class);
        if(Objects.nonNull(title)){
            this.title = title.value();
        }
    }
}
