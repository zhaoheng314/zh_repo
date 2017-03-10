package cn.edu.zzti.sys.service;

/**
 * 角色权限 业务层接口
 * @author sunwj
 *
 */
public interface ISysRolePrivilegeService {

	/**
	 * 根据角色编号获取权限编号信息
	 * @param roleId 角色编号
	 * @return
	 */
	public String getPrivilegeByRoleId(Long roleId);
	
	/**
	 * 批量插入角色权限记录
	 * @param roleId          角色编号
	 * @param privilegeIdArr  权限编号数组
	 * @return
	 */
	public int insertList(Long roleId, String [] privilegeIdArr);
	
	/**
	 * 根据角色编号删除角色拥有的权限信息
	 * @param roleId 角色编号
	 * @return 
	 */
	public int deletePrivilegeByRoleId(Long roleId);
}
