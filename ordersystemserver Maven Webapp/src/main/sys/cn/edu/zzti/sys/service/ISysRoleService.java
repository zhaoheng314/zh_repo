package cn.edu.zzti.sys.service;

import java.util.Map;

import cn.edu.zzti.sys.entity.SysRole;

/**
 * 系统角色 业务层接口
 * @author sunwj
 *
 */
public interface ISysRoleService {

	/**
	 * 根据主键查询角色信息
	 * @param id 主键，角色编号
	 * @return
	 */
	public SysRole selectByPrimaryKey(Long id);
	
	/**
	 * 获取角色列表，分页
	 * @param page      当前页
	 * @param pagesize  页的大小 
	 * @return
	 */
	public String getSysRoleList(String page, String pagesize);
	
	/**
	 * 获取所有角色并构造角色树
	 * @return
	 */
	public String getAllRoles();
	
	/**
	 * 获取系统角色总记录条数
	 * @return
	 */
	public int getCount();
	
	/**
	 * 通过角色名称或角色代码获取角色数量
	 * @param sysRole  系统角色对象
	 * @param op       操作类型
	 * @return
	 */
	public Map<String, Integer> getRoleNumber(SysRole sysRole, String op);
	
	/**
	 * 插入一条角色记录
	 * @param sysRole 系统角色对象
	 * @return
	 */
	public int insert(SysRole sysRole);
	
	/**
	 * 根据主键有选择的更新角色信息 
	 * @param sysRole 系统角色对象
	 * @return
	 */
	public int updateByPrimaryKeySelective(SysRole sysRole);
	
	/**
	 * 根据主键删除角色信息
	 * @param id 主键，角色编号
	 * @return
	 */
	public int deleteByPrimaryKey(Long id);
}
