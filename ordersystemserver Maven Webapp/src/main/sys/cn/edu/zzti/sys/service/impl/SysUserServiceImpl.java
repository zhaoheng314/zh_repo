package cn.edu.zzti.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zzti.common.util.CommonUtil;
import cn.edu.zzti.common.util.JSONUtil;
import cn.edu.zzti.common.util.PageUtil;
import cn.edu.zzti.sys.dao.ISysUserDao;
import cn.edu.zzti.sys.entity.SysUser;
import cn.edu.zzti.sys.service.ISysUserService;
/**
 * 系统用户 业务层实现
 * @author sunwj
 *
 */
@Service("sysUserService")
public class SysUserServiceImpl implements ISysUserService {

	private static Logger logger = Logger.getLogger(SysUserServiceImpl.class);
	
	@Autowired
	private ISysUserDao sysUserDao;
	
	@Override
	public SysUser selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		logger.info("根据主键查询用户信息...");
		return sysUserDao.selectByPrimaryKey(id).get(0);
	}
	
	@Override
	public String getSysUserList(String page, String pagesize) {
		// TODO Auto-generated method stub
		logger.info("查询系统用户列表...");
		//定义参数集合，将分页相关的参数封装入参数集合
		Map<String, Object> paramsMap = new HashMap<String, Object>();
        PageUtil pageUtil = CommonUtil.getPage(page, pagesize);
    	paramsMap.put("page", pageUtil);
    	List<SysUser> userList = null;
    	// 获取系统用户总的记录数
    	int count = getCount();
    	if(count <= 0){
    		userList = new ArrayList<SysUser>();
    	}else{
    		// 获取分页内容
    		userList = sysUserDao.getSysUserList(paramsMap);
        	
        	if(null == userList || userList.isEmpty()){
        		userList = new ArrayList<SysUser>();
        	}
    	}
    	
    	return JSONUtil.getListPageGridJson(userList, count, Integer.parseInt(pagesize));
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		logger.info("查询系统用户总数...");
		return sysUserDao.getCount();
	}

	@Override
	public int getUserNumber(SysUser sysUser, String op) {
		// TODO Auto-generated method stub
		logger.info("根据用户名查询用户数量..");
		//定义参数集合
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		//将用户名、用户编号和操作类型封装入参数集合
		paramsMap.put("username", sysUser.getUsername());
		paramsMap.put("id", sysUser.getId());
		paramsMap.put("op", op);
		return sysUserDao.getUserNumber(paramsMap);
	}

	@Override
	public int insert(SysUser sysUser) {
		// TODO Auto-generated method stub
		logger.info("插入一条用户记录...");
		return sysUserDao.insert(sysUser);
	}
	
	@Override
	public int resetPasswordByPrimaryKey(Long id, String password) {
		// TODO Auto-generated method stub
		logger.info("根据主键重置用户密码...");
		//定义参数集合，将主键和密码封装入参数集合
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("id", id);
		paramsMap.put("password", password);
		return sysUserDao.resetPasswordByPrimaryKey(paramsMap);
	}
	
	@Override
	public int updateByPrimaryKeySelective(SysUser sysUser) {
		// TODO Auto-generated method stub
		logger.info("根据主键有选择的更新用户信息...");
		return sysUserDao.updateByPrimaryKeySelective(sysUser);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		logger.info("根据主键删除用户信息...");
		return sysUserDao.deleteByPrimaryKey(id);
	}

}
