package cn.edu.zzti.biz.dao;

import java.util.List;
import java.util.Map;

import cn.edu.zzti.biz.entity.Member;

/**
 * 会员 数据访问层接口
 * @author sunwj
 *
 */
public interface IMemberDao {
	
	/**
	 * 根据主键获取会员对象
	 * @param id 主键，会员编号
	 * @return 
	 */
	public Member selectByPrimiaryKey(Long id);
	
	/**
	 * 获取会员列表，分页
	 * @param paramsMap 参数集合
	 *        strat     起始值 
	 *        limit     页的大小
	 * @return
	 */
	public List<Member> getMemberList(Map<String, Object> paramsMap);
	
	/**
	 * 获取会员数量 
	 * @return
	 */
	public int getCount();
	
	/**
	 * 通过会员手机号获取会员数量
	 * @param paramsMap 参数集合
	 *        phone     手机号
	 *        op        操作类型
	 *        id        主键
	 * @return
	 */
	public int getMemberNumber(Map<String, Object> paramsMap);
	
	/**
	 * 插入一条会员记录
	 * @param member 会员对象
	 * @return
	 */
	public int insert(Member member);
	
	/**
	 * 根据主键有选择的更新会员信息
	 * @param member 会员对象
	 * @return
	 */
	public int updateByPrimaryKeySelective(Member member);
	
	/**
	 * 根据主键删除会员信息
	 * @param id 主键，会员编号
	 * @return
	 */
	public int deleteByPrimiaryKey(Long id);
}
