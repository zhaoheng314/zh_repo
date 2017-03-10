package cn.edu.zzti.sys.dao;

import java.util.List;
import java.util.Map;

import cn.edu.zzti.sys.entity.SysPrivilege;

/**
 * 系统权限 数据访问层接口
 * @author sunwj
 *
 */
public interface ISysPrivilegeDao {

	/**
	 * 根据主键查询权限信息
	 * @param id 主键，权限编号
	 * @return
	 */
	public List<SysPrivilege> selectByPrimaryKey(Long id);
	
	/**
	 * 获取权限列表，分页
	 * @param paramsMap 参数集合
	 *        parentId  父权限编号
	 *        start     起始值
	 *        limit     页的大小
	 * @return
	 */
	public List<SysPrivilege> getSysPriviliegeList(Map<String, Object> paramsMap);
	
	/**
	 * 获取系统权限总记录数
	 * @param paramsMap 参数集合
	 *        parentId  父权限编号
	 * @return
	 */
	public int getCount(Map<String, Object> paramsMap);
	
	/**
	 * 通过权限名称或权限URL获取权限数量
	 * @param paramsMap      参数集合
	 *        privilegeName  权限名称 
	 *        privilegeUrl   权限URL
	 *        op             操作
	 *        id             主键，权限编号
	 * @return
	 */
    public int getPrivilegeNumber(Map<String, Object> paramsMap);
    
    /**
     * 获取父权限列表
     * @return
     */
    public List<Map<String, Object>> getParentPrivilege();
                                 
    /**
	 * 获取所有子权限信息
	 * @return
	 */
	public List<Map<String, Object>> getChildPrivilege();
	
	/**
	 * 根据父权限编号获取权限信息
	 * @param parentId 父权限编号
	 * @return
	 */
	public List<Map<String, Object>> getPrivilegeByPID(Long parentId);
    
	/**
	 * 根据角色编号获取角色对应的权限
	 * @param roleId 角色编号
	 * @return
	 */
	public List<SysPrivilege> getPrivilegeByRoleId(Long roleId);
	
    /**
     * 插入一条权限记录
     * @param sysPrivilege 系统权限对象
     * @return
     */
    public int insert(SysPrivilege sysPrivilege);
    
    /**
     * 有选择的更新权限信息
     * @param sysPrivilege 系统权限对象
     * @return
     */
    public int updateByPrimaryKeySelective(SysPrivilege sysPrivilege);
    
    /**
     * 根据主键修改权限状态，启用/禁用
     * @param id
     * @return
     */
    public int changeStatusByPrimaryKey(Long id);
    
    /**
     * 根据主键删除权限信息
     * @param id 主键，权限编号
     * @return
     */
    public int deleteByPrimaryKey(Long id);
}
