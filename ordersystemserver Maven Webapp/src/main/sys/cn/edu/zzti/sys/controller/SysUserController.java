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
import cn.edu.zzti.common.util.MD5Util;
import cn.edu.zzti.sys.base.BaseController;
import cn.edu.zzti.sys.entity.SysUser;
import cn.edu.zzti.sys.service.ISysUserService;

/**
 * 系统用户 控制层
 * @author sunwj
 *
 */
@Controller
@Scope("prototype")
@RequestMapping(value="/sysUser")
public class SysUserController extends BaseController {

	private static Logger logger = Logger.getLogger(SysUserController.class);
	
	@Autowired
	private ISysUserService sysUserService;
	
	/**
	 * 跳转到用户列表页面 user_list.jsp
	 * @return
	 */
	@RequestMapping("/userList")
	public ModelAndView userlist(){
		logger.info("跳转到用户列表页面...");
		ModelAndView view = new ModelAndView();
		view.setViewName("sys/user/user_list");
		return view;
	}
	
	/**
	 * 获取当前页面的用户列表
	 * @param page 当前页
	 * @param rows 页的大小
	 */
	@RequestMapping("/getUserList")
	public void getUserList(String page, String rows){
		logger.info("分页获取用户列表...");
		String data = sysUserService.getSysUserList(page, rows);
		out(data);
	}
	
	/**
	 * 跳转到添加用户界面
	 * @return user_edit.jsp
	 */
	@RequestMapping("/addSysUser")
	public ModelAndView addSysUser(){
		logger.info("跳转到添加用户页面...");
		SysUser sysUser = new SysUser();
		ModelAndView view = new ModelAndView();
		view.addObject(Constants.OP, Constants.OP_ADD);
		view.addObject("sysUser", sysUser);
		view.setViewName("sys/user/user_edit");
		return view;
	}
	
	/**
	 * 跳转到修改用户信息界面
	 * @param id 待修改的用户编号
	 * @return user_edit.jsp
	 */
	@RequestMapping("/modifySysUser")
	public ModelAndView modifySysUser(Long id){
		logger.info("跳转到修改用户页面...");
		//根据主键查询用户信息
	    SysUser sysUser =new SysUser();
	    sysUser = sysUserService.selectByPrimaryKey(id);
		ModelAndView view = new ModelAndView();
		view.addObject(Constants.OP, Constants.OP_MODIFY);
		view.addObject("sysUser", sysUser);
		view.setViewName("sys/user/user_edit");
		return view;
	}
	
	/**
	 * 添加或修改用户信息
	 * @param sysUser 系统用户信息
	 * @param op      操作类型：添加或者修改
	 */
	@RequestMapping("/saveSysUser")
	public void saveSysUser(SysUser sysUser, String op){
		//定义结果集合
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//先判断用户名是否存在
		int count = sysUserService.getUserNumber(sysUser, op);
		//count等于0说明用户名不存在，可以添加或者修改
		if(count == 0){
			if(Constants.OP_MODIFY.equals(op)){  //修改用户信息
				//设置用户修改时间
				sysUser.setUpdateDate(DateUtil.getSystemTime());
				//修改用户
				int result = sysUserService.updateByPrimaryKeySelective(sysUser);
				//判断是否修改成功
				resultMap = CommonUtil.checkSave(result);
			}else{                               //添加用户信息
				//设置创建时间、修改时间和用户状态
				sysUser.setCreateDate(DateUtil.getSystemTime());
				sysUser.setUpdateDate(DateUtil.getSystemTime());
				sysUser.setUserStatus(1);
				//对用户密码加密,初始密码设置为1
				sysUser.setPassword(MD5Util.getMD5("1"));
				//添加用户
				int result = sysUserService.insert(sysUser);
				//判断是否添加成功
				resultMap = CommonUtil.checkSave(result);
			}
		}else{   //用户名存在，返回提示信息
			resultMap.put("msg", "此用户名已存在，请更换用户名!");
		}
		//输出返回结果至页面
		outJson(resultMap);
	}
	
	/**
	 * 根据主键删除用户信息
	 * @param id 主键
	 */
	@RequestMapping("/deleteSysUser")
	public void deleteSysUser(Long id){
		logger.info("根据主键删除用户信息...");
		//定义结果集合
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//删除用户信息
		int result = sysUserService.deleteByPrimaryKey(id);
		//判断是否删除成功
		resultMap = CommonUtil.checkDel(result);
		//输出返回结果至页面
		outJson(resultMap);
	}
	
	/**
	 * 根据主键重置用户密码
	 * @param id 主键
	 */
	@RequestMapping("/resetPassword")
	public void resetPassword(Long id){
		logger.info("管理员为系统用户重置密码...");
		//定义结果集合
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String password = MD5Util.getMD5("1");
		//重置密码
		int result = sysUserService.resetPasswordByPrimaryKey(id, password);
		//判断重置密码是否成功
		resultMap = CommonUtil.checkResetPassword(result);
		//输出返回结果至页面
		outJson(resultMap);
	}
}
