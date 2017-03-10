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
import cn.edu.zzti.common.util.Constants;
import cn.edu.zzti.common.util.DateUtil;
import cn.edu.zzti.sys.base.BaseController;
import cn.edu.zzti.sys.entity.SysPrivilege;
import cn.edu.zzti.sys.service.ISysPrivilegeService;

/**
 * 系统权限 控制层s
 * @author sunwj
 *
 */
@Controller
@Scope(value="prototype")
@RequestMapping(value="/sysPri")
public class SysPrivilegeController extends BaseController {

	private static Logger logger = Logger.getLogger(SysPrivilegeController.class);
	
	@Autowired
	private ISysPrivilegeService privilegeService;
	
	/**
	 * 跳转至权限列表页面 
	 * @return privilege_list.jsp
	 */
	@RequestMapping("/priList")
	public ModelAndView privilegeList(){
		logger.info("跳转至权限列表页面...");
		ModelAndView view = new ModelAndView();
		view.setViewName("sys/privilege/privilege_list");
		return view;
	}
	
	/**
	 * 获取权限列表，分页获取
	 * @param page      当前页
	 * @param rows      页的大小
	 * @param parentId  父权限编号
	 */
	@RequestMapping("/getPriList")
	public void getPrivilegeList(Long parentId, String page, String rows){
		logger.info("分页获取权限列表...");		
		String data = privilegeService.getSysPriviliegeList(page, rows, parentId);
		out(data);
	}
	
	/**
	 * 获取父权限菜单
	 */
	@RequestMapping("/getParentPri")
	public void getParentPrivilege(){
		logger.info("获取父权限菜单");
		String dataTree = privilegeService.getParentPrivilege();
		out(dataTree);
	}
	
	/**
	 * 跳转至添加权限页面
	 * @param  parentId 父权限编号
	 * @return privilege_edit.jsp
	 */
	@RequestMapping("/addSysPri")
	public ModelAndView addSysPrivilege(Long parentId){
		logger.info("跳转至添加权限页面...");
		ModelAndView view = new ModelAndView();
		//创建系统权限对象
		SysPrivilege sysPrivilege = new SysPrivilege();
		sysPrivilege.setParentId(parentId);
		view.setViewName("sys/privilege/privilege_edit");
		view.addObject("sysPrivilege", sysPrivilege);
		view.addObject("op", Constants.OP_ADD);
		return view;
	}
	
	/**
	 * 跳转至修改权限页面
	 * @param id 主键，权限编号
	 * @return privilege_edit.jsp
	 */
	@RequestMapping("/modifySysPri")
	public ModelAndView modifySysPrivilege(Long id){
		logger.info("跳转至修改权限页面...");
		ModelAndView view = new ModelAndView();
		//根据主键获取系统权限对象
		SysPrivilege sysPrivilege = privilegeService.selectByPrimaryKey(id);
		view.setViewName("sys/privilege/privilege_edit");
		view.addObject("sysPrivilege", sysPrivilege);
		view.addObject("op", Constants.OP_MODIFY);
		return view;
	}
	
	/**
	 * 添加或修改权限信息 
	 * @param sysPrivilege 系统权限信息
	 * @param op 操作类型：新增或修改
	 */
	@RequestMapping("/saveSysPri")
	public void saveSysPrivilege(SysPrivilege sysPrivilege, String op){
		//定义结果集合
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//先判断权限名称是否存在
		int count = privilegeService.getPrivilegeNumber(sysPrivilege, op);
		if(count == 0){
			if(Constants.OP_MODIFY.equals(op)){   //修改权限信息
				 //设置权限修改时间
				 sysPrivilege.setUpdateDate(DateUtil.getSystemTime());
				 //修改权限信息
				 int result = privilegeService.updateByPrimaryKeySelective(sysPrivilege);
				 //判断是否修改成功
				 resultMap = CommonUtil.checkSave(result);
			}else{                                //添加权限信息
				 //设置权限创建时间、修改时间和权限状态
				 sysPrivilege.setCreateDate(DateUtil.getSystemTime());
				 sysPrivilege.setUpdateDate(DateUtil.getSystemTime());
				 sysPrivilege.setPrivilegeStatus(1);
				 //添加权限
				 int result = privilegeService.insert(sysPrivilege);
				 //判断是否添加成功
				 resultMap = CommonUtil.checkSave(result);
			}
		}else{
			//权限名称存在
			resultMap.put("msg", "此权限名称已存在，请更换权限名称!");			
		}
		//输出返回结果至页面
		outJson(resultMap);
	}
	
	/**
	 * 根据主键删除权限信息
	 * @param id 主键，权限编号
	 */
	@RequestMapping("/deleteSysPri")
	public void deleteSysPrivilege(Long id){
		logger.info("根据主键删除权限信息...");
		//定义结果集合
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//删除用户信息
		int result = privilegeService.deleteByPrimaryKey(id);
		//判断是否删除成功
	    resultMap = CommonUtil.checkDel(result);
	    //输出返回结果至页面
	    outJson(resultMap);
	}
	
	/**
	 * 根据主键修改权限状态 启用/禁用
	 * @param id 主键，权限编号
	 */
	@RequestMapping("/changeStatus")
	public void changeStatus(Long id){
		logger.info("根据主键修改权限状态...");
		//定义结果集合
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//修改权限状态
		int result = privilegeService.changeStatusByPrimaryKey(id);
		//判断修改状态是否成功
		resultMap = CommonUtil.checkChangeStatus(result);
		//输出返回结果至页面
		outJson(resultMap);
	}
}
