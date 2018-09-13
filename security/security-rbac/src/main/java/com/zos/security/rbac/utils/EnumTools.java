package com.zos.security.rbac.utils;

import com.zos.security.rbac.support.common.BaseEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EnumTools {

    /**
     * Gets i enum.
     *
     * @param <T>        the type parameter
     * @param targerType the targer type
     * @param source     the source
     * @return the enum
     */
    public static <T extends BaseEnum> Object getEnum(Class<T> targerType, String source) {
        log.info("EnumConstants: {}, source: {}", targerType.getEnumConstants(), source);
        for(T enumObj : targerType.getEnumConstants()){
            if(source.equals(enumObj.getCode())){
                return enumObj;
            }
        }
        return null;
    }
}

