package com.yhtx.forms.service;

import com.yhtx.forms.enums.QueryExpression;
import com.yhtx.forms.exception.FormsNoLegalPowerException;
import com.yhtx.forms.invoke.DataProcessorManager;
import com.yhtx.forms.model.query.FormsQuery;
import com.yhtx.forms.model.query.Page;
import com.yhtx.forms.model.query.TableQueryVo;
import com.yhtx.forms.model.vo.FormsModel;
import com.yhtx.forms.query.Condition;
import com.yhtx.forms.util.DataHandlerUtil;
import com.yhtx.forms.util.FormsUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 组件查询服务
 */
@Service
public class FormsService {


    /**
     * 通用查询
     * @param formsModel 查询对象
     * @param tableQueryVo 前端查询对象
     * @param serverCondition 条件字符串
     * @return
     */
    public Page getFormsData(FormsModel formsModel, TableQueryVo tableQueryVo, List<Condition> serverCondition,String... customCondition) {
        List<Condition> legalConditions = FormsUtil.geneFormsSearchCondition(formsModel, tableQueryVo.getCondition());
        List<String> conditionStrings = new ArrayList<>();
        conditionStrings.addAll(Arrays.asList(customCondition));

        Optional.ofNullable(serverCondition).ifPresent(legalConditions::addAll);
        Page page = DataProcessorManager.getEruptDataProcessor(formsModel.getClazz())
                .queryList(formsModel, new Page(tableQueryVo.getPageIndex(), tableQueryVo.getPageSize(), tableQueryVo.getSort()),
                        FormsQuery.builder().orderBy(tableQueryVo.getSort()).conditionStrings(conditionStrings).conditions(legalConditions).build());
        Optional.ofNullable(page.getList()).ifPresent(it -> DataHandlerUtil.convertDataToFormsView(formsModel, it));
        return page;
    }


    /**
     * 校验id使用权限
     * @param formsModel formsModel
     * @param id         标识主键
     */
    public void verifyIdPermissions(FormsModel formsModel, String id) {
        List<Condition> conditions = new ArrayList<>();
        conditions.add(new Condition(formsModel.getPrimaryKeyCol(), id, QueryExpression.EQ));
        Page page = DataProcessorManager.getEruptDataProcessor(formsModel.getClazz())
                .queryList(formsModel, new Page(0, 1, null),
                        FormsQuery.builder().conditions(conditions).build());
        if (page.getList().size() <= 0) {
            throw new FormsNoLegalPowerException();
        }
    }
}
