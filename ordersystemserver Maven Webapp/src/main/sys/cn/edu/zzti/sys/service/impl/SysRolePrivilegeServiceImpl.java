package cn.edu.zzti.sys.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zzti.common.util.DateUtil;
import cn.edu.zzti.common.util.JSONUtil;
import cn.edu.zzti.sys.dao.ISysPrivilegeDao;
import cn.edu.zzti.sys.dao.ISysRolePrivilegeDao;
import cn.edu.zzti.sys.entity.SysRolePrivilege;
import cn.edu.zzti.sys.service.ISysRolePrivilegeService;

/**
 * 角色权限 业务层实现
 * @author sunwj
 *
 */
@Service("rolePrivilegeService")
public class SysRolePrivilegeServiceImpl implements ISysRolePrivilegeService {

	private Logger logger = Logger.getLogger(SysRolePrivilegeServiceImpl.class);
	
	@Autowired
	private ISysRolePrivilegeDao rolePrivilegeDao;
	
	@Autowired
	private ISysPrivilegeDao privilegeDao;

	@Override
	public String getPrivilegeByRoleId(Long roleId) {
		// TODO Auto-generated method stub
		logger.info("根据角色编号获取权限编号信息...");
		//获取父权限集合
		List<Map<String, Object>> parentPriList = privilegeDao.getParentPrivilege();
		//获取角色拥有的权限集合
		List<Long> priIdList = rolePrivilegeDao.getPrivilegeByRoleId(roleId);
		//定义所有权限集合
		List<Map<String, Object>> allPriList = new ArrayList<Map<String,Object>>();
		//遍历父权限集合
		for(Map<String, Object> pMap:parentPriList){
			//根据父权限编号获取子权限信息
			Long pid = Long.parseLong(String.valueOf(pMap.get("id")));
			//设置父节点展开
			if(priIdList.contains(pid)){
				//设置选中
				pMap.put("ischecked", true);
			}else{
				//设置不选中
				pMap.put("ischecked", false);
			}
			List<Map<String, Object>> childPriList = privilegeDao.getPrivilegeByPID(pid);
			//遍历子权限集合
			for(Map<String, Object> cMap:childPriList){
				Long id = Long.parseLong(String.valueOf(cMap.get("id")));
				if(priIdList.contains(id)){
					//设置选中
					cMap.put("ischecked", true);
				}else{
					//设置不选中
					cMap.put("ischecked", false);
				}
			}
			//将子权限加入所有权限集合
			allPriList.addAll(childPriList);
		}
		//将父权限加入所有权限集合
		allPriList.addAll(parentPriList);
		return JSONUtil.getJson(allPriList);
	}

	@Override
	public int insertList(Long roleId, String[] privilegeIdArr) {
		// TODO Auto-generated method stub
		logger.info("批量插入角色权限记录...");
		//定义角色权限集合
		List<SysRolePrivilege> rolePrivilegeList = new ArrayList<SysRolePrivilege>();
		//遍历权限编号数组
		for(String privilegeId:privilegeIdArr){
			//创建角色权限对象
			SysRolePrivilege sysRolePrivilege = new SysRolePrivilege();
			sysRolePrivilege.setRoleId(roleId);
			sysRolePrivilege.setPrivilegeId(Long.parseLong(privilegeId));
			sysRolePrivilege.setCreateDate(DateUtil.getSystemTime());
			//将角色权限对象加入集合
			rolePrivilegeList.add(sysRolePrivilege);
		}
		return rolePrivilegeDao.insertList(rolePrivilegeList);
	}

	@Override
	public int deletePrivilegeByRoleId(Long roleId) {
		// TODO Auto-generated method stub
		logger.info("根据角色编号删除角色拥有的权限信息...");
		return rolePrivilegeDao.deletePrivilegeByRoleId(roleId);
	}

}
