package com.yhtx.forms.invoke;


import com.yhtx.forms.constant.FormsConst;
import com.yhtx.forms.service.IFormsDataService;
import com.yhtx.forms.util.FormsSpringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author YuePeng
 * date 2020/12/4 16:24
 */
public class DataProcessorManager {

    private static final Map<String, Class<? extends IFormsDataService>> formsDataServiceMap = new HashMap<>();

    public static void register(String name, Class<? extends IFormsDataService> eruptDataService) {
        formsDataServiceMap.put(name, eruptDataService);
    }

    public static IFormsDataService getEruptDataProcessor(Class<?> clazz) {
        return FormsSpringUtil.getBean(formsDataServiceMap.get(
                FormsConst.DEFAULT_DATA_PROCESSOR));
    }
}
