package com.yhtx.forms.data.jpa;

import com.yhtx.forms.annotation.Comment;
import com.yhtx.forms.constant.FormsConst;
import com.yhtx.forms.data.jpa.dao.FormsJpaDao;
import com.yhtx.forms.data.jpa.dao.FormsJpaUtils;
import com.yhtx.forms.exception.FormsWebApiRuntimeException;
import com.yhtx.forms.invoke.DataProcessorManager;
import com.yhtx.forms.model.query.Column;
import com.yhtx.forms.model.query.FormsQuery;
import com.yhtx.forms.model.query.Page;
import com.yhtx.forms.model.vo.FormsModel;
import com.yhtx.forms.service.IFormsDataService;
import com.yhtx.forms.util.ReflectUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.transaction.Transactional;
import java.util.*;

@Comment("数据源默认实现使用jpa")
@Service
public class FormsDataServiceDbImpl implements IFormsDataService {

    static {
        DataProcessorManager.register(FormsConst.DEFAULT_DATA_PROCESSOR, FormsDataServiceDbImpl.class);
    }

    @Resource
    private FormsJpaDao eruptJpaDao;

    @Resource
    private EntityManagerService entityManagerService;

    @Override
    public Object findDataById(FormsModel formsModel, Object id) {
        return entityManagerService.getEntityManager(formsModel.getClazz(), (em) -> em.find(formsModel.getClazz(), id));
    }

    @Override
    public Page queryList(FormsModel formsModel, Page page, FormsQuery formsQuery) {
        return eruptJpaDao.queryFormsList(formsModel, page, formsQuery);
    }


    @Override
    public void addData(FormsModel formsModel, Object data) {
        try {
            eruptJpaDao.addEntity(formsModel.getClazz(), data);
        } catch (Exception e) {
            handlerException(e, formsModel);
        }
    }

    @Transactional
    @Override
    public void editData(FormsModel eruptModel, Object data) {
        try {
            eruptJpaDao.editEntity(eruptModel.getClazz(), data);
        } catch (Exception e) {
            handlerException(e, eruptModel);
        }
    }

    //优化异常提示类
    private void handlerException(Exception e, FormsModel eruptModel) {
        e.printStackTrace();
        if (e instanceof DataIntegrityViolationException) {
            if (e.getMessage().contains("ConstraintViolationException")) {
                throw new FormsWebApiRuntimeException("数据字段重复");
            } else if (e.getMessage().contains("DataException")) {
                throw new FormsWebApiRuntimeException("内容超出数据库限制长度");
            } else {
                throw new FormsWebApiRuntimeException(e.getMessage());
            }
        } else {
            throw new FormsWebApiRuntimeException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public void deleteData(FormsModel eruptModel, Object object) {
        try {
            eruptJpaDao.removeEntity(eruptModel.getClazz(), object);
        } catch (DataIntegrityViolationException | ConstraintViolationException e) {
            e.printStackTrace();
            throw new FormsWebApiRuntimeException("删除失败，可能存在关联数据，无法直接删除");
        } catch (Exception e) {
            throw new FormsWebApiRuntimeException(e.getMessage());
        }
    }


    /**
     * 根据列获取相关数据
     *
     * @param eruptModel eruptModel
     * @param columns    列
     * @param query      查询对象
     * @return 数据结果集
     */
    @Override
    public Collection<Map<String, Object>> queryColumn(FormsModel eruptModel, List<Column> columns, FormsQuery query) {
        StringBuilder hql = new StringBuilder();
        List<String> columnStrList = new ArrayList<>();
        columns.forEach(column -> columnStrList.add(FormsJpaUtils.completeHqlPath(eruptModel.getFormsName()
                , column.getName()) + " as " + column.getAlias()));
        hql.append("select new map(").append(String.join(", ", columnStrList))
                .append(") from ").append(eruptModel.getFormsName()).append(" as ").append(eruptModel.getFormsName());
        ReflectUtil.findClassAllFields(eruptModel.getClazz(), field -> {
            if (null != field.getAnnotation(ManyToOne.class) || null != field.getAnnotation(OneToOne.class)) {
                hql.append(" left outer join ").append(eruptModel.getFormsName()).append(".")
                        .append(field.getName()).append(" as ").append(field.getName());
            }
        });
        hql.append(" where 1 = 1 ");
        Optional.ofNullable(query.getConditions()).ifPresent(c -> c.forEach(it -> hql.append(FormsJpaUtils.AND).append(it.getKey()).append('=').append(it.getValue())));
        Optional.ofNullable(query.getConditionStrings()).ifPresent(c -> c.forEach(it -> hql.append(FormsJpaUtils.AND).append(it)));

        if (StringUtils.isNotBlank(query.getOrderBy())) {
            hql.append(" order by ").append(query.getOrderBy());
        }
        return entityManagerService.getEntityManager(eruptModel.getClazz(), (em) -> em.createQuery(hql.toString()).getResultList());
    }


}
