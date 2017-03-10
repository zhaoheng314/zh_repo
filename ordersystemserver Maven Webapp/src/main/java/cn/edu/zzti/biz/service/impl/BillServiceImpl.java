package cn.edu.zzti.biz.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zzti.biz.dao.IBillDao;
import cn.edu.zzti.biz.entity.Bill;
import cn.edu.zzti.biz.service.IBillService;
import cn.edu.zzti.common.util.CommonUtil;
import cn.edu.zzti.common.util.JSONUtil;
import cn.edu.zzti.common.util.PageUtil;
/**
 * 账单 业务层 实现 
 * @author sunwj
 *
 */
@Service("billService")
public class BillServiceImpl implements IBillService {

	private static Logger logger = Logger.getLogger(BillServiceImpl.class);
	
	@Autowired
	private IBillDao billDao;

	@Override
	public Bill selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		logger.info("根据主键查询账单信息...");
		return billDao.selectByPrimaryKey(id);
	}

	@Override
	public String getBillList(String page, String pagesize) {
		// TODO Auto-generated method stub
		logger.info("获取账单列表，分页...");
		//定义参数集合
		Map<String, Object> paramsMap = new HashMap<String,Object>();
		//获得分页参数
		PageUtil pageUtil = CommonUtil.getPage(page, pagesize);
		//将分页参数封装入参数集合
		paramsMap.put("page", pageUtil);
		//获取账单总数
		int count = getCount();
		//定义账单集合
		List<Bill> billList = null;
		if(count <= 0){
			billList = new ArrayList<Bill>();
		}else{
			//获得分页内容
			billList = billDao.getBillList(paramsMap);
			
			if(null == billList || billList.isEmpty()){
				billList = new ArrayList<Bill>();
			}
		}
		return JSONUtil.getListPageGridJson(billList, count, Integer.parseInt(pagesize));
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		logger.info("获取账单总数...");
		return billDao.getCount();
	}

	@Override
	public int insert(Bill bill) {
		// TODO Auto-generated method stub
		logger.info("插入一条账单记录...");
		return billDao.insert(bill);
	}

	@Override
	public int updateByPrimaryKeySelective(Bill bill) {
		// TODO Auto-generated method stub
		logger.info("有选择的更新账单信息 ...");
		return billDao.updateByPrimaryKeySelective(bill);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		logger.info("根据主键删除账单信息...");
		return billDao.deleteByPrimaryKey(id);
	}
}
