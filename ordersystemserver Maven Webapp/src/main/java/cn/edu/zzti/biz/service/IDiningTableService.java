package cn.edu.zzti.biz.service;

import java.util.List;

import cn.edu.zzti.biz.entity.DiningTable;

/**
 * 餐桌 业务层接口
 * @author sunwj
 *
 */
public interface IDiningTableService {
	
	/**
	 * 根据主键获取餐桌对象
	 * @param id 主键，餐桌编号
	 * @return
	 */
	public DiningTable selectByPrimiaryKey(Long id);
	
	/**
	 * 获取餐桌列表，分页
	 * @param page     当前页
	 * @param pagesize 页的大小
	 * @return
	 */
	public String getDiningTableList(String page, String pagesize);
	
	/**
	 * 获取餐桌总数
	 * @return
	 */
	public int getCount();
	
	/**
	 * 获取餐桌数量
	 * @param table 餐桌对象
	 * @param op    操作类型
	 * @return
	 */
	public int getTableNumber(DiningTable table, String op);
	
	/**
	 * 获取所有餐桌列表 
	 * @param tableId 餐桌编号
	 * @return
	 */
	public List<DiningTable> getAllTables(Long tableId);
	
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
