package com.zos.security.rbac.repository.support;

import java.lang.reflect.InvocationTargetException;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.beanutils.PropertyUtils;


/**
 *
 * @author 01Studio
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public abstract class AbstractEventConditionBuilder<T, C> extends AbstractConditionBuilder<T> {

	/**
	 * 查询条件
	 */
	private C condition;
	
	/**
	 * @param condition 查询条件
	 */
	public AbstractEventConditionBuilder(C condition){
		this.condition = condition;
	}

	/**
	 * 向查询中添加包含(like)条件
	 * 
	 * @param queryWraper 查询构造器
	 * @param field 指出查询条件的值从 condition 对象的哪个字段里取, 并且指出要向哪个字段添加包含(like)条件(同一个字段名称)
	 */
	protected void addLikeCondition(QueryWraper<T> queryWraper, String field){
		addLikeCondition(queryWraper, field, field);
	}
	
	/**
	 * 向查询中添加包含(like)条件
	 * 
	 * @param queryWraper 查询构造器
	 * @param field 指出查询条件的值从 condition 对象的哪个字段里取
	 * @param column 指出要向哪个字段添加包含(like)条件
	 */
	protected void addLikeCondition(QueryWraper<T> queryWraper, String field, String column){
		addLikeConditionToColumn(queryWraper, column, (String) 
				getValue(getCondition(), field));
	}
	
	
	/**
	 * 向查询中添加包含(like)条件,%放在值后面
	 * 
	 * @param queryWraper 查询构造器
	 * @param field 指出查询条件的值从 condition 对象的哪个字段里取, 并且指出要向哪个字段添加包含(like)条件(同一个字段名称)
	 */
	protected void addStartsWidthCondition(QueryWraper<T> queryWraper, String field){
		addStartsWidthCondition(queryWraper, field, field);
	}
	
	/**
	 * 向查询中添加包含(like)条件, %放在值后面
	 * 
	 * @param queryWraper 查询构造器
	 * @param field 指出查询条件的值从 condition 对象的哪个字段里取
	 * @param column 指出要向哪个字段添加包含(like)条件
	 */
	protected void addStartsWidthCondition(QueryWraper<T> queryWraper, String field, String column){
		addStartsWidthConditionToColumn(queryWraper, column, (String) 
				getValue(getCondition(), field));
	}
		
	/**
	 * 向查询中添加等于(=)条件
	 * 
	 * @param queryWraper 查询构造器
	 * @param field 指出查询条件的值从 condition 对象的哪个字段里取, 并且指出要向哪个字段添加条件(同一个字段名称)
	 */
	protected void addEqualsCondition(QueryWraper<T> queryWraper, String field){
		addEqualsCondition(queryWraper, field, field);
	}
	
	/**
	 * 向查询中添加等于(=)条件
	 * 
	 * @param queryWraper 查询构造器
	 * @param field 指出查询条件的值从 condition 对象的哪个字段里取
	 * @param column 指出要向哪个字段添加条件
	 */
	protected void addEqualsCondition(QueryWraper<T> queryWraper, String field, String column){
		addEqualsConditionToColumn(queryWraper, column, 
				getValue(getCondition(), field));
	}
	
	/**
	 * 向查询中添加不等于(!=)条件
	 * 
	 * @param queryWraper 查询构造器
	 * @param field 指出查询条件的值从 condition 对象的哪个字段里取, 并且指出要向哪个字段添加条件(同一个字段名称)
	 */
	protected void addNotEqualsCondition(QueryWraper<T> queryWraper, String field){
		addNotEqualsCondition(queryWraper, field, field);
	}
	
	/**
	 * 向查询中添加等于(=)条件
	 * 
	 * @param queryWraper 查询构造器
	 * @param field 指出查询条件的值从condition对象的哪个字段里取
	 * @param column 指出要向哪个字段添加条件
	 */
	protected void addNotEqualsCondition(QueryWraper<T> queryWraper, String field, String column){
		addNotEqualsConditionToColumn(queryWraper, column, getValue(getCondition(), field));
	}
	
	/**
	 * 向查询中添加in条件
	 *
	 * @param queryWraper 查询构造器
	 * @param field 属性
	 */
	protected void addInCondition(QueryWraper<T> queryWraper, String field) {
		addInCondition(queryWraper, field, field);
	}

	/**
	 * 向查询中添加in条件
	 *
	 * @param queryWraper 查询构造器
	 * @param field 属性
	 * @param column 字段
	 */
	protected void addInCondition(QueryWraper<T> queryWraper, String field, String column) {
		addInConditionToColumn(queryWraper, column, 
				getValue(getCondition(), field));
	}
	
	/**
	 * 向查询中添加between条件
	 *
	 * @param queryWraper 查询构造器
	 * @param field 属性
	 */
	protected void addBetweenCondition(QueryWraper<T> queryWraper, String field) {
		addBetweenCondition(queryWraper, field, field+"To", field);
	}
	/**
	 * 向查询中添加between条件
	 *
	 * @param queryWraper 查询构造器
	 * @param startField 开始属性
	 * @param endField 结束属性
	 * @param column 字段
	 */
	@SuppressWarnings("rawtypes")
	protected void addBetweenCondition(QueryWraper<T> queryWraper, String startField, String endField, String column) {
		addBetweenConditionToColumn(queryWraper, column, 
				(Comparable)getValue(getCondition(), startField), 
				(Comparable)getValue(getCondition(), endField));
	}
	
	/**
	 * 向查询中添加大于条件
	 *
	 * @param queryWraper 查询构造器
	 * @param field 属性
	 */
	protected void addGreaterThanCondition(QueryWraper<T> queryWraper, String field) {
		addGreaterThanCondition(queryWraper, field, field);
	}
	/**
	 * 向查询中添加大于条件
	 *
	 * @param queryWraper 查询构造器
	 * @param field 属性
	 * @param column 字段
	 */
	@SuppressWarnings("rawtypes")
	protected void addGreaterThanCondition(QueryWraper<T> queryWraper, String field, String column) {
		addGreaterThanConditionToColumn(queryWraper, column, 
				(Comparable)getValue(getCondition(), field));
	}
	
	/**
	 * 向查询中添加大于等于条件
	 *
	 * @param queryWraper 查询构造器
	 * @param field 属性
	 */
	protected void addGreaterThanOrEqualCondition(QueryWraper<T> queryWraper, String field) {
		addGreaterThanOrEqualCondition(queryWraper, field, field);
	}
	
	/**
	 * 向查询中添加大于等于条件
	 *
	 * @param queryWraper 查询构造器
	 * @param field 属性
	 * @param column 字段
	 */
	@SuppressWarnings("rawtypes")
	protected void addGreaterThanOrEqualCondition(QueryWraper<T> queryWraper, String field, String column) {
		addGreaterThanOrEqualConditionToColumn(queryWraper, column, 
				(Comparable)getValue(getCondition(), field));
	}
	
	/**
	 * 向查询中添加小于条件
	 *
	 * @param queryWraper 查询构造器
	 * @param field 属性
	 */
	protected void addLessThanCondition(QueryWraper<T> queryWraper, String field) {
		addLessThanCondition(queryWraper, field, field);
	}
	/**
	 * 向查询中添加小于条件
	 *
	 * @param queryWraper 查询构造器
	 * @param field 属性
	 * @param column 字段
	 */
	@SuppressWarnings("rawtypes")
	protected void addLessThanCondition(QueryWraper<T> queryWraper, String field, String column) {
		addLessThanConditionToColumn(queryWraper, column, 
				(Comparable)getValue(getCondition(), field));
	}

	/**
	 * 向查询中添加小于等于条件
	 *
	 * @param queryWraper 查询构造器
	 * @param field 属性
	 */
	protected void addLessThanOrEqualCondition(QueryWraper<T> queryWraper, String field) {
		addLessThanOrEqualCondition(queryWraper, field, field);
	}
	
	/**
	 * 向查询中添加小于等于条件
	 *
	 * @param queryWraper 查询构造器
	 * @param field 属性
	 * @param column 字段
	 */
	@SuppressWarnings("rawtypes")
	protected void addLessThanOrEqualCondition(QueryWraper<T> queryWraper, String field, String column) {
		addLessThanOrEqualConditionToColumn(queryWraper, column, 
				(Comparable)getValue(getCondition(), field));
	}

	private Object getValue(C condition, String field) {
		try {
			return PropertyUtils.getProperty(condition, field);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}

}
