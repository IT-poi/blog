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
public interface GenericDao<T> {
	/**
	 * 通过id查询对应数据
	 * @param id
	 * @return 查询的实体
	 */
	public T queryById(int id);
	
	/**
	 * 查询所有数据
	 * @return 查询到的所有实体集合
	 */
	public List<T> queryAll();
	
	/**
	 * 将泛型所约定的实体插入数据表
	 * @param t 要插入的实体
	 * @return 插入操作影响的行数
	 */
	public boolean insert(T t);
	
	/**
	 * 更新操作
	 * @param t 更新的实体
	 * @return 影响的行数
	 */
	public int update(T t);
	
	/**
	 * 通过id删除
	 * @param id
	 * @return 影响的行数
	 */
	public int deleteById(int id);
	
	public void flush();

}
