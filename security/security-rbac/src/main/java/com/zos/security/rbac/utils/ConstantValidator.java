package com.zos.security.rbac.utils;

import org.apache.commons.lang.StringUtils;

public class ConstantValidator {

    /**
     * 检测主键是否合法
     * @param id 主键
     * @return 合法 true, 不合法 false
     */
    public static boolean isValuable(String id) {
        if (StringUtils.isNotBlank(id) && id.matches("\\w{32}")) {
            return true;
        }
        return false;
    }

    /**
     * 检测 Long 型数据是否可用, 仅在大于等于 1 的情况下合法
     * @param value 待测数据
     * @return 合法 true, 不合法 false
     */
    public static boolean isValuable(Long value) {
        if (value != null && value.compareTo(0L) >= 1) {
            return true;
        }
        return false;
    }

    /**
     * 检测 Long 型数据是否无用, 在小 1 和是空指针的情况下无用
     * @param value 待测数据
     * @return 无用 true, 有用 false
     */
    public static boolean isValueless(Long value) {
        if (value == null || value.compareTo(0L) <= 0) {
            return true;
        }
        return false;
    }

    /**
     * 检测对象是否不是空指针
     * @param object 待测对象
     * @return 不是空指针 true, 空指针 false
     */
    public static boolean isNotNull(Object object) {
        if (object == null) {
            return false;
        }
        return true;
    }

    /**
     * 检测对象是否是空指针
     * @param object 待测对象
     * @return 空指针 true, 不是空指针 false
     */
    public static boolean isNull(Object object) {
        if (object == null) {
            return true;
        }
        return false;
    }
}
