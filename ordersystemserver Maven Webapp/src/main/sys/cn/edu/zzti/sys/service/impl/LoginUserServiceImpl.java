package cn.edu.zzti.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zzti.common.util.CommonUtil;
import cn.edu.zzti.sys.dao.ILoginUserDao;
import cn.edu.zzti.sys.dao.ISysPrivilegeDao;
import cn.edu.zzti.sys.dao.ISysRoleUserDao;
import cn.edu.zzti.sys.entity.LoginUser;
import cn.edu.zzti.sys.entity.SysPrivilege;
import cn.edu.zzti.sys.service.ILoginUserService;
/**
 * 系统用户登录 业务层实现
 * @author sunwj
 *
 */
@Service("loginUserService")
public class LoginUserServiceImpl implements ILoginUserService {

	private static Logger logger = Logger.getLogger(LoginUserServiceImpl.class);
	
	@Autowired
	private ILoginUserDao loginUserDao;
	
	@Autowired 
	private ISysRoleUserDao roleUserDao;
	
	@Autowired
	private ISysPrivilegeDao privilegeDao;
	
	@Override
	public LoginUser selectByUsername(String username) {
		// TODO Auto-generated method stub
		logger.info("根据用户名获得用户对象...");
		List<LoginUser> userList = loginUserDao.selectByUsername(username);
		if(null != userList && !userList.isEmpty() && userList.size() > 0){
			return userList.get(0);
		}else{
			return null;
		}
	}

	@Override
	public List<SysPrivilege> getPrivilegeList(Long userId) {
		logger.info("获取登录用户的权限集合...");
		// TODO Auto-generated method stub
		//获得当前登录用户的角色编号
		Long roleId = roleUserDao.getRoleIdByUserId(userId);
		List<SysPrivilege> privilegeList = null;
		if(CommonUtil.isNull(roleId)){
			privilegeList = new ArrayList<SysPrivilege>();
		}else{
			//获取角色对应的权限集合
			privilegeList = privilegeDao.getPrivilegeByRoleId(roleId);
		}
		return privilegeList;
	}

}
