package com.yhtx.forms.util;

import com.yhtx.forms.annotation.Comment;
import com.yhtx.forms.enums.QueryExpression;
import com.yhtx.forms.model.vo.FormsFieldModel;
import com.yhtx.forms.model.vo.FormsModel;
import com.yhtx.forms.query.Condition;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 实体类转换相关util
 */
public class FormsUtil {

    //将object中erupt标识的字段抽取出来放到map中
    @SneakyThrows
    public static Map<String, Object> generateEruptDataMap(FormsModel formsModel, Object obj) {
        Map<String, Object> map = new HashMap<>();
        return map;
    }

    public static Object toFormsId(FormsModel formsModel, String id) {
        Field primaryField = ReflectUtil.findClassField(formsModel.getClazz(), formsModel.getPrimaryKeyCol());
        return TypeUtil.typeStrConvertObject(id, primaryField.getType());
    }

    public static Object convertObjectType(FormsFieldModel formsFieldModel, Object obj) {
        if (null == obj) {
            return null;
        }
        if (null == formsFieldModel) {
            return obj.toString();
        }
        String str = obj.toString();

        switch (formsFieldModel.getField().getGenericType().toString()) {
            case "java.util.date":
                if (isDateField(formsFieldModel.getFieldReturnName())) {
                    return DateUtil.getDate(str);
                } else {
                    return str;
                }
            default:
                return TypeUtil.typeStrConvertObject(str, formsFieldModel.getField().getType());
        }
    }

    //是否为时间字段
    public static boolean isDateField(String fieldType) {
        if (Date.class.getSimpleName().equals(fieldType)) {
            return true;
        } else if (LocalDate.class.getSimpleName().equals(fieldType)) {
            return true;
        } else {
            return LocalDateTime.class.getSimpleName().equals(fieldType);
        }
    }

    @Comment("生成合法的查询条件，条件判断为eq或是like")
    public static List<Condition> geneFormsSearchCondition(FormsModel formsModel, List<Condition> searchCondition) {
        List<Condition> legalConditions = new ArrayList<>();
        if(Objects.nonNull(searchCondition)){
            for (Condition condition : searchCondition) {
                condition.setExpression(QueryExpression.EQ);
                legalConditions.add(condition);
            }
        }
        return legalConditions;
    }
}
