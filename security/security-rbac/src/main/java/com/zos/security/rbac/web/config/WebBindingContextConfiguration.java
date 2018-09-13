package com.zos.security.rbac.web.config;

import com.zos.security.rbac.support.convert.EnumConvertFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;

@Configuration
public class WebBindingContextConfiguration {

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    /**
     * Add conversion config.
     */
    @PostConstruct
    public void addConversionConfig() {
        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) requestMappingHandlerAdapter.getWebBindingInitializer();
        if (initializer != null && initializer.getConversionService() != null) {
            GenericConversionService genericConversionService = (GenericConversionService)initializer.getConversionService();
            if(genericConversionService != null){
                genericConversionService.addConverterFactory(new EnumConvertFactory());
            }
        }
    }
}
