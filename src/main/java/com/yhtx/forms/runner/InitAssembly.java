package com.yhtx.forms.runner;

import com.yhtx.forms.enums.EditTypeEnum;
import com.yhtx.forms.model.Assembly;
import com.yhtx.forms.repository.AssemblyRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.stream.Stream;

@Component
@Order(value = 1)
public class InitAssembly implements ApplicationRunner {

    @Resource
    private AssemblyRepository assemblyRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        EditTypeEnum.values();
        Stream.of(EditTypeEnum.values()).forEach(o-> {
            Assembly assembly = new Assembly();
            assembly.setAssemblyId(o.getEid());
            assembly.setName(o.getName());
            assembly.setEditType(o.getEditType());
            assembly.setRequired(true);
            assemblyRepository.save(assembly);
        });

    }
}
