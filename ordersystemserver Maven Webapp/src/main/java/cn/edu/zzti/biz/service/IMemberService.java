package cn.edu.zzti.biz.service;

import cn.edu.zzti.biz.entity.Member;

/**
 * 会员 业务层接口
 * @author sunwj
 *
 */
public interface IMemberService {

	/**
	 * 根据主键获取会员对象
	 * @param id 主键，会员编号
	 * @return 
	 */
	public Member selectByPrimiaryKey(Long id);
	
	/**
	 * 获取会员列表，分页
	 * @param page     当前页
	 * @param pagesize 页的大小
	 * @return
	 */
	public String getMemberList(String page, String pagesize);
	
	/**
	 * 获取会员数量 
	 * @return
	 */
	public int getCount();
	
	/**
	 * 通过会员手机号获取会员数量
	 * @param member 会员对象
	 * @param op     操作类型
	 * @return
	 */
	public int getMemberNumber(Member member, String op);
	
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
