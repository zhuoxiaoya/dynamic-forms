package com.yhtx.forms.repository;

import com.yhtx.forms.model.TemplateVo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 模版视图表服务层
 */
@Repository
public interface TemplateVoRepository extends CrudRepository<TemplateVo,Long> {
}
