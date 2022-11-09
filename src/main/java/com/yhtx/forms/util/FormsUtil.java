package com.yhtx.forms.util;

import com.google.gson.JsonObject;
import com.yhtx.forms.annotation.Comment;
import com.yhtx.forms.annotation.Title;
import com.yhtx.forms.enums.QueryExpression;
import com.yhtx.forms.enums.SceneEnum;
import com.yhtx.forms.model.api.FormsApiModel;
import com.yhtx.forms.model.vo.FormsFieldModel;
import com.yhtx.forms.model.vo.FormsModel;
import com.yhtx.forms.query.Condition;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 实体类转换相关util
 */
public class FormsUtil {

    //将object中的字段抽取出来放到map中
    @SneakyThrows
    public static Map<String, Object> generateEruptDataMap(FormsModel formsModel, Object obj) {
        Map<String, Object> map = new HashMap<>();
        for (FormsFieldModel fieldModel : formsModel.getFormsFieldModels()) {
            Field field = fieldModel.getField();
            field.setAccessible(true);
            Object value = field.get(obj);
            if (null != value) {
                map.put(field.getName(), value);
            }
        }
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

    @Comment("校验数据非空判断")
    public static FormsApiModel validDateFormsValue(FormsModel formsModel, JsonObject data) {
        return FormsApiModel.successApi();
    }

    @Comment("清理序列化后对象所产生的默认值（通过json串进行校验）")
    public static void clearObjectDefaultValueByJson(Object obj, JsonObject data) {
        ReflectUtil.findClassAllFields(obj.getClass(), field -> {
            try {
                field.setAccessible(true);
                if (null != field.get(obj)) {
                    if (!data.has(field.getName())) {
                        field.set(obj, null);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

    @Comment("将对象A的非空数据源覆盖到对象B中")
    public static Object dataTarget(FormsModel formsModel, Object data, Object target, SceneEnum sceneEnum) {
        ReflectUtil.findClassAllFields(formsModel.getClazz(), f -> Optional.ofNullable(f.getAnnotation(Title.class)).ifPresent(eruptField -> {
                try {
                    f.setAccessible(true);
                    f.set(target, f.get(data));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
        }));
        return target;
    }

    @Comment("将请求参数里到请求参数设置到对象内")
    public static void setCondition(Object paramObj,Map<String,Object> conditions){
        ReflectUtil.findClassAllFields(paramObj.getClass(), field -> {
            try {
                field.setAccessible(true);
                if(conditions.containsKey(field.getName())){
                    Object value = conditions.get(field.getName());
                    if(field.getName().equals("id")){
                        Long longValue = Long.parseLong(value.toString());
                        field.setLong(paramObj,longValue);
                    }else {
                        field.set(paramObj,value);
                    }

                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }
}
