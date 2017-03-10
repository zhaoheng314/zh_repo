package cn.edu.zzti.biz.dao;

import java.util.List;
import java.util.Map;

import cn.edu.zzti.biz.entity.DiningTable;

/**
 * 餐桌 数据访问层接口
 * @author sunwj
 *
 */
public interface IDiningTableDao {
	
	/**
	 * 根据主键获取餐桌对象
	 * @param id 主键，餐桌编号
	 * @return
	 */
	public DiningTable selectByPrimiaryKey(Long id);
	
	/**
	 * 获取餐桌列表，分页
	 * @param paramsMap 参数集合
	 *        start     起始值
	 *        limit     页的大小
	 * @return
	 */
	public List<DiningTable> getDiningTableList(Map<String, Object> paramsMap);
	
	/**
	 * 获取餐桌总数
	 * @return
	 */
	public int getCount();
	
	/**
	 * 通过餐桌代码获取餐桌数量
	 * @param paramsMap 参数集合
	 *        tableCode 餐桌代码
	 *        op        操作类型
	 *        id        餐桌编号
	 * @return
	 */
	public int getTableNumber(Map<String, Object> paramsMap);
	
	/**
	 * 获取所有餐桌列表 
	 * @param paramsMap 参数集合
	 *        tableId   餐桌编号
	 * @return
	 */
	public List<DiningTable> getAllTables(Map<String, Object> paramsMap);
	
	/**
	 * 插入一条餐桌记录
	 * @param table 餐桌对象
	 * @return
	 */
	public int insert(DiningTable table);
	
	/**
	 * 根据主键有选择的更新餐桌信息
	 * @param table 餐桌对象
	 * @return
	 */
	public int updateByPrimaryKeySelective(DiningTable table);
	
	/**
	 * 根据主键删除餐桌信息
	 * @param id 主键
	 * @return
	 */
	public int deleteByPrimiaryKey(Long id);
}
