package com.yhtx.forms.service;

import com.yhtx.forms.annotation.Comment;
import com.yhtx.forms.model.vo.FormsFieldModel;
import com.yhtx.forms.model.vo.FormsModel;
import com.yhtx.forms.toolkit.TimeRecorder;
import com.yhtx.forms.util.FormsSpringUtil;
import com.yhtx.forms.util.ReflectUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedCaseInsensitiveMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.*;

@Slf4j
@Order(100)
@Service
public class FormsCoreService implements ApplicationRunner {



    @Comment("类名对应抽象组件类集合")
    private static final Map<String, FormsModel> FORMS = new LinkedCaseInsensitiveMap<>();

    @Comment("抽象组件集合")
    private static final List<FormsModel> FORMS_LIST = new ArrayList<>();

    public static FormsModel getForms(String assemblyName) {
        return FORMS.get(assemblyName);
    }

    @SneakyThrows
    public static FormsModel getFormsView(String assemblyName) {
        FormsModel em = getForms(assemblyName).clone();
        for (FormsFieldModel fieldModel : em.getFormsFieldModels()) {
            //todo 这里需要判断一下当前列是否是选择框和标签框
        }
        return em;
    }

    @Comment("根据类名初始化组件抽象类，定义好页面表格的结构")
    private static FormsModel initFormsModel(Class<?> clazz) {
        // forms class data to memory
        FormsModel formsModel = new FormsModel(clazz);
        // forms field data to memory
        formsModel.setFormsFieldModels(new ArrayList<>());
        formsModel.setFormsFieldMap(new LinkedCaseInsensitiveMap<>());
        ReflectUtil.findClassAllFields(clazz, field -> Optional.ofNullable(field.getAnnotation(Column.class))
                .ifPresent(ignore -> {
                    FormsFieldModel eruptFieldModel = new FormsFieldModel(field);
                    formsModel.getFormsFieldModels().add(eruptFieldModel);
                    if(!formsModel.getFormsFieldMap().containsKey(field.getName())){
                        formsModel.getFormsFieldMap().put(field.getName(), eruptFieldModel);
                    }
                    //获取主键
                    if(Objects.nonNull(field.getAnnotation(Id.class))){
                        formsModel.setPrimaryKeyCol(field.getName());
                    }

                }));
        //sort 根据组件的序号来拍
        formsModel.getFormsFieldModels().sort(Comparator.comparingLong((a) -> a.getSort()));
        // annotation validate
        return formsModel;
    }

    private String repeat(String space, int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) sb.append(space);
        return sb.toString();
    }

    @Comment("先初始化，将可以走通用crud的抽象组件对象放置静态map中")
    @Override
    public void run(ApplicationArguments args) throws Exception {
        TimeRecorder totalRecorder = new TimeRecorder();
        FormsSpringUtil.scannerPackage(FormsApplication.getScanPackage(), new TypeFilter[]{
                new AnnotationTypeFilter(Entity.class)
        }, clazz -> {
            FormsModel eruptModel = initFormsModel(clazz);
            FORMS.put(clazz.getSimpleName(), eruptModel);
            FORMS_LIST.add(eruptModel);
        });
        log.info("<" + repeat("===", 20) + ">");

        log.info("FORMS classes : " + FORMS.size());
        log.info("FORMS Framework initialization completed in {}ms", totalRecorder.recorder());
        log.info("<" + repeat("===", 20) + ">");
    }
}
