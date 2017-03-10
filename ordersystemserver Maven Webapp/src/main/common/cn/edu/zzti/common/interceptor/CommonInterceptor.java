package cn.edu.zzti.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.zzti.common.util.CommonUtil;
import cn.edu.zzti.common.util.Constants;

public  class CommonInterceptor implements HandlerInterceptor{

	//允许通过的url请求
	private String[] allowUrls;
		
	public String[] getAllowUrls() {
		return allowUrls;
	}

	public void setAllowUrls(String[] allowUrls) {
		this.allowUrls = allowUrls;
	}
	
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		// TODO Auto-generated method stub
		String requstUrl = arg0.getRequestURI();
		if (!CommonUtil.isNull(allowUrls)) {
			for (String url : allowUrls) {
				if (requstUrl.endsWith(url)) {
					return true;
				}
			}
		}
		
		Object loginUser = arg0.getSession().getAttribute(Constants.LONGIN_USER);
		if (CommonUtil.isNull(loginUser)) {
			arg1.sendRedirect(arg0.getContextPath()+"/login/init.do");
		}else {
			return true;
		}
		return false;
	}

	
}
