package com.yhtx.forms.controller;

import com.yhtx.forms.annotation.Comment;
import com.yhtx.forms.constant.FormsRestPath;
import com.yhtx.forms.model.vo.FormsBuildModel;
import com.yhtx.forms.model.vo.FormsModel;
import com.yhtx.forms.service.FormsCoreService;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Comment("通过组件名称来获取当前组件的表格列结构")
@RestController
@RequestMapping(FormsRestPath.FORMS_BUILD)
public class FormsBuildController {

    @GetMapping("/{assembly}")
    @SneakyThrows
    public FormsBuildModel getEruptBuild(@PathVariable("assembly") String assemblyName) {
        FormsModel formsModel = FormsCoreService.getFormsView(assemblyName);

        FormsBuildModel eruptBuildModel = new FormsBuildModel();
        eruptBuildModel.setFormsModel(formsModel);

        return eruptBuildModel;
    }
}
