package com.yhtx.forms.service;


import com.yhtx.forms.annotation.Comment;
import com.yhtx.forms.model.query.Column;
import com.yhtx.forms.model.query.FormsQuery;
import com.yhtx.forms.model.query.Page;
import com.yhtx.forms.model.vo.FormsModel;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 抽象基本查询相关接口，需要自行实现
 */
public interface IFormsDataService {

    @Comment("根据主键id获取数据")
    Object findDataById(FormsModel formsModel, @Comment("主键值") Object id);

    @Comment("查询分页数据")
    Page queryList(FormsModel formsModel, @Comment("分页对象") Page page, @Comment("条件") FormsQuery formsQuery);

    @Comment("根据列查询相关数据")
    Collection<Map<String, Object>> queryColumn(FormsModel formsModel, @Comment("列信息") List<Column> columns, @Comment("条件") FormsQuery formsQuery);

    @Comment("添加数据")
    void addData(FormsModel formsModel, @Comment("数据对象") Object object);

    @Comment("修改数据")
    void editData(FormsModel formsModel, @Comment("数据对象") Object object);

    @Comment("删除数据")
    void deleteData(FormsModel formsModel, @Comment("数据对象") Object object);

}
