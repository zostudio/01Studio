package com.zos.security.rbac.repository.support;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

/**
 * 复杂查询封装
 * 
 * @author 01Studio
 */
public abstract class ZosSpecification<T, C> extends AbstractEventConditionBuilder<T, C>
		implements Specification<T> {

	/**
	 * @param condition 条件
	 */
	public ZosSpecification(C condition) {
		super(condition);
	}

	/**
	 * 构建查询条件, 子类必须实现 addCondition 方法来编写查询的逻辑
	 * 子类可以通过 addFetch 方法控制查询的关联和抓取行为
	 *
	 * @param root Root
	 * @param query CriteriaQuery
	 * @param cb CriteriaBuilder
	 * @return Predicate
	 */
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

		if (Long.class != query.getResultType()) {
			addFetch(root);
		}

		List<Predicate> predicates = new ArrayList<Predicate>();

		QueryWraper<T> queryWraper = new QueryWraper<T>(root, query, cb, predicates);

		/**
		 * 添加查询条件
		 */
		addCondition(queryWraper);

		Predicate permissionCondition = getPermissionCondition(queryWraper);
		if (permissionCondition != null) {
			queryWraper.addPredicate(permissionCondition);
		}

		return cb.and(predicates.toArray(new Predicate[predicates.size()]));
	}

	/**
	 * 添加权限条件, 如果要查询的 domain 实现了 ManagedByOrgan 接口, 那么传入的Condition对象也应该实现 ManagedByOrgan接口,
	 * 程序会尝试从 Condition 对象获取 organFullId, 然后作为 like 查询条件添加到查询中,
	 * 查出所有以传入的 organFullId 开头的 domain.
	 * 
	 * @param queryWraper 查询构造器
	 * @return Predicate
	 */
	protected Predicate getPermissionCondition(QueryWraper<T> queryWraper) {
		return null;
	}

	/**
	 * 可以通过覆盖此方法实现关联抓取, 避免 n+1 查询
	 * 
	 * @param root Root
	 */
	protected void addFetch(Root<T> root) {
		
	}

	protected abstract void addCondition(QueryWraper<T> queryWraper);

}
