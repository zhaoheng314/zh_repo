package cn.edu.zzti.biz.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zzti.biz.dao.IMemberDao;
import cn.edu.zzti.biz.entity.Member;
import cn.edu.zzti.biz.service.IMemberService;
import cn.edu.zzti.common.util.CommonUtil;
import cn.edu.zzti.common.util.JSONUtil;
import cn.edu.zzti.common.util.PageUtil;

/**
 * 餐桌 业务层实现
 * @author sunwj
 *
 */
@Service("memberService")
public class MemberServiceImpl implements IMemberService {

	private static Logger logger = Logger.getLogger(MemberServiceImpl.class);
	
	@Autowired
	private IMemberDao memberDao;

	@Override
	public Member selectByPrimiaryKey(Long id) {
		// TODO Auto-generated method stub
		logger.info("根据主键获取会员对象...");
		return memberDao.selectByPrimiaryKey(id);
	}

	@Override
	public String getMemberList(String page, String pagesize) {
		// TODO Auto-generated method stub
		logger.info("获取会员列表，分页...");
		//定义参数集合
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		//获取分页参数
		PageUtil pageUtil = CommonUtil.getPage(page, pagesize);
		//将分页参数封装入参数集合
		paramsMap.put("page", pageUtil);
		//定义会员列表
		List<Member> memberList = null;
		//获取会员数量
		int count = getCount();
		if(count <= 0){
			memberList = new ArrayList<Member>();
		}else{
			//获取会员列表
			memberList = memberDao.getMemberList(paramsMap);
			
			if(null == memberList ||memberList.isEmpty()){
				memberList = new ArrayList<Member>();
			}
		}
		return JSONUtil.getListPageGridJson(memberList, count, Integer.parseInt(pagesize));
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		logger.info("获取会员数量... ");
		return memberDao.getCount();
	}

	@Override
	public int getMemberNumber(Member member, String op) {
		// TODO Auto-generated method stub
		logger.info("通过会员手机号获取会员数量...");
		//定义参数集合
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		//将会员手机号、操作类型和主键封装入参数集合、
		paramsMap.put("phone", member.getPhone());
		paramsMap.put("op", op);
		paramsMap.put("id", member.getId());
		return memberDao.getMemberNumber(paramsMap);
	}

	@Override
	public int insert(Member member) {
		// TODO Auto-generated method stub
		logger.info("插入一条会员记录...");
		return memberDao.insert(member);
	}

	@Override
	public int updateByPrimaryKeySelective(Member member) {
		// TODO Auto-generated method stub
		logger.info("根据主键有选择的更新会员信息...");
		return memberDao.updateByPrimaryKeySelective(member);
	}

	@Override
	public int deleteByPrimiaryKey(Long id) {
		// TODO Auto-generated method stub
		logger.info("根据主键删除会员信息...");
		return memberDao.deleteByPrimiaryKey(id);
	}
}
