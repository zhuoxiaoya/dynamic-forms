package com.yhtx.forms.repository;


import com.yhtx.forms.model.FormsAssembly;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 组件表服务层接口
 */
@Repository
public interface FormsAssemblyRepository extends CrudRepository<FormsAssembly,Long> {

}
