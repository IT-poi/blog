package com.cuit.boke.dao;

import java.util.List;
/**
 * 创建时间：2016年11月21日20:26:04
 * @author inori
 *
 * @param <T>
 * 
 * dao类的通用接口，包括基本的crud操作
 */
public interface GenericDao<T, PK> {
	/**
	 * 通过id查询对应数据
	 * @param id
	 * @return 查询的实体
	 */
	public T queryById(Class<T> clazz,PK id);
	
	/**
	 * 查询所有数据
	 * @return 查询到的所有实体集合
	 */
	public List<T> queryAll(Class<T> clazz);
	
	/**
	 * 将泛型所约定的实体插入数据表
	 * @param t 要插入的实体
	 * @return 插入数据id
	 */
	public PK insert(T t);
	
	/**
	 * 更新操作
	 * @param t 更新的实体
	 * @return 更新数据id
	 */
	public void update(T t);
	
	/**
	 * 通过id删除
	 * @param id
	 * @return 删除数据id
	 */
	public void deleteById(Class<T> clazz,PK id);
	
	/**
	 * flush方法的主要作用就是清理缓存，
	 * 强制数据库与Hibernate缓存同步，
	 * 以保证数据的一致性,但不会真正提交数据，
	 * 只有事务结束Commit才会提交数据，
	 * commit方法会首先调用flush方法然后提交事务！
	 */
	public void flush();
	

}
