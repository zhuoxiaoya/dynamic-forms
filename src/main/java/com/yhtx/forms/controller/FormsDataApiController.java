package com.yhtx.forms.controller;

import com.yhtx.forms.annotation.Comment;
import com.yhtx.forms.constant.FormsRestPath;
import com.yhtx.forms.invoke.DataProcessorManager;
import com.yhtx.forms.model.query.Page;
import com.yhtx.forms.model.query.TableQueryVo;
import com.yhtx.forms.model.vo.FormsModel;
import com.yhtx.forms.service.FormsCoreService;
import com.yhtx.forms.service.FormsService;
import com.yhtx.forms.util.FormsUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping(FormsRestPath.FORMS_DATA)
public class FormsDataApiController {

    private final FormsService formsService;

    @Comment("组件表格查询，返回分页数据对象")
    @PostMapping({"/table/{assembly}"})
    public Page getFormsData(@PathVariable("assembly") String assemblyName, @RequestBody TableQueryVo tableQueryVo) {
        return formsService.getFormsData(FormsCoreService.getForms(assemblyName), tableQueryVo, null);
    }

    @Comment("通过组件ID获取组件详情")
    @GetMapping("/{assembly}/{id}")
    public Map<String, Object> getFormsDataById(@PathVariable("assembly") String assemblyName, @PathVariable("id") String id) {
        FormsModel formsModel = FormsCoreService.getForms(assemblyName);
        formsService.verifyIdPermissions(formsModel, id);
        Object data = DataProcessorManager.getFormsDataProcessor(formsModel.getClazz())
                .findDataById(formsModel, FormsUtil.toFormsId(formsModel, id));
        return FormsUtil.generateEruptDataMap(formsModel, data);
    }
}
