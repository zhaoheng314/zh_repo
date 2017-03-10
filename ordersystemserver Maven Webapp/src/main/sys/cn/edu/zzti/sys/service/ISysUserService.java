package cn.edu.zzti.sys.service;

import cn.edu.zzti.sys.entity.SysUser;

/**
 * 系统用户 业务层接口
 * @author sunwj
 *
 */
public interface ISysUserService {
	
	/**
	 * 根据主键查询用户信息
	 * @param id 主键，用户编号
	 * @return
	 */
	public SysUser selectByPrimaryKey(Long id);
	
	/**
	 * 获取用户列表，分页 
	 * @param page      当前页
	 * @param pagesize  页的大小
	 * @return
	 */
	public String getSysUserList(String page, String pagesize);
	
	/**
	 * 获取系统用户总记录条数
	 * @return
	 */
	public int getCount();
	
	/**
	 * 通过用户名获取用户数量
	 * @param sysUser 用户对象
	 * @param op      操作类型
	 * @return
	 */
	public int getUserNumber(SysUser sysUser, String op);
	
	/**
	 * 插入一条用户记录
	 * @param sysUser
	 * @return
	 */
	public int insert(SysUser sysUser);
	
	/**
	 * 根据主键重置用户密码
	 * @param id        主键
	 * @param password  密码
	 * @return
	 */
	public int resetPasswordByPrimaryKey(Long id, String password);
	
	/**
	 * 根据主键有选择的更新用户信息
	 * @param sysUser 系统用户对象
	 * @return
	 */
	public int updateByPrimaryKeySelective(SysUser sysUser);
	
	/**
	 * 根据主键删除用户信息 
	 * @param id 主键，用户编号
	 * @return
	 */
	public int deleteByPrimaryKey(Long id);
}
