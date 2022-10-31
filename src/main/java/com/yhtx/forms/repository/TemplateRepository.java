package com.yhtx.forms.repository;

import com.yhtx.forms.model.Template;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 模版表服务层
 */
@Repository
public interface TemplateRepository extends CrudRepository<Template,Long> {

}
