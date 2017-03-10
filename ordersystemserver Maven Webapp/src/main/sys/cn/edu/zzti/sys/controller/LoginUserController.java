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
import cn.edu.zzti.sys.entity.LoginUser;
import cn.edu.zzti.sys.service.ILoginUserService;

/**
 * 系统登录用户 控制层
 * @author sunwj
 *
 */
@Controller
@Scope("prototype")
@RequestMapping(value="/login")
public class LoginUserController extends BaseController {

	private static Logger logger = Logger.getLogger(LoginUserController.class);
	
    @Autowired
	private ILoginUserService loginUserService;
	
	/**
	 * 初始化登录页面
	 * @return login.jsp
	 */
	@RequestMapping(value="/init")
	public ModelAndView init(){
		ModelAndView view = new ModelAndView();
		view.setViewName("login/login");
		return view;  
	}
	
	/**
	 * 处理用户登录请求
	 * @param username 用户名
	 * @param password 密码
	 */
	@RequestMapping(value="/doLogin")
	public void doLogin(String username, String password){
		Map<String,Object> retMap = new HashMap<String,Object>();
		//判断用户名密码是否为空
		if(CommonUtil.isNull(username) || CommonUtil.isNull(password)){
			retMap.put(Constants.RESULT_CODE, Constants.RETURN_FAIL_FLAG);
			retMap.put(Constants.RESULT_MSG, "用户名或密码不能为空！");
		}else{
			
			LoginUser loginUser = loginUserService.selectByUsername(username);
			if(null != loginUser){
				//判断密码
				if(MD5Util.getMD5(password).equals(loginUser.getPassword())){					
					retMap.put(Constants.RESULT_CODE, Constants.RESULT_SUCCESS_FLAG);
					retMap.put(Constants.RESULT_MSG, "登录成功！");
					session.setAttribute(Constants.LONGIN_USER, loginUser);
					logger.info(username+":登录成功，时间："+DateUtil.getSystemTime());					

				}else{
					retMap.put(Constants.RESULT_CODE, Constants.RETURN_FAIL_FLAG);
					retMap.put(Constants.RESULT_MSG, "密码错误！");
					logger.info(username+":密码错误，时间："+DateUtil.getSystemTime());		
				}
			}else{
				retMap.put(Constants.RESULT_CODE, Constants.RETURN_FAIL_FLAG);
				retMap.put(Constants.RESULT_MSG, "当前用户不存在！");
				logger.info(username+":当前用户不存在，时间："+DateUtil.getSystemTime());		
			}
		}
		outJson(retMap);
	}
	
	/**
	 * 注销登录 
	 * @return login.jsp
	 */
	@RequestMapping(value="/logout")
	public ModelAndView logout(){
		ModelAndView view = new ModelAndView();
		logger.info("执行注销登录...");
		session.removeAttribute(Constants.LONGIN_USER);
		session.invalidate();
		view.setViewName("login/login");
		return view;
	}
}
