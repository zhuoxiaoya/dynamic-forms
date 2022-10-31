package com.yhtx.forms.repository;

import com.yhtx.forms.model.FormsTemplate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 模版表服务层
 */
@Repository
public interface FormsTemplateRepository extends CrudRepository<FormsTemplate,Long> {

}
