package com.yhtx.forms.data.jpa.dao;

import com.yhtx.forms.model.query.FormsQuery;
import com.yhtx.forms.model.vo.FormsFieldModel;
import com.yhtx.forms.model.vo.FormsModel;
import com.yhtx.forms.query.Condition;
import com.yhtx.forms.util.ReflectUtil;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author YuePeng date 2018-11-05.
 */
public class FormsJpaUtils {

    public static final String L_VAL_KEY = "l_";

    public static final String R_VAL_KEY = "r_";

    public static final String PERCENT = "%";

    public static final String AND = " and ";

    public static final String AS = " as ";

    public static final String LEFT_JOIN = " left outer join ";

    public static Set<String> getEruptColJpaKeys(FormsModel formsModel) {
        Set<String> cols = new HashSet<>();
        String eruptNameSymbol = formsModel.getFormsName() + ".";
        cols.add(eruptNameSymbol + formsModel.getPrimaryKeyCol() + AS + formsModel.getPrimaryKeyCol());
        formsModel.getFormsFieldModels().forEach(field -> {
            if (null != field.getField().getAnnotation(OneToMany.class) || null != field.getField().getAnnotation(ManyToMany.class)) {
                return;
            }
            if (null != field.getField().getAnnotation(Transient.class)) {
                return;
            }
            cols.add(eruptNameSymbol + field.getFieldName() + AS + field.getFieldName());
        });
        return cols;
    }

    //erupt 注解信息映射成hql语句
    public static String generateEruptJpaHql(FormsModel formsModel, String cols, FormsQuery query, boolean countSql) {
        StringBuilder hql = new StringBuilder();
        if (StringUtils.isNotBlank(cols)) {
            hql.append("select ").append(cols).append(" from ")
                    .append(formsModel.getFormsName()).append(AS).append(formsModel.getFormsName());
            // 修复view配置多级显示时查询结果不正确的缺陷
            // 如果view配置了多级显示，则必须手动进行left join 关联，否则会因jpa自动生成的cross join 导致查询结果不完整。
            // 在这里调用改写的 generateEruptJoinHql 方法
            hql.append(generateEruptJoinHql(formsModel));

        } else {
            hql.append("from ").append(formsModel.getFormsName());
        }
        hql.append(geneEruptHqlCondition(formsModel, query.getConditions(), query.getConditionStrings()));
        if (!countSql) {
            hql.append(geneEruptHqlOrderBy(formsModel, query.getOrderBy()));
        }
        return hql.toString();
    }

    public static String generateEruptJoinHql(FormsModel formsModel) {
        StringBuffer hql = new StringBuffer();
        ReflectUtil.findClassAllFields(formsModel.getClazz(), field -> {
            if (null != field.getAnnotation(ManyToOne.class) || null != field.getAnnotation(OneToOne.class)) {
                FormsFieldModel model = formsModel.getFormsFieldMap().get(field.getName());
                if (model != null) {

                }
            }
        });
        return hql.toString();
    }

    public static String geneEruptHqlCondition(FormsModel formsModel, List<Condition> conditions, List<String> customCondition) {
        StringBuilder hql = new StringBuilder();
        hql.append(" where 1 = 1 ");
        //condition
        if (null != conditions) {
            for (Condition condition : conditions) {

            }
        }
        Optional.ofNullable(customCondition).ifPresent(it -> it.forEach(str -> {
            if (StringUtils.isNotBlank(str)) hql.append(FormsJpaUtils.AND).append(str);
        }));
        return hql.toString();
    }

    public static String geneEruptHqlOrderBy(FormsModel formsModel, String orderBy) {
        if (StringUtils.isNotBlank(orderBy)) {
            return " order by " + FormsJpaUtils.completeHqlPath(formsModel.getFormsName(), orderBy);
        } else {
            return "";
        }
    }

    //在left join的情况下要求必须指定表信息，通过此方法生成；
    public static String completeHqlPath(String eruptName, String hqlPath) {
        if (hqlPath.contains(".")) {
            return hqlPath;
        } else {
            return eruptName + "." + hqlPath;
        }
    }

}
