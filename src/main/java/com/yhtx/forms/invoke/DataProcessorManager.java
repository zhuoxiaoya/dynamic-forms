package com.yhtx.forms.invoke;


import com.yhtx.forms.constant.FormsConst;
import com.yhtx.forms.service.IFormsDataService;
import com.yhtx.forms.util.FormsSpringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据处理器管理器
 */
public class DataProcessorManager {

    private static final Map<String, Class<? extends IFormsDataService>> formsDataServiceMap = new HashMap<>();

    public static void register(String name, Class<? extends IFormsDataService> eruptDataService) {
        formsDataServiceMap.put(name, eruptDataService);
    }

    public static IFormsDataService getFormsDataProcessor(Class<?> clazz) {
        return FormsSpringUtil.getBean(formsDataServiceMap.get(
                FormsConst.DEFAULT_DATA_PROCESSOR));
    }
}
