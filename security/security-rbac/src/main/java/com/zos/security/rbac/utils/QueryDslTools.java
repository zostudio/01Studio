package com.zos.security.rbac.utils;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class QueryDslTools {

    @Autowired
    RepositoryRestProperties repositoryRestProperties;

    /**
     * 分析排序方式
     * @param order 排序方式
     * @return Order 排序方式
     */
    public Order getOrder(Sort.Order order) {
        if (order == null || order.getDirection() == null || order.getDirection().isDescending()) {
            return Order.DESC;
        }
        return Order.ASC;
    }

    /**
     * 分析空值处理方式
     * @param order 空值处理方式
     * @return Order 空值处理方式
     */
    public OrderSpecifier.NullHandling getNullHandling(Sort.Order order) {
        if (order == null || order.getNullHandling() == null || order.getNullHandling().compareTo(Sort.NullHandling.NATIVE) == 0) {
            return OrderSpecifier.NullHandling.Default;
        } else if (order.getNullHandling().compareTo(Sort.NullHandling.NULLS_FIRST) == 0) {
            return OrderSpecifier.NullHandling.NullsFirst;
        }
        return  OrderSpecifier.NullHandling.NullsLast;
    }

    /**
     * 添加分页参数
     *
     * @param pageable 分页参数
     * @param jpaQuery 查询对象
     */
    public void addPageable(Pageable pageable, JPAQuery<?> jpaQuery) {
        if (pageable == null || jpaQuery == null) {
            return;
        }
        if (pageable.getOffset() >= 0 && pageable.getPageSize() < repositoryRestProperties.getMaxPageSize()) {
            jpaQuery.offset(pageable.getOffset()).limit(pageable.getPageSize());
        }
    }

}
