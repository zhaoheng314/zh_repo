package cn.edu.zzti.biz.dao;

import java.util.List;
import java.util.Map;

import cn.edu.zzti.biz.entity.Dish;

/**
 * 菜品 数据访问层接口
 * @author sunwj
 *
 */
public interface IDishDao {

	/**
	 * 根据主键获取菜品类别对象
	 * @param id 主键，菜品编号
	 * @return
	 */
	public Dish selectByPrimaryKey(Long id);
	
	/**
	 * 获取菜品列表，分页
	 * @param paramsMap  参数集合
	 *        categoryId 菜品类别编号
	 *        page       当前页
	 *        limit      页的大小
	 * @return
	 */
	public List<Dish> getDishList(Map<String, Object> paramsMap);
	
	/**
	 * 获取菜品总数 
	 * @param paramsMap  参数集合
	 *        categoryId 菜品类别编号
	 * @return
	 */
	public int getCount(Map<String, Object> paramsMap);
	
	/**
	 * 通过菜品名称获取菜品数量
	 * @param paramsMap 参数集合
	 *        dishName  菜品名称
	 *        op        操作类型
	 *        id        主键，菜品编号
	 * @return
	 */
	public int getDishNumber(Map<String, Object> paramsMap);
	
	/**
	 * 获得所有菜品列表
	 * @return
	 */
	public List<Dish> getAllDishes();
	
	/**
	 * 获得选择的菜品列表
	 * @param dishIdArr 数组集合
	 * @return
	 */
	public List<Dish> getCheckedDishes(String [] dishIdArr);
	
	/**
	 * 获得未选择的菜品列表
	 * @param dishIdArr 数组集合
	 * @return
	 */
	public List<Dish> getUnCheckedDishes(String [] dishIdArr);
	
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
