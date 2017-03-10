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
import cn.edu.zzti.sys.dao.ISysPrivilegeDao;
import cn.edu.zzti.sys.entity.SysPrivilege;
import cn.edu.zzti.sys.service.ISysPrivilegeService;

/**
 * 系统权限 业务层实现
 * @author sunwj
 *
 */
@Service("privilegeService")
public class SysPrivilegeServiceImpl implements ISysPrivilegeService {

	private static Logger logger = Logger.getLogger(SysPrivilegeServiceImpl.class);
	
	@Autowired
	private ISysPrivilegeDao privilegeDao;

	@Override
	public SysPrivilege selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		logger.info("根据主键查询权限信息...");
		return privilegeDao.selectByPrimaryKey(id).get(0);
	}

	@Override
	public String getSysPriviliegeList(String page, String pagesize,
			Long parentId) {
		// TODO Auto-generated method stub
		logger.info("查询系统权限列表...");
		//定义参数集合，将分页参数和父权限编号封装入参数集合
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		PageUtil pageUtil = CommonUtil.getPage(page, pagesize);
		paramsMap.put("page", pageUtil);
		paramsMap.put("parentId", parentId);
		List<SysPrivilege> privilegeList = null;
		//获取系统权限总记录数
		int count = getCount(paramsMap);
		if(count <= 0){
			privilegeList = new ArrayList<SysPrivilege>();
		}else{
			//获取分页后的权限列表
			privilegeList = privilegeDao.getSysPriviliegeList(paramsMap);
			
			if(null == privilegeList || privilegeList.isEmpty()){
				privilegeList = new ArrayList<SysPrivilege>();
			}
			
		}
		return JSONUtil.getListPageGridJson(privilegeList, count, Integer.parseInt(pagesize));
	}

	@Override
	public int getCount(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		logger.info("获取系统权限总记录数...");
		return privilegeDao.getCount(paramsMap);
	}

	@Override
	public int getPrivilegeNumber(SysPrivilege sysPrivilege, String op) {
		// TODO Auto-generated method stub
		logger.info("通过权限名称获取权限数量...");
		//定义参数集合，将权限名称、主键和操作类型封装入参数集合
	    Map<String, Object> paramsMap = new HashMap<String, Object>();
	    paramsMap.put("privilegeName", sysPrivilege.getPrivilegeName());
	    paramsMap.put("id", sysPrivilege.getId());
	    paramsMap.put("op", op);
		return privilegeDao.getPrivilegeNumber(paramsMap);
	}

	@Override
	public String getParentPrivilege() {
		// TODO Auto-generated method stub
		logger.info("获取父权限列表...");
		//获取父权限集合
		List<Map<String, Object>> privilegeList = privilegeDao.getParentPrivilege();
	    //定义权限树集合
		List<Map<String, Object>> treeList = new ArrayList<Map<String,Object>>();
		//构造树的根节点
		Map<String, Object> priMap = new HashMap<String, Object>();
		priMap.put("id", 0);
		priMap.put("pid", -1);
		priMap.put("text", "父权限列表");
		priMap.put("nodes", privilegeList);
		treeList.add(priMap);
		return JSONUtil.getJson(treeList);
	}

	@Override
	public int insert(SysPrivilege sysPrivilege) {
		// TODO Auto-generated method stub
		logger.info("插入一条权限记录...");
		return privilegeDao.insert(sysPrivilege);
	}

	@Override
	public int updateByPrimaryKeySelective(SysPrivilege sysPrivilege) {
		// TODO Auto-generated method stub
		logger.info("有选择的更新权限信息...");
		return privilegeDao.updateByPrimaryKeySelective(sysPrivilege);
	}

	@Override
	public int changeStatusByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		logger.info("根据主键修改权限状态...");
		return privilegeDao.changeStatusByPrimaryKey(id);
	}
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		logger.info("根据主键山粗权限信息...");
		return privilegeDao.deleteByPrimaryKey(id);
	}
	
}
