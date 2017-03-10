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
import cn.edu.zzti.sys.service.ISysRoleUserService;
/**
 * 角色用户 控制层
 * @author sunwj
 *
 */
@Controller
@Scope(value="prototype")
@RequestMapping(value="/roleUser")
public class SysRoleUserController extends BaseController {

	private static Logger logger = Logger.getLogger(SysRoleUserController.class);
	
	@Autowired
	private ISysRoleUserService roleUserService;
	
	/**
	 * 跳转至角色用户页面
	 * @return
	 */
	@RequestMapping("/roleUserList")
	public ModelAndView roleUserList(){
		logger.info("跳转至角色用户页面...");
		ModelAndView view = new ModelAndView();
		view.setViewName("sys/role/role_user_list");
		return view;
	}
	
	/**
	 * 获取拥有角色的用户列表
	 * @param roleId 角色编号
	 * @param page 当前页
	 * @param rows 页的大小
	 * @return
	 */
	@RequestMapping("/getRoleUserList")
	public void getRoleUserList(Long roleId, String page, String rows){
		logger.info("获取拥有角色的用户列表...");
		String data = roleUserService.getRoleUserList(page, rows, roleId);
		out(data);
	}
	
	/**
	 * 获取没有角色的用户列表
	 * @param page 当前页
	 * @param rows 页的大小
	 */
	@RequestMapping("/getNoRoleUserList")
	public void getNoRoleUserList(String page, String rows){
		logger.info("获取没有角色的用户列表...");
		String data = roleUserService.getNoRoleUserList(page, rows);
		out(data);
	}
	
	/**
	 * 批量为用户赋予角色
	 * @param roleId  角色编号
	 * @param userIds 用户编号字符串
	 */
	@RequestMapping("/addRoleUser")
	public void addRoleUser(Long roleId, String userIds){
		logger.info("批量为用户赋予角色...");
		//定义结果集合
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//获取用户编号数组
		String [] userIdArr = userIds.split(",");
		//执行批量插入操作
		int result = roleUserService.insertList(roleId, userIdArr);
		//判断批量插入是否成功
		resultMap = CommonUtil.checkSave(result);
		//输出返回结果至页面
	    outJson(resultMap);
	}
	
	/**
	 * 根据主键删除角色用户记录
	 * @param id 主键
	 */
	@RequestMapping("/deleteRoleUser")
	public void deleteRoleUser(Long id){
		logger.info("根据主键删除角色用户记录...");
		//定义结果集合
		Map<String, Object> resultMap = new HashMap<String, Object>();
	    //根据主键删除角色用户记录
		int result = roleUserService.deleteByPrimaryKey(id);
		//判断是否删除成功
		resultMap = CommonUtil.checkDel(result);
		//输出返回结果至页面
		outJson(resultMap);
	}
}
