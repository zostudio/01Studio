package com.zos.security.rbac.utils;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class CollectionTools<T> {

    public Set<T> listToSet(List<T> list) {
        Set<T> result = new HashSet<T>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (T o : list) {
                result.add(o);
            }
        }
        return result;
    }
}
