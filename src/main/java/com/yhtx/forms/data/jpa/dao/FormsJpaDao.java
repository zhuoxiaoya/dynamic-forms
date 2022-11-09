package com.yhtx.forms.data.jpa.dao;

import com.yhtx.forms.annotation.FormsDataSource;
import com.yhtx.forms.data.jpa.EntityManagerService;
import com.yhtx.forms.model.query.FormsQuery;
import com.yhtx.forms.model.query.Page;
import com.yhtx.forms.model.vo.FormsFieldModel;
import com.yhtx.forms.model.vo.FormsModel;
import com.yhtx.forms.query.Condition;
import com.yhtx.forms.util.FormsUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author YuePeng
 * date 2018-10-11.
 */
@Repository
public class FormsJpaDao {

    @Resource
    private EntityManagerService entityManagerService;

    public void addEntity(Class<?> eruptClass, Object entity) {
        entityManagerService.entityManagerTran(eruptClass, (em) -> {
            em.persist(entity);
            em.flush();
        });
    }

    public void editEntity(Class<?> eruptClass, Object entity) {
        entityManagerService.entityManagerTran(eruptClass, (em) -> {
            em.merge(entity);
            em.flush();
        });
    }

    public void removeEntity(Class<?> eruptClass, Object entity) {
        entityManagerService.entityManagerTran(eruptClass, (em) -> {
            FormsDataSource formsDataSource = eruptClass.getAnnotation(FormsDataSource.class);
            if (null == formsDataSource) {
                em.remove(entity);
            } else {
                em.remove(em.merge(entity));
            }
            em.flush();
        });
    }

    public Page queryFormsList(FormsModel eruptModel, Page page, FormsQuery formsQuery) {
        String hql = FormsJpaUtils.generateEruptJpaHql(eruptModel, "new map(" + String.join(",", FormsJpaUtils.getEruptColJpaKeys(eruptModel)) + ")", formsQuery, false);
        String countHql = FormsJpaUtils.generateEruptJpaHql(eruptModel, "count(*)", formsQuery, true);
        return entityManagerService.getEntityManager(eruptModel.getClazz(), entityManager -> {
            Query query = entityManager.createQuery(hql);
            Query countQuery = entityManager.createQuery(countHql);
            Map<String, FormsFieldModel> eruptFieldMap = eruptModel.getFormsFieldMap();
            if (null != formsQuery.getConditions()) {
                for (Condition condition : formsQuery.getConditions()) {
                    FormsFieldModel formsFieldModel = eruptFieldMap.get(condition.getKey());
                    switch (condition.getExpression()) {
                        case EQ:
                            countQuery.setParameter(condition.getKey(), FormsUtil.convertObjectType(formsFieldModel, condition.getValue()));
                            query.setParameter(condition.getKey(), FormsUtil.convertObjectType(formsFieldModel, condition.getValue()));
                            break;
                        case LIKE:
                            countQuery.setParameter(condition.getKey(), FormsJpaUtils.PERCENT + condition.getValue() + FormsJpaUtils.PERCENT);
                            query.setParameter(condition.getKey(), FormsJpaUtils.PERCENT + condition.getValue() + FormsJpaUtils.PERCENT);
                            break;
                        case RANGE:
                            List<?> list = (List<?>) condition.getValue();
                            countQuery.setParameter(FormsJpaUtils.L_VAL_KEY + condition.getKey(), FormsUtil.convertObjectType(formsFieldModel, list.get(0)));
                            countQuery.setParameter(FormsJpaUtils.R_VAL_KEY + condition.getKey(), FormsUtil.convertObjectType(formsFieldModel, list.get(1)));
                            query.setParameter(FormsJpaUtils.L_VAL_KEY + condition.getKey(), FormsUtil.convertObjectType(formsFieldModel, list.get(0)));
                            query.setParameter(FormsJpaUtils.R_VAL_KEY + condition.getKey(), FormsUtil.convertObjectType(formsFieldModel, list.get(1)));
                            break;
                        case IN:
                            List<Object> listIn = new ArrayList<>();
                            for (Object o : (List<?>) condition.getValue()) {
                                listIn.add(FormsUtil.convertObjectType(formsFieldModel, o));
                            }
                            countQuery.setParameter(condition.getKey(), listIn);
                            query.setParameter(condition.getKey(), listIn);
                            break;
                    }
                }
            }
            page.setTotal((Long) countQuery.getSingleResult());
            if (page.getTotal() > 0) {
                page.setList(query.setMaxResults(page.getPageSize()).setFirstResult((page.getPageIndex() - 1) * page.getPageSize()).getResultList());
            } else {
                page.setList(new ArrayList<>(0));
            }
            return page;
        });
    }

}
