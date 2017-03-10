package cn.edu.zzti.sys.service;

import java.util.List;

import cn.edu.zzti.sys.entity.LoginUser;
import cn.edu.zzti.sys.entity.SysPrivilege;
/**
 * 系统用户登录 业务层
 * @author sunwj
 *
 */
public interface ILoginUserService {
	
	/**
	 * 根据登录时的用户名获得用户对象 
	 * @param username 用户名
	 * @return
	 */
	public LoginUser selectByUsername(String username);
	
	/**
	 * 获取登录用户的权限集合
	 * @param userId 用户编号
	 * @return
	 */
	public List<SysPrivilege> getPrivilegeList(Long userId);
}
