package com.zos.security.rbac.utils;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.data.domain.Sort;

public class QueryDslTools {

    public static BooleanExpression andBooleanExpression(BooleanExpression... booleanExpressions) {
        if (ArrayUtils.isNotEmpty(booleanExpressions)) {
            if (booleanExpressions.length == 1) {
                return booleanExpressions[0];
            }
            int index = booleanExpressions.length;
            while(index-- > 1) {
                if (booleanExpressions[index - 1] == null) {
                    continue;
                }
                booleanExpressions[index].and(booleanExpressions[index - 1]);
            }
            return booleanExpressions[booleanExpressions.length - 1];
        } else {
            return null;
        }
    }

    public static BooleanExpression orBooleanExpression(BooleanExpression... booleanExpressions) {
        if (ArrayUtils.isNotEmpty(booleanExpressions)) {
            if (booleanExpressions.length == 1) {
                return booleanExpressions[0];
            }
            int index = booleanExpressions.length;
            while(index-- > 1) {
                booleanExpressions[index].or(booleanExpressions[index - 1]);
            }
            return booleanExpressions[index - 1];
        } else {
            return null;
        }
    }

    public static Order getOrder(Sort.Order order) {
        if (order == null || order.getDirection() == null || order.getDirection().isDescending()) {
            return Order.DESC;
        }
        return Order.ASC;
    }

    public static OrderSpecifier.NullHandling getNullHandling(Sort.Order order) {
        if (order == null || order.getNullHandling() == null || order.getNullHandling().compareTo(Sort.NullHandling.NATIVE) == 0) {
            return OrderSpecifier.NullHandling.Default;
        } else if (order.getNullHandling().compareTo(Sort.NullHandling.NULLS_FIRST) == 0) {
            return OrderSpecifier.NullHandling.NullsFirst;
        }
        return  OrderSpecifier.NullHandling.NullsLast;
    }


}
