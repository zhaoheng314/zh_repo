package cn.edu.zzti.sys.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.zzti.common.util.Constants;
import cn.edu.zzti.sys.entity.LoginUser;
import cn.edu.zzti.sys.entity.SysPrivilege;
import cn.edu.zzti.sys.service.ILoginUserService;
import cn.edu.zzti.sys.base.BaseController;

@Controller
@Scope("prototype")
@RequestMapping(value="/main")
public class MainController extends BaseController {
	
	private static Logger logger = Logger.getLogger(MainController.class);
	
	@Autowired 
	private ILoginUserService loginUserService;
	
	/**
	 * 初始化主页面  
	 * @return main.jsp
	 */
	@RequestMapping(value="/init")
	public ModelAndView init(){
		logger.info("初始化主页面...");
		ModelAndView view = new ModelAndView();
		//将当前登录用户对象让入session
		LoginUser loginUser = (LoginUser) session.getAttribute(Constants.LONGIN_USER);
		//获取当前用户的权限集合
		List<SysPrivilege> privilegeList = loginUserService.getPrivilegeList(loginUser.getId());
		//创建父权限集合
		List<SysPrivilege> parentList = new ArrayList<SysPrivilege>();
		//创建子权限集合
		List<SysPrivilege> childList = new ArrayList<SysPrivilege>();
		for(SysPrivilege privilege:privilegeList){
			if(privilege.getParentId() == 0){    //等于0表示是父权限
				parentList.add(privilege);
			}else{                               //不等于0表示是子权限
				childList.add(privilege);
			}
		}
		view.addObject("parentList", parentList);
		view.addObject("childList", childList);
		view.addObject("loginUser", loginUser);
		view.setViewName("main/main");
		return view;
	}
}
