package com.yhtx.forms.service;

import com.yhtx.forms.enums.QueryExpression;
import com.yhtx.forms.exception.FormsNoLegalPowerException;
import com.yhtx.forms.exception.FormsWebApiRuntimeException;
import com.yhtx.forms.invoke.DataProcessorManager;
import com.yhtx.forms.model.query.FormsQuery;
import com.yhtx.forms.model.query.Page;
import com.yhtx.forms.model.query.TableQueryVo;
import com.yhtx.forms.model.vo.FormsModel;
import com.yhtx.forms.query.Condition;
import com.yhtx.forms.util.DataHandlerUtil;
import com.yhtx.forms.util.FormsUtil;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.*;

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
        Page page = new Page(tableQueryVo.getPageIndex(),tableQueryVo.getPageSize(),tableQueryVo.getSort());
        JpaRepository formsRepository = FormsCoreService.getFormsRepository(formsModel.getFormsName());
        Pageable pageable = PageRequest.of(tableQueryVo.getPageIndex(), tableQueryVo.getPageSize());
        try {
            Object o = formsModel.getClazz().newInstance();
            //设置请求参数到对象内
            FormsUtil.setCondition(o,CollectionToMap(legalConditions));
            org.springframework.data.domain.Page repositoryAll = formsRepository.findAll(Example.of(o), pageable);
            page.setTotal(repositoryAll.getTotalElements());
            page.setTotalPage(repositoryAll.getTotalPages());
            page.setSort(repositoryAll.getSort().toString());
            page.setList(repositoryAll.getContent());
        } catch (InstantiationException e) {
            throw new FormsWebApiRuntimeException("实例化异常");
        } catch (IllegalAccessException e) {
            throw new FormsWebApiRuntimeException("访问权限异常");
        }
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
        Page page = DataProcessorManager.getFormsDataProcessor(formsModel.getClazz())
                .queryList(formsModel, new Page(0, 1, null),
                        FormsQuery.builder().conditions(conditions).build());
        if (page.getList().size() <= 0) {
            throw new FormsNoLegalPowerException();
        }
    }

    /**
     * 将请求参数转化为map格式
     * @param conditions
     * @return
     */
    public static Map<String,Object> CollectionToMap(List<Condition> conditions){
        Map<String,Object> map = new HashMap<>();
        for(Condition condition : conditions){
            map.put(condition.getKey(),condition.getValue());
        }
        return map;
    }
}
