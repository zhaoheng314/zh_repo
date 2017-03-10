package cn.edu.zzti.sys.dao;

import java.util.List;

import cn.edu.zzti.sys.entity.LoginUser;
/**
 * 系统用户登录 数据访问层接口
 * @author sunwj
 *
 */
public interface ILoginUserDao {
	
	/**
	 * 根据登录时的用户名获得用户对象
	 * @param username 用户名
	 * @return
	 */
	public List<LoginUser> selectByUsername(String username);
}
