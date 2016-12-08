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
	 * @param clazz 要查询的实体的类型
	 * @return 查询到的所有实体集合
	 */
	public List<T> queryAll(Class<T> clazz);
	
	/**
	 * 分页查询，并且根据orderBy的值排序
	 * @param clazz 要查询的实体类型
	 * @param begin 查询开始位置
	 * @param pageSize 查询的记录条数
	 * @param orderBy 根据此字段排序 若为空则默认
	 * @param order 排序方式 若为空则默认升序
	 * @return 查询记录结果列表
	 */
	public List<T> queryByPage(Class<T> clazz, int begin, int pageSize, String orderBy, String order);
	
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
