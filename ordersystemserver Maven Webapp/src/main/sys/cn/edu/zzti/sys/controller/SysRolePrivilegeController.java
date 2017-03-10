package cn.edu.zzti.sys.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.zzti.common.util.CommonUtil;
import cn.edu.zzti.sys.base.BaseController;
import cn.edu.zzti.sys.service.ISysRolePrivilegeService;
/**
 * 角色权限 控制层
 * @author sunwj
 *
 */
@Controller
@Scope("prototype")
@RequestMapping(value="/rolePri")
public class SysRolePrivilegeController extends BaseController {

	private Logger logger = Logger.getLogger(SysRolePrivilegeController.class);
	
	@Autowired
	private ISysRolePrivilegeService rolePrivilegeService;
	
	/**
	 * 跳转至角色权限页面
	 * @return role_privilege_list.jsp
	 */
	@RequestMapping("/rolePriList")
    public ModelAndView rolePrivilegeList(){
		logger.info("跳转至角色权限页面...");
    	ModelAndView view = new ModelAndView();
    	view.setViewName("sys/role/role_privilege_list");
		return view;
    }
	
	/**
	 * 获取角色拥有的权限信息
	 * @param roleId 角色编号
	 */
	@RequestMapping("/getRolePriList")
	public void getRolePrivilegeList(Long roleId){
		logger.info("获取角色拥有的权限信息...");
		String data = rolePrivilegeService.getPrivilegeByRoleId(roleId);
		out(data);
	}
	
	/**
	 * 批量添加角色权限记录
	 * @param roleId        角色编号
	 * @param privilegeIds  权限编号字符串
	 */
	@RequestMapping("/addRolePri")
	public void addRolePrivilege(Long roleId, String privilegeIds){
		logger.info("批量添加角色权限记录...");
		//定义结果集合
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//根据角色编号删除角色对应的权限
		rolePrivilegeService.deletePrivilegeByRoleId(roleId);
		//获取权限编号数组
		String [] privilegeIdArr = privilegeIds.split(",");
		//执行批量插入操作
		int result = rolePrivilegeService.insertList(roleId, privilegeIdArr);
		//验证批量插入是否成功
		resultMap = CommonUtil.checkSave(result);
		//输出返回结果至页面
		outJson(resultMap);
	}
	
	/**
	 * 根据角色编号删除权限信息
	 * @param roleId 角色编号
	 */
	@RequestMapping("/deleteByRoleId")
	public void deletePrivilegeByRoleId(Long roleId){
		logger.info("根据角色编号删除权限信息...");
		//定义结果结合
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//执行删除操作
		int result = rolePrivilegeService.deletePrivilegeByRoleId(roleId);
		//验证删除是否成功
		resultMap = CommonUtil.checkDel(result);
		//输出返回结果至页面
		outJson(resultMap);
	}
}
