package com.yhtx.forms.repository;

import com.yhtx.forms.model.FormsTemplateVoValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 模版视图组件对应值
 */
@Repository
public interface FormsTemplateVoValueRepository extends JpaRepository<FormsTemplateVoValue,Long> {
}
