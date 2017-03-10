package cn.edu.zzti.sys.dao;

import java.util.List;
import java.util.Map;

import cn.edu.zzti.sys.entity.SysUser;

/**
 * 系统用户 数据访问层接口
 * @author sunwj
 *
 */
public interface ISysUserDao {
	
	/**
	 * 根据主键查询用户信息
	 * @param id 主键，用户编号
	 * @return
	 */
	public List<SysUser> selectByPrimaryKey(Long id);
	
	/**
	 *  获取用户列表，分页 
	 * @param paramsMap 参数集合
	 *        start     起始值
	 *        limit     页的大小
	 * @return
	 */
	public List<SysUser> getSysUserList(Map<String, Object> paramsMap);
	
	/**
	 * 获取系统用户总记录条数
	 * @return
	 */
	public int getCount();
	
	/**
	 * 通过用户名获取用户数量
	 * @param paramsMap 参数集合
	 *        usernamae 用户名
	 *        id        主键
	 *        op        操作类型
	 * @return
	 */
	public int getUserNumber(Map<String, Object> paramsMap);
	
	/**
	 * 插入一条用户记录
	 * @param sysUser 系统用户对象
	 * @return
	 */
	public int insert(SysUser sysUser);
	
	/**
	 * 根据主键重置用户密码
	 * @param paramsMap 参数集合
	 *        id        主键
	 *        password  密码
	 * @return
	 */
	public int resetPasswordByPrimaryKey(Map<String, Object> paramsMap);
	
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
