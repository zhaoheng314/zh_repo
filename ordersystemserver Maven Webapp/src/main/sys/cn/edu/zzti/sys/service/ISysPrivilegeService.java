package cn.edu.zzti.sys.service;

import java.util.Map;

import cn.edu.zzti.sys.entity.SysPrivilege;

/**
 * 系统权限 业务层接口
 * @author sunwj
 *
 */
public interface ISysPrivilegeService {
	
	/**
	 * 根据主键查询权限信息
	 * @param id 主键，权限编号
	 * @return
	 */
	public SysPrivilege selectByPrimaryKey(Long id);
	
	/**
	 * 获取权限列表，分页
	 * @param page      当前页
	 * @param pagesize  页的大小
	 * @param parentId  父权限编号
	 * @return
	 */
	public String getSysPriviliegeList(String page, String pagesize, Long parentId);
	
	/**
	 * 获取系统权限总记录数
	 * @param paramsMap 参数集合
	 *        parentId  父权限编号
	 * @return
	 */
	public int getCount(Map<String, Object> paramsMap);
	
	/**
	 * 通过权限名称或权限URL获取权限数量
	 * @param sysPrivilege 系统权限对象
	 * @param op 操作
	 * @return
	 */
	public int getPrivilegeNumber(SysPrivilege sysPrivilege, String op);
	
	/**
	 * 获取父权限列表
	 * @return
	 */
	public String getParentPrivilege();
	
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
