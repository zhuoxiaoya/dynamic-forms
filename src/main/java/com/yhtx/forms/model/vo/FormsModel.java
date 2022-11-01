package com.yhtx.forms.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yhtx.forms.util.CloneSupport;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@JsonIgnoreProperties(value = {"clazz","formsFieldMap"})
public class FormsModel implements Cloneable{

    private transient Class<?> clazz;

    private transient Map<String, FormsFieldModel> formsFieldMap;

    private String formsName;

    private String primaryKeyCol;

    private List<FormsFieldModel> formsFieldModels;

    public FormsModel(Class<?> formsClazz) {
        this.clazz = formsClazz;
        this.formsName = formsClazz.getSimpleName();
    }

    @Override
    public final FormsModel clone() throws CloneNotSupportedException {
        FormsModel formsModel = (FormsModel) super.clone();
        formsModel.formsFieldModels = formsFieldModels.stream().map(CloneSupport::clone)
                .collect(Collectors.toList());
        return formsModel;
    }

}
