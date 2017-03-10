package cn.edu.zzti.biz.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zzti.biz.dao.IDiningTableDao;
import cn.edu.zzti.biz.entity.DiningTable;
import cn.edu.zzti.biz.service.IDiningTableService;
import cn.edu.zzti.common.util.CommonUtil;
import cn.edu.zzti.common.util.JSONUtil;
import cn.edu.zzti.common.util.PageUtil;

/**
 * 餐桌 业务层实现
 * @author sunwj
 *
 */
@Service("tableService")
public class DiningTableServiceImpl implements IDiningTableService {

	private Logger logger = Logger.getLogger(DiningTableServiceImpl.class);
	
	@Autowired
	private IDiningTableDao tableDao;

	@Override
	public DiningTable selectByPrimiaryKey(Long id) {
		// TODO Auto-generated method stub
		logger.info("根据主键获取餐桌对象...");
		return tableDao.selectByPrimiaryKey(id);
	}

	@Override
	public String getDiningTableList(String page, String pagesize) {
		// TODO Auto-generated method stub
		logger.info("获取餐桌列表，分页...");
		//定义参数集合
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		//获取分页参数
		PageUtil pageUtil = CommonUtil.getPage(page, pagesize);
		//将分页参数封装入参数集合
		paramsMap.put("page", pageUtil);
		//定义餐桌列表
		List<DiningTable> tableList = null;
		//获取餐桌总数
		int count = getCount();
		if(count <= 0){
			tableList = new ArrayList<DiningTable>();
		}else{
			//获取分页内容
			tableList = tableDao.getDiningTableList(paramsMap);
			
			if(null == tableList || tableList.isEmpty()){
				tableList = new ArrayList<DiningTable>();
			}
		}
		return JSONUtil.getListPageGridJson(tableList, count, Integer.parseInt(pagesize));
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		logger.info("获取餐桌总数...");
		return tableDao.getCount();
	}

	@Override
	public int getTableNumber(DiningTable table, String op) {
		// TODO Auto-generated method stub
		logger.info("获取餐桌数量...");
		//定义参数集合
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		//将餐桌代码、餐桌编号和操作类型封装入参数集合
		paramsMap.put("tableCode", table.getTableCode());
		paramsMap.put("id", table.getId());
		paramsMap.put("op", op);
		return tableDao.getTableNumber(paramsMap);
	}

	@Override
	public List<DiningTable> getAllTables(Long tableId) {
		// TODO Auto-generated method stub
		logger.info("获取所有餐桌列表...");
		//定义参数集合
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		//将餐桌编号封装入参数集合
		paramsMap.put("tableId", tableId);
		return tableDao.getAllTables(paramsMap);
	}
	
	@Override
	public int insert(DiningTable table) {
		// TODO Auto-generated method stub
		logger.info("插入一条餐桌记录...");
		return tableDao.insert(table);
	}

	@Override
	public int updateByPrimaryKeySelective(DiningTable table) {
		// TODO Auto-generated method stub
		logger.info("根据主键有选择的更新餐桌信息...");
		return tableDao.updateByPrimaryKeySelective(table);
	}

	@Override
	public int deleteByPrimiaryKey(Long id) {
		// TODO Auto-generated method stub
		logger.info("根据主键删除餐桌信息...");
		return tableDao.deleteByPrimiaryKey(id);
	}
	
}
