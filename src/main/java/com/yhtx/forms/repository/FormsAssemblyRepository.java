package com.yhtx.forms.repository;


import com.yhtx.forms.model.FormsAssembly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 组件表服务层接口
 */
@Repository
public interface FormsAssemblyRepository extends JpaRepository<FormsAssembly,Long> {

}
