package com.zos.security.rbac.utils;

public class ConstantValidator {

    public static boolean isAvaluableId(Long id) {
        if (id == null || id.compareTo(0L) <= 0) {
            return false;
        }
        return true;
    }

    public static boolean isNotNull(Object object) {
        if (object == null) {
            return false;
        }
        return true;
    }
}
