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
import cn.edu.zzti.sys.dao.ISysRoleDao;
import cn.edu.zzti.sys.entity.SysRole;
import cn.edu.zzti.sys.service.ISysRoleService;

/**
 * 系统角色 业务层实现
 * @author sunwj
 *
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements ISysRoleService {

	private static Logger logger = Logger.getLogger(SysRoleServiceImpl.class);
	
	@Autowired
	private ISysRoleDao sysRoleDao;

	@Override
	public SysRole selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		logger.info("根据主键查询角色信息...");
		return sysRoleDao.selectByPrimaryKey(id).get(0);
	}

	@Override
	public String getSysRoleList(String page, String pagesize) {
		// TODO Auto-generated method stub
		logger.info("查询系统角色列表...");
		//定义参数集合，将分页相关的参数封装入参数集合
		Map<String, Object> paramsMap = new HashMap<String, Object>();
        PageUtil pageUtil = CommonUtil.getPage(page, pagesize);
    	paramsMap.put("page", pageUtil);
    	List<SysRole> roleList = null;
    	int count = getCount();
    	if(count <= 0){
    		roleList = new ArrayList<SysRole>();
    	}else{
    		// 获取分页内容
    		roleList = sysRoleDao.getSysRoleList(paramsMap);
    		
    		if(null == roleList || roleList.isEmpty()){
    			roleList = new ArrayList<SysRole>();
    		}
    	}
		return JSONUtil.getListPageGridJson(roleList, count, Integer.parseInt(pagesize));
	}

	@Override
	public String getAllRoles() {
		// TODO Auto-generated method stub
		logger.info("获取所有角色并构造角色树");
		//获取角色集合
		List<Map<String, Object>> roleList = sysRoleDao.getAllRoles();
		//定义角色树集合
		List<Map<String, Object>> treeList = new ArrayList<Map<String,Object>>();
		//构造树的根节点
		Map<String ,Object> rootMap = new HashMap<String, Object>();
		rootMap.put("id", 0);
		rootMap.put("pid", -1);
		rootMap.put("text", "角色列表");
		//遍历角色集合
		for(Map<String, Object> arg:roleList){
			arg.put("pid", 0);
		}
		rootMap.put("nodes", roleList);
		treeList.add(rootMap);
		return JSONUtil.getJson(treeList);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		logger.info("查询系统角色总数...");
		return sysRoleDao.getCount();
	}

	@Override
	public Map<String, Integer> getRoleNumber(SysRole sysRole, String op) {
		// TODO Auto-generated method stub
		logger.info("根据角色名称或角色代码查询用户数量..");
		//定义参数集合
		Map<String, Object> paramsMap1 = new HashMap<String, Object>();
		Map<String, Object> paramsMap2 = new HashMap<String, Object>();
		//定义结果集合
		Map<String, Integer> resultMap = new HashMap<String, Integer>();
		//将角色名称和角色代码封装入参数集合
		paramsMap1.put("roleName", sysRole.getRoleName());
		paramsMap1.put("id", sysRole.getId());
		paramsMap1.put("op", op);
		paramsMap2.put("roleCode", sysRole.getRoleCode());
		paramsMap2.put("id", sysRole.getId());
		paramsMap2.put("op", op);
		//查询角色名称是否存在
		int result1 = sysRoleDao.getRoleNumber(paramsMap1);
		//查询角色代码是否存在
		int result2 = sysRoleDao.getRoleNumber(paramsMap2);
		//封装结果集合
		resultMap.put("roleName", result1);
		resultMap.put("roleCode", result2);
		return resultMap;
	}

	@Override
	public int insert(SysRole sysRole) {
		// TODO Auto-generated method stub
		logger.info("插入一条角色记录...");
		return sysRoleDao.insert(sysRole);
	}

	@Override
	public int updateByPrimaryKeySelective(SysRole sysRole) {
		// TODO Auto-generated method stub
		logger.info("根据主键有选择的更新角色信息...");
		return sysRoleDao.updateByPrimaryKeySelective(sysRole);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		logger.info("根据主键删除角色信息...");
		return sysRoleDao.deleteByPrimaryKey(id);
	}
	
}
