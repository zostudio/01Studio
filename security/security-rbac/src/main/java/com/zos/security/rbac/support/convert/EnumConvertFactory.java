package com.zos.security.rbac.support.convert;

import com.zos.security.rbac.support.common.BaseEnum;
import com.zos.security.rbac.utils.EnumTools;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

@Component
public class EnumConvertFactory implements ConverterFactory<String, BaseEnum> {

    @Override
    public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToEum<>(targetType);
    }

    private static class StringToEum<T extends BaseEnum> implements Converter<String, T> {

        private  Class<T> targerType;

        /**
         * Instantiates a new String to enum.
         *
         * @param targerType the targer type
         */
        public StringToEum(Class<T> targerType) {
            this.targerType = targerType;
        }

        @Override
        public T convert(String source) {
            if (StringUtils.isBlank(source)) {
                return null;
            }
            return (T) EnumTools.getEnum(this.targerType, source);
        }
    }
}

