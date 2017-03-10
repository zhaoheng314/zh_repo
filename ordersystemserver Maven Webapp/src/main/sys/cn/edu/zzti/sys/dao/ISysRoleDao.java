package cn.edu.zzti.sys.dao;

import java.util.List;
import java.util.Map;

import cn.edu.zzti.sys.entity.SysRole;

/**
 * 系统角色 数据访问层接口
 * @author sunwj
 *
 */
public interface ISysRoleDao {

	/**
	 * 根据主键查询角色信息
	 * @param id 主键，角色编号
	 * @return
	 */
	public List<SysRole> selectByPrimaryKey(Long id);
	
	/**
	 * 获取角色列表，分页
	 * @param paramsMap 参数集合
	 *        start     起始值
	 *        limit     页的大小
	 * @return
	 */
	public List<SysRole> getSysRoleList(Map<String, Object> paramsMap);

	/**
	 * 获取所有角色 
	 * @return
	 */
	public List<Map<String, Object>> getAllRoles();
	
	/**
	 * 获取系统角色总记录条数
	 * @return
	 */
	public int getCount();
	
	/**
	 * 通过角色名或角色代码获取角色数量
	 * @param paramsMap 参数集合
	 *        roleName  角色名称
	 *        roleCode  角色代码
	 *        id        主键
	 *        op        操作类型
	 * @return
	 */
	public int getRoleNumber(Map<String, Object> paramsMap);
	
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
