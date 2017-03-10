package cn.edu.zzti.sys.dao;

import java.util.List;
import java.util.Map;

import cn.edu.zzti.sys.entity.SysRoleUser;

/**
 * 角色用户 数据访问接口
 * @author sunwj
 *
 */
public interface ISysRoleUserDao {
	
	/**
	 * 根据用户编号获得角色编号
	 * @param userId 用户编号
	 * @return
	 */
	public Long getRoleIdByUserId(Long userId);
	
	/**
	 * 获取拥有角色的用户列表,分页
	 * @param paramsMap 参数集合
	 *        roleId    角色编号
	 *        start     起始值
	 *        limit     页的大小
	 * @return
	 */
	public List<Map<String, Object>> getRoleUserList(Map<String, Object> paramsMap);
	
	/**
	 * 获取没有角色的用户列表,分页
	 * @param paramsMap 参数集合
	 *        start     起始值
	 *        limit     页的大小
	 * @return
	 */
	public List<Map<String, Object>> getNoRoleUserList(Map<String, Object> paramsMap);
	
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
	 * 批量插入角色用户记录
	 * @param roleUserList 角色用户集合
	 * @return
	 */
	public int insertList(List<SysRoleUser> roleUserList);
	
	/**
	 * 根据主键删除角色用户记录
	 * @param id 主键
	 * @return
	 */
	public int deleteByPrimaryKey(Long id);
}
