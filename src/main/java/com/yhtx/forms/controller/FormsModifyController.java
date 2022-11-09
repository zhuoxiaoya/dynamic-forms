package com.yhtx.forms.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.yhtx.forms.config.GsonFactory;
import com.yhtx.forms.constant.FormsRestPath;
import com.yhtx.forms.enums.SceneEnum;
import com.yhtx.forms.invoke.DataProcessorManager;
import com.yhtx.forms.model.api.FormsApiModel;
import com.yhtx.forms.model.vo.FormsModel;
import com.yhtx.forms.service.FormsCoreService;
import com.yhtx.forms.service.FormsService;
import com.yhtx.forms.service.IFormsDataService;
import com.yhtx.forms.util.FormsUtil;
import com.yhtx.forms.util.ReflectUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(FormsRestPath.FORMS_DATA_MODIFY)
@RequiredArgsConstructor
public class FormsModifyController {

    private final Gson gson = GsonFactory.getGson();

    private final FormsService formsService;

    @SneakyThrows
    @PostMapping({"/{forms}"})
    @Transactional
    public FormsApiModel addFormsData(@PathVariable("forms") String forms, @RequestBody Map<String,Object> map,
                                               JsonObject jsonObject, HttpServletRequest request) {
        FormsModel formsModel = FormsCoreService.getForms(forms);
        JsonObject data = new Gson().fromJson(gson.toJson(map),JsonObject.class);
        FormsApiModel formsApiModel = FormsUtil.validDateFormsValue(formsModel, data);
        if (formsApiModel.getStatus() == FormsApiModel.Status.ERROR) return formsApiModel;
        Object o = gson.fromJson(data.toString(), formsModel.getClazz());
        FormsUtil.clearObjectDefaultValueByJson(o, data);
        Object obj = FormsUtil.dataTarget(formsModel, o, formsModel.getClazz().newInstance(), SceneEnum.ADD);
        if (null != jsonObject) {
            for (String key : jsonObject.keySet()) {
                Field field = ReflectUtil.findClassField(formsModel.getClazz(), key);
                field.setAccessible(true);
                field.set(obj, gson.fromJson(jsonObject.get(key).toString(), field.getType()));
            }
        }
        DataProcessorManager.getFormsDataProcessor(formsModel.getClazz()).addData(formsModel, obj);
        this.modifyLog(formsModel, "ADD", data.toString());
        return FormsApiModel.successApi();
    }

    @PutMapping("/{forms}")
    @Transactional
    public FormsApiModel editFormsData(@PathVariable("forms") String forms, @RequestBody Map<String,Object> map) throws IllegalAccessException {
        FormsModel formsModel = FormsCoreService.getForms(forms);
        JsonObject data = new Gson().fromJson(gson.toJson(map),JsonObject.class);
        FormsApiModel formsApiModel = FormsUtil.validDateFormsValue(formsModel, data);
        if (formsApiModel.getStatus() == FormsApiModel.Status.ERROR) return formsApiModel;
        formsService.verifyIdPermissions(formsModel, data.get(formsModel.getPrimaryKeyCol()).getAsString());
        Object o = this.gson.fromJson(data.toString(), formsModel.getClazz());
        FormsUtil.clearObjectDefaultValueByJson(o, data);
        DataProcessorManager.getFormsDataProcessor(formsModel.getClazz()).editData(formsModel, o);
        this.modifyLog(formsModel, "EDIT", data.toString());
        return FormsApiModel.successApi();
    }

    @DeleteMapping("/{forms}/{id}")
    @Transactional
    public FormsApiModel deleteFormsData(@PathVariable("forms") String forms, @PathVariable("id") String id) {
        FormsModel formsModel = FormsCoreService.getForms(forms);
        formsService.verifyIdPermissions(formsModel, id);
        IFormsDataService dataService = DataProcessorManager.getFormsDataProcessor(formsModel.getClazz());
        //获取对象数据信息用于DataProxy函数中
        Object obj = dataService.findDataById(formsModel, FormsUtil.toFormsId(formsModel, id));
        dataService.deleteData(formsModel, obj);
        this.modifyLog(formsModel, "DELETE", id);
        return FormsApiModel.successApi();
    }

    
    @Transactional
    @DeleteMapping("/{forms}")
    public FormsApiModel deleteFormsDataList(@PathVariable("forms") String forms, @RequestParam("ids") String[] ids) {
        FormsApiModel formsApiModel = FormsApiModel.successApi();
        for (String id : ids) {
            formsApiModel = this.deleteFormsData(forms, id);
            if (formsApiModel.getStatus() == FormsApiModel.Status.ERROR) {
                break;
            }
        }
        return formsApiModel;
    }

    private void modifyLog(FormsModel formsModel, String placeholder, String content) {
        log.info("[" + formsModel.getFormsName() + " -> " + placeholder + "]:" + content);
    }
}
