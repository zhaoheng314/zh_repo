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
import cn.edu.zzti.sys.entity.SysRole;
import cn.edu.zzti.sys.service.ISysRoleService;

/**
 * 系统角色 控制层
 * @author sunwj
 *
 */
@Controller
@Scope(value="prototype")
@RequestMapping(value="/sysRole")
public class SysRoleController extends BaseController {

	private static Logger logger = Logger.getLogger(SysRoleController.class);
	
	@Autowired
	private ISysRoleService sysRoleService;
	
	/**
	 * 跳转到角色列表页面 role_list.jsp
	 * @return
	 */
	@RequestMapping("/roleList")
	public ModelAndView roleList(){
		logger.info("跳转到角色列表页面...");
		ModelAndView view = new ModelAndView();
		view.setViewName("sys/role/role_list");
		return view;
	}
	
	/**
	 * 获取当前页面的用户列表 
	 * @param page 当前页
	 * @param rows 页的大小
	 */
	@RequestMapping("/getRoleList")
	public void getRoleList(String page, String rows){
		logger.info("分页获取角色列表...");
		String data = sysRoleService.getSysRoleList(page, rows);
		out(data);
	}
	
	/**
	 * 跳转到添加角色界面
	 * @return role_edit.jsp
	 */
	@RequestMapping("/addSysRole")
	public ModelAndView addSysRole(){
		logger.info("跳转至添加角色页面...");
		SysRole sysRole = new SysRole();
		ModelAndView view = new ModelAndView();
		view.addObject(Constants.OP, Constants.OP_ADD);
		view.addObject("sysRole", sysRole);
		view.setViewName("sys/role/role_edit");
		return view;
	}
	
	/**
	 * 跳转至修改角色页面
	 * @param id 主键，角色编号
	 * @return role_edit.jsp
	 */
	@RequestMapping("/modifySysRole")
	public ModelAndView modifySysRole(Long id){
		logger.info("跳转至修改角色页面...");
		//根据主键查询角色信息
		SysRole sysRole = sysRoleService.selectByPrimaryKey(id);
		ModelAndView view = new ModelAndView();
		view.addObject(Constants.OP, Constants.OP_MODIFY);
		view.addObject("sysRole", sysRole);
		view.setViewName("sys/role/role_edit");
		return view;
	}
	
	/**
	 * 添加或修改角色信息
	 * @param sysRole 系统角色信息
	 * @param op      操作类型：新增或者修改
	 */
	@RequestMapping("/saveSysRole")
	public void saveSysRole(SysRole sysRole, String op){
		//定义结果集合
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//先判断角色名称和角色代码是否存在
		Map<String, Integer> map = sysRoleService.getRoleNumber(sysRole, op);
		//根据角色名称查询的角色数量
		int num1 = map.get("roleName");
		//根据角色代码查询的角色数量
		int num2 = map.get("roleCode");
		//num1和num2同时等于0说明角色名称和角色代码不存在，可以添加或者修改
		if(num1 == 0 && num2 == 0){
			if(Constants.OP_MODIFY.equals(op)){ //修改角色信息
				//设置角色修改时间
				sysRole.setUpdateDate(DateUtil.getSystemTime());
				//修改角色
				int result = sysRoleService.updateByPrimaryKeySelective(sysRole);
				//判断是否修改成功
				resultMap = CommonUtil.checkSave(result);
			}else{                              //添加角色信息 
				//设置角色创建时间、修改时间和角色状态
				sysRole.setCreateDate(DateUtil.getSystemTime());
				sysRole.setUpdateDate(DateUtil.getSystemTime());
				sysRole.setRoleStatus(1);
				//添加角色
				int result = sysRoleService.insert(sysRole);
				//判断是否添加成功
				resultMap = CommonUtil.checkSave(result);
			}
		}else{
			if(num1 != 0 && num2 != 0){  //角色名称和角色代码均存在
				resultMap.put("msg", "此角色名称和角色代码均已存在，请更换角色名称和角色代码!");				
			}else if(num1 != 0 && num2 == 0){ //角色名称存在但角色代码不存在
				resultMap.put("msg", "此角色名称已存在，请更换角色名称!");	
			}else{                      //角色名称不存在但角色代码存在
				resultMap.put("msg", "此角色代码已存在，请更换角色代码!");
			}
		}
		//输出返回结果至页面
	    outJson(resultMap);
	}
	
	/**
	 * 根据主键删除角色信息
	 * @param id 主键
	 */
	@RequestMapping("/deleteSysRole")
	public void deleteSysRole(Long id){
		logger.info("根据主键删除角色信息...");
		//定义结果集合
	    Map<String, Object> resultMap = new HashMap<String, Object>();
	    //删除角色信息
	    int result = sysRoleService.deleteByPrimaryKey(id);
		//判断是否删除成功
		resultMap = CommonUtil.checkDel(result);
		//输出返回结果至页面
		outJson(resultMap);
	}
	
	/**
	 * 获取角色树信息
	 */
	@RequestMapping("/getAllRoles")
	public void getAllRole(){
		logger.info("获取角色树信息...");
		String dataTree = sysRoleService.getAllRoles();
		out(dataTree);
	}
}
