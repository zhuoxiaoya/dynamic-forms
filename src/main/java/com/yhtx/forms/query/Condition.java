package com.yhtx.forms.query;

import com.yhtx.forms.enums.QueryExpression;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Condition {

    private String key;

    private Object value;

    private transient QueryExpression expression = QueryExpression.EQ;

    public Condition(String key, Object value, QueryExpression expression) {
        this.key = key;
        this.value = value;
        this.expression = expression;
    }

    public Condition(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public Condition(){

    }

}
