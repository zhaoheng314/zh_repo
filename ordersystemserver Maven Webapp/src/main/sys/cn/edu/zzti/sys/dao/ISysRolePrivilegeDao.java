package cn.edu.zzti.sys.dao;

import java.util.List;

import cn.edu.zzti.sys.entity.SysRolePrivilege;

/**
 * 角色权限 数据访问接口
 * @author sunwj
 *
 */
public interface ISysRolePrivilegeDao {
	
	/**
	 * 根据角色编号获取权限编号集合
	 * @param roleId 角色编号
	 * @return
	 */
	public List<Long> getPrivilegeByRoleId(Long roleId);
	
	/**
	 * 批量插入角色权限记录
	 * @param rolePrivilegeList 角色权限集合
	 * @return
	 */
	public int insertList(List<SysRolePrivilege> rolePrivilegeList);
	
	/**
	 * 根据角色编号删除角色拥有的权限信息
	 * @param roleId 角色编号
	 * @return 
	 */
	public int deletePrivilegeByRoleId(Long roleId);
}
