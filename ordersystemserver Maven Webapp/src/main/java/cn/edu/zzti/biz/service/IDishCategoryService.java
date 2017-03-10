package cn.edu.zzti.biz.service;

import java.util.List;

import cn.edu.zzti.biz.entity.DishCategory;

/**
 * 菜品类别 业务层接口
 * @author sunwj
 *
 */
public interface IDishCategoryService {
	
	/**
	 * 根据主键获取菜品类别对象
	 * @param id 主键
	 * @return
	 */
	public DishCategory selectByPrimiaryKey(Long id);
	
	/**
	 * 获取菜品类别列表，分页
	 * @param page     当前页
	 * @param pagesize 页的大小
	 * @return
	 */
	public String getCategoryList(String page, String pagesize);
	
	/**
	 * 获取所有菜品类别信息
	 * @param categoryId 菜品类别编号
	 * @return
	 */
	public List<DishCategory> getAllCategory(Long categoryId);
	
	/**
	 * 构造菜品类别树
	 * @return
	 */
	public String getCategoryTree();
	
	/**
	 * 获取菜品类别总数
	 * @return
	 */
	public int getCount();
	
	/**
	 * 获取菜品类别数量 
	 * @param category 菜品类别对象
	 * @param op       操作类型
	 * @return
	 */
	public int getCategoryNumber(DishCategory category, String op);
	
	/**
	 * 插入一条菜品类别记录
	 * @param category 菜品类别对象
	 * @return
	 */
	public int insert(DishCategory category);
	
	/**
	 * 根据主键有选择的更新菜品类别信息
	 * @param category 菜品类别对象
	 * @return
	 */
	public int updateByPrimaryKeySelective(DishCategory category);
	
	/**
	 * 根据主键删除菜品类别信息
	 * @param id 菜品类别编号
	 * @return
	 */
	public int deleteByPrimiaryKey(Long id);
}
