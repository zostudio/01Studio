package com.zos.security.rbac.repository.support;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import lombok.Getter;
import lombok.Setter;

/**
 * 包装用于构建 JPA 动态查询时所需的对象
 * 
 * @author 01Studio
 */
@Setter
@Getter
public class QueryWraper<T> {

	/**
	 * @param root
	 *            JPA Root 相当于 FROM 子句
	 * @param query
	 *            CriteriaQuery 查询结果
	 * @param cb
	 *            JPA CriteriaBuilder CriteriaQuery 工厂
	 * @param predicates
	 *            JPA Predicate 查询条件集合
	 */
	public QueryWraper(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb, List<Predicate> predicates) {
		this.root = root;
		this.query = query;
		this.cb = cb;
		this.predicates = predicates;
	}

	/**
	 * JPA Root
	 */
	private Root<T> root;

	/**
	 * JPA CriteriaBuilder
	 */
	private CriteriaBuilder cb;

	/**
	 * JPA Predicate 集合
	 */
	private List<Predicate> predicates;

	/**
	 * JPA 查询对象
	 */
	private CriteriaQuery<?> query;

	/**
	 * @param predicate JPA Predicate
	 */
	public void addPredicate(Predicate predicate) {
		this.predicates.add(predicate);
	}

}
