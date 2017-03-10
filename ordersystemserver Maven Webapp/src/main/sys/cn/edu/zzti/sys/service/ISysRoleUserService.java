package cn.edu.zzti.sys.service;

import java.util.Map;

/**
 * 角色用户 业务层接口
 * @author sunwj
 *
 */
public interface ISysRoleUserService {
	
	/**
	 * 获取拥有角色的用户列表,分页
	 * @param page     当前页
	 * @param pagesize 页的大小
	 * @param roleId   角色编号
	 * @return
	 */
	public String getRoleUserList(String page, String pagesize, Long roleId);
	
	/**
	 * 获取没有角色的用户列表,分页
	 * @param page     当前页
	 * @param pagesize 页的大小
	 * @return
	 */
	public String getNoRoleUserList(String page, String pagesize);
	
	/**
	 * 获取拥有角色的用户数量
	 * @param paramsMap 参数集合
	 *        roleId    角色编号
	 * @return
	 */
	public int getRoleUserNumber(Map<String, Object> paramsMap);
	
	/**
	 * 获取没有角色的用户数量
	 * @return
	 */
	public int getNoRoleUserNumber();
	
	/**
	 * 批量插入用户角色记录
	 * @param roleId     角色编号
	 * @param userIdArr  用户编号数组
	 * @return
	 */
	public int insertList(Long roleId, String [] userIdArr);
	
	/**
	 * 根据主键删除角色用户记录
	 * @param id 主键
	 * @return
	 */
	public int deleteByPrimaryKey(Long id);
}
