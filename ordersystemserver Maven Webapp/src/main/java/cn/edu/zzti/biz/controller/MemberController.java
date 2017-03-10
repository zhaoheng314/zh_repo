package cn.edu.zzti.biz.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.zzti.biz.entity.Member;
import cn.edu.zzti.biz.service.IMemberService;
import cn.edu.zzti.common.util.CommonUtil;
import cn.edu.zzti.common.util.Constants;
import cn.edu.zzti.common.util.DateUtil;
import cn.edu.zzti.sys.base.BaseController;

/**
 * 会员 控制层
 * @author sunwj
 *
 */
@Controller
@Scope(value="prototype")
@RequestMapping(value="/member")
public class MemberController extends BaseController {

	private Logger logger = Logger.getLogger(MemberController.class);
	
	@Autowired
	private IMemberService memberService;
	
	/**
	 * 跳转至会员列表页面
	 * @return member_list.jsp
	 */
	@RequestMapping("/memberList")
	public ModelAndView memberList(){
		logger.info("跳转至会员列表页面...");
		ModelAndView view = new ModelAndView();
		view.setViewName("biz/member/member_list");
		return view;
	}
	
	/**
	 * 获取会员列表，分页
	 * @param page 当前页
	 * @param rows 页的大小
	 */
	@RequestMapping("/getMemberList")
	public void getMemberList(String page, String rows){
		logger.info("获取会员列表...");
		String data = memberService.getMemberList(page, rows);
		//输出返回结果至页面
		out(data);
	}
	
	/**
	 * 跳转至添加会员页面
	 * @return member_edit.jsp
	 */
	@RequestMapping("/addMember")
	public ModelAndView addMember(){
		logger.info("跳转至添加会员页面...");
		ModelAndView view = new ModelAndView();
		//创建会员对象
		Member member = new Member();
		view.addObject("member", member);
		view.addObject(Constants.OP, Constants.OP_ADD);
		view.setViewName("biz/member/member_edit");
		return view;
	}
	
	/**
	 * 跳转至修改会员页面
	 * @param id 主键，会员编号
	 * @return
	 */
	@RequestMapping("/modifyMember")
	public ModelAndView modifyMember(Long id){
		logger.info("跳转至修改会员页面...");
		ModelAndView view = new ModelAndView();
		//根据主键获取会员对象
		Member member = memberService.selectByPrimiaryKey(id);
		view.addObject("member", member);
		view.addObject(Constants.OP, Constants.OP_MODIFY);
		view.setViewName("biz/member/member_edit");
		return view;
	}
	
	/**
	 * 保存会员信息
	 * @param member 会员对象
	 * @param op     操作类型
	 */
	@RequestMapping("/saveMember")
	public void saveMember(Member member, String op){
		logger.info("保存会员信息");
		//定义结果集合
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//判断会员手机号是否存在
		int count = memberService.getMemberNumber(member, op);
		//count等于0说明会员手机号不存在，可以添加或者修改
		if(count == 0){
			if(Constants.OP_MODIFY.equals(op)){   //修改会员信息
				//设置会员修改时间
				member.setUpdateDate(DateUtil.getSystemTime());
				//执行修改操作
				int result = memberService.updateByPrimaryKeySelective(member);
				//判断修改是否成功
				resultMap = CommonUtil.checkSave(result);
			}else{                                //添加会员信息
				//设置会员创建时间，修改时间和会员状态
				member.setCreateDate(DateUtil.getSystemTime());
				member.setUpdateDate(DateUtil.getSystemTime());
				member.setMemberStatus(1);
				//执行添加操作
				int result = memberService.insert(member);
				//判断是否添加成功
				resultMap = CommonUtil.checkSave(result);
			}
		}else{
			resultMap.put("msg", "此手机号已存在，请更换手机号!");
		}
		//输出返回结果至页面
		outJson(resultMap);
	}
	
	/**
	 * 根据主键删除会员信息
	 * @param id 主键，会员编号
	 */
	@RequestMapping("/deleteMember")
	public void deleteMember(Long id){
		logger.info("根据主键删除会员信息...");
		//定义结果集合
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//执行删除操作
		int result = memberService.deleteByPrimiaryKey(id);
		//判断是否删除成功
		resultMap = CommonUtil.checkDel(result);
		//输出返回结果至页面
		outJson(resultMap);
	}
}
