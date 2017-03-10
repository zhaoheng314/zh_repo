package cn.edu.zzti.biz.service;

import java.util.List;
import java.util.Map;

import cn.edu.zzti.biz.entity.Dish;

/**
 * 菜品 业务层接口
 * @author sunwj
 *
 */
public interface IDishService {

	/**
	 * 根据主键获取菜品类别对象
	 * @param id 主键，菜品编号
	 * @return
	 */
	public Dish selectByPrimaryKey(Long id);
	
	/**
	 * 获取菜品列表，分页
	 * @param page        当前页
	 * @param pagesize    页的大小
	 * @param categoryId  菜品类别编号
	 * @return
	 */
	public String getDishList(String page, String pagesize, Long categoryId);
	
	/**
	 * 获取菜品总数 
	 * @param paramsMap  参数集合
	 *        categoryId 菜品类别编号
	 * @return
	 */
	public int getCount(Map<String, Object> paramsMap);
	
	/**
	 * 通过菜品名称获取菜品数量
	 * @param dish 菜品对象
	 * @param op   操作类型
	 * @return
	 */
	public int getDishNumber(Dish dish, String op);
	
	/**
	 * 获得所有菜品列表
	 * @return
	 */
	public List<Dish> getAllDishes();
	
	/**
	 * 获得选择菜品列表
	 * @param dishIds 菜品编号集合
	 * @return
	 */
	public List<Dish> getCheckedDishes(String dishIds);
	
	/**
	 * 获得未选择的菜品列表
	 * @param dishIds 菜品编号集合
	 * @return
	 */
	public List<Dish> getUnCheckedDishes(String dishIds);
	
	/**
	 * 插入一条菜品记录
	 * @param dish 菜品对象
	 * @return
	 */
	public int insert(Dish dish);
	
	/**
	 * 有选择的更新菜品信息
	 * @param dish 菜品对象
	 * @return
	 */
	public int updateByPrimaryKeySelective(Dish dish);
	
	/**
	 * 根据主键删除菜品信息
	 * @param id 主键，菜品编号
	 * @return
	 */
	public int deleteByPrimaryKey(Long id);
}
