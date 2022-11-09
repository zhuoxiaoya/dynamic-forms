package com.yhtx.forms.repository;

import com.yhtx.forms.model.FormsTemplateVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 模版视图表服务层
 */
@Repository
public interface FormsTemplateVoRepository extends JpaRepository<FormsTemplateVo,Long> {
}
