package com.yhtx.forms.model.query;

import com.yhtx.forms.query.Condition;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 通用数据对象
 */
@Getter
@Setter
@Builder
public class FormsQuery {
    List<Condition> conditions;

    List<String> conditionStrings;

    String orderBy;
}
