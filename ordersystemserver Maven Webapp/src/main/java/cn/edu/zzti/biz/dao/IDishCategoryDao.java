package cn.edu.zzti.biz.dao;

import java.util.List;
import java.util.Map;

import cn.edu.zzti.biz.entity.DishCategory;

/**
 * 菜品类别 数据访问层接口
 * @author sunwj
 *
 */
public interface IDishCategoryDao {
	
	/**
	 * 根据主键获取菜品类别对象
	 * @param id 主键
	 * @return
	 */
	public DishCategory selectByPrimiaryKey(Long id);
	
	/**
	 * 获取菜品类别列表，分页
	 * @param paramsMap 参数集合
	 *        start     起始值
	 *        limit     页的大小
	 * @return
	 */
	public List<DishCategory> getCategoryList(Map<String, Object> paramsMap);
	
	/**
	 * 获取所有菜品类别信息
	 * @param paramsMap  参数集合
	 *        categoryId 菜品类别编号
	 * @return
	 */
	public List<DishCategory> getAllCategory(Map<String, Object> paramsMap);
	
	/**
	 * 获取菜品类别总数
	 * @return
	 */
	public int getCount();
	
	/**
	 * 通过菜品类别名称获取菜品类别数量 
	 * @param paramsMap    参数集合
	 *        categoryName 菜品类别名称
	 *        op           操作类型
	 *        id           菜品类别编号
	 * @return
	 */
	public int getCategoryNumber(Map<String, Object> paramsMap);
	
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
