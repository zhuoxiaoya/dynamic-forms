package com.yhtx.forms.runner;

import com.yhtx.forms.enums.EditTypeEnum;
import com.yhtx.forms.model.FormsAssembly;
import com.yhtx.forms.repository.FormsAssemblyRepository;
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
    private FormsAssemblyRepository formsAssemblyRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        EditTypeEnum.values();
        Stream.of(EditTypeEnum.values()).forEach(o-> {
            FormsAssembly formsAssembly = new FormsAssembly();
            formsAssembly.setId(o.getEid());
            formsAssembly.setAssemblyName(o.getName());
            formsAssembly.setEditType(o.getEditType());
            formsAssembly.setRequired(true);
            formsAssemblyRepository.save(formsAssembly);
        });

    }
}
