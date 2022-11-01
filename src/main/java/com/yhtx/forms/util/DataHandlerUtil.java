package com.yhtx.forms.util;

import com.yhtx.forms.annotation.Comment;
import com.yhtx.forms.model.vo.FormsFieldModel;
import com.yhtx.forms.model.vo.FormsModel;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据显示包装层
 */
public class DataHandlerUtil {

    @Comment("包装数据")
    public static void convertDataToFormsView(FormsModel formsModel, Collection<Map<String, Object>> list) {
        Map<String, Map<String, String>> choiceItems = new HashMap<>();
        for (Map<String, Object> map : list) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                FormsFieldModel fieldModel = cycleFindFieldByKey(formsModel, entry.getKey());
                if (null == fieldModel) {
                    continue;
                }
                map.put(entry.getKey(), convertColumnValue(fieldModel, entry.getValue(), choiceItems));
                }
            }
        }

    private static Object convertColumnValue(FormsFieldModel fieldModel, Object value, Map<String, Map<String, String>> choiceItems) {
        if (null == value) return null;
        //todo 需要判断选择框和布尔类型的数据
        return value;
    }

    private static FormsFieldModel cycleFindFieldByKey(FormsModel formsModel, String key) {
        FormsFieldModel fieldModel = formsModel.getFormsFieldMap().get(key);
        if (null != fieldModel) {
            return fieldModel;
        }
        if (key.contains("_")) {
            return cycleFindFieldByKey(formsModel, key.substring(0, key.lastIndexOf("_")));
        }
        return null;
    }

}
