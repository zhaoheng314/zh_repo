package cn.edu.zzti.sys.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import cn.edu.zzti.common.util.Constants;
import cn.edu.zzti.common.util.JSONUtil;
import cn.edu.zzti.sys.entity.LoginUser;

public class BaseController {
	protected HttpServletResponse response;
	protected HttpServletRequest request;
	protected HttpSession session;

	protected String operDescription = "";//操作描述


	/**
	 * 日期格式化
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		dateFormat.setLenient(false);	
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,true));
	}


	/**
	 * 当前登录用户
	 * @return
	 */
	protected LoginUser getLoginUser(){
		return (LoginUser) session.getAttribute(Constants.LONGIN_USER);
	}



	/**
	 * @ModelAttribute放置在方法上面：表示请求该类的每个Action前都会首先执行它， 你可以将一些准备数据的操作放置在该方法里面
	 */
	@ModelAttribute
	public void setReqAndResp(HttpServletResponse response, HttpServletRequest request) {
		this.response = response;
		this.request = request;
		this.session = request.getSession();
	}


	/**
	 * 输出
	 * @param value
	 */
	protected void out(String value) {
		PrintWriter out = null;
		try {
			response.setContentType("text/html;charset=UTF-8");
			out = response.getWriter();
			out.println(value);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}


	/**
	 * 输出json格式
	 * @param obj
	 */
	protected void outJson(Object obj) {
		out(JSONUtil.getJson(obj));
	}

}
