package cn.edu.zzti.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zzti.common.util.CommonUtil;
import cn.edu.zzti.common.util.DateUtil;
import cn.edu.zzti.common.util.JSONUtil;
import cn.edu.zzti.common.util.PageUtil;
import cn.edu.zzti.sys.dao.ISysRoleUserDao;
import cn.edu.zzti.sys.entity.SysRoleUser;
import cn.edu.zzti.sys.service.ISysRoleUserService;
/**
 * 角色用户 业务层实现
 * @author sunwj
 *
 */
@Service("roleUserService")
public class SysRoleUserServiceImpl implements ISysRoleUserService {

	private static Logger logger = Logger.getLogger(SysRoleUserServiceImpl.class);
	
	@Autowired
	private ISysRoleUserDao sysRoleUserDao;
	
	@Override
	public String getRoleUserList(String page, String pagesize, Long roleId) {
		// TODO Auto-generated method stub
		logger.info("查询拥有角色的用户列表...");
		//定义参数集合，将分页相关的参数封装入参数集合
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		PageUtil pageUtil = CommonUtil.getPage(page, pagesize);
		paramsMap.put("page", pageUtil);
		//将角色编号封装入参数集合
		paramsMap.put("roleId", roleId);
		//定义角色用户集合
		List<Map<String, Object>> roleUserList = null;
		//获取角色用户的总记录数
		int count = getRoleUserNumber(paramsMap);
		if(count <= 0){
			roleUserList = new ArrayList<Map<String, Object>>();
		}else{
			//获取分页内容
			roleUserList = sysRoleUserDao.getRoleUserList(paramsMap);
			
			if(null == roleUserList || roleUserList.isEmpty()){
				roleUserList = new ArrayList<Map<String,Object>>();
			}
		}
		return JSONUtil.getListPageGridJson(roleUserList, count, Integer.parseInt(pagesize));
	}

	@Override
	public String getNoRoleUserList(String page, String pagesize) {
		// TODO Auto-generated method stub
		logger.info("查询没有角色的用户列表...");
		//定义参数集合，将分页相关的参数封装入参数集合
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		PageUtil pageUtil = CommonUtil.getPage(page, pagesize);
		paramsMap.put("page", pageUtil);
		//定义没有角色的用户集合
		List<Map<String, Object>> noRoleUserList = null;
		//获取没有角色的用户数量
		int count = getNoRoleUserNumber();
		if(count <= 0){
			noRoleUserList = new ArrayList<Map<String,Object>>();
		}else{
			//获取分页内容
			noRoleUserList = sysRoleUserDao.getNoRoleUserList(paramsMap);
			
			if(null == noRoleUserList || noRoleUserList.isEmpty()){
				noRoleUserList = new ArrayList<Map<String,Object>>();
			}
		}
		return JSONUtil.getListPageGridJson(noRoleUserList, count, Integer.parseInt(pagesize));
	}

	@Override
	public int getRoleUserNumber(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		logger.info("获取拥有角色的用户数量...");
		return sysRoleUserDao.getRoleUserNumber(paramsMap);
	}

	@Override
	public int getNoRoleUserNumber() {
		// TODO Auto-generated method stub
		logger.info("获取没有角色的用户数量...");
		return sysRoleUserDao.getNoRoleUserNumber();
	}

	@Override
	public int insertList(Long roleId, String [] userIdArr) {
		// TODO Auto-generated method stub
		logger.info("批量插入角色用户记录...");
		//定义角色用户集合
		List<SysRoleUser> roleUserList = new ArrayList<SysRoleUser>();
		//遍历用户编号数组
		for(String userId:userIdArr){
			//创建角色用户对象
			SysRoleUser sysRoleUser = new SysRoleUser();
			sysRoleUser.setRoleId(roleId);
			sysRoleUser.setUserId(Long.parseLong(userId));
			sysRoleUser.setCreateDate(DateUtil.getSystemTime());
			//将角色用户对象加入集合
			roleUserList.add(sysRoleUser);
		}
		return sysRoleUserDao.insertList(roleUserList);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		logger.info("根据主键删除角色用户记录...");
		return sysRoleUserDao.deleteByPrimaryKey(id);
	}

}
