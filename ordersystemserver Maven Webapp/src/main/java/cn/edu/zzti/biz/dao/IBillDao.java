package cn.edu.zzti.biz.dao;

import java.util.List;
import java.util.Map;

import cn.edu.zzti.biz.entity.Bill;

/**
 * 账单 数据访问层 接口
 * @author sunwj
 *
 */
public interface IBillDao {
	
	/**
	 * 根据主键查询账单信息
	 * @param id 主键，账单信息
	 * @return
	 */
	public Bill selectByPrimaryKey(Long id);
	
	/**
	 * 获取账单列表，分页
	 * @param paramsMap 参数集合
	 *        start     起始值
	 *        limit     页的大小
	 * @return
	 */
	public List<Bill> getBillList(Map<String, Object> paramsMap);
	
	/**
	 * 获取账单总数
	 * @return
	 */
	public int getCount();
	
	/**
	 * 插入一条账单记录
	 * @param bill 账单对象
	 */
	public int insert(Bill bill);
	
	/**
	 * 有选择的更新账单信息 
	 * @param bill
	 * @return
	 */
	public int updateByPrimaryKeySelective(Bill bill);
	
	/**
	 * 根据主键删除账单信息
	 * @param id 主键，账单编号
	 * @return
	 */
	public int deleteByPrimaryKey(Long id);
}
