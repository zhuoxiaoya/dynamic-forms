package com.yhtx.forms.repository;


import com.yhtx.forms.model.Assembly;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 组件表服务层接口
 */
@Repository
public interface AssemblyRepository extends CrudRepository<Assembly,Long> {

}
