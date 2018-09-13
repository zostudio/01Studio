package com.zos.security.rbac.utils;

import org.apache.commons.lang.StringUtils;

public class ConstantValidator {

    public static boolean isAvaluableId(String id) {
        if (StringUtils.isNotBlank(id) && id.matches("\\w{32}")) {
            return true;
        }
        return false;
    }

    public static boolean isNotNull(Object object) {
        if (object == null) {
            return false;
        }
        return true;
    }

    public static boolean isNull(Object object) {
        if (object == null) {
            return true;
        }
        return false;
    }
}
