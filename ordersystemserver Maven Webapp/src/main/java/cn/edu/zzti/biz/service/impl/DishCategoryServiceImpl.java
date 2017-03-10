package cn.edu.zzti.biz.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zzti.biz.dao.IDishCategoryDao;
import cn.edu.zzti.biz.entity.DishCategory;
import cn.edu.zzti.biz.service.IDishCategoryService;
import cn.edu.zzti.common.util.CommonUtil;
import cn.edu.zzti.common.util.JSONUtil;
import cn.edu.zzti.common.util.PageUtil;

/**
 * 菜品分类 业务层实现
 * @author sunwj
 *
 */
@Service("categoryService")
public class DishCategoryServiceImpl implements IDishCategoryService {

	private Logger logger = Logger.getLogger(DishCategoryServiceImpl.class);
	
	@Autowired
	private IDishCategoryDao categoryDao;

	@Override
	public DishCategory selectByPrimiaryKey(Long id) {
		// TODO Auto-generated method stub
		logger.info("根据主键获取菜品类别对象");
		return categoryDao.selectByPrimiaryKey(id);
	}

	@Override
	public String getCategoryList(String page, String pagesize) {
		// TODO Auto-generated method stub
		logger.info("获取菜品类别列表，分页...");
		//定义参数集合
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		//获取分页参数
		PageUtil pageUtil = CommonUtil.getPage(page, pagesize);
		//将分页参数封装入参数集合
		paramsMap.put("page", pageUtil);
		//定义菜品类别对象
		List<DishCategory> categoryList = null;
		//获取菜品类别数量
		int count = getCount();
		if(count <= 0){
			categoryList = new ArrayList<DishCategory>();
		}else{
			//获取分页内容
			categoryList = categoryDao.getCategoryList(paramsMap);
			
			if(null == categoryList || categoryList.isEmpty()){
				categoryList = new ArrayList<DishCategory>();
			}
		}
		return JSONUtil.getListPageGridJson(categoryList, count, Integer.parseInt(pagesize));
	}

	@Override
	public List<DishCategory> getAllCategory(Long categoryId) {
		// TODO Auto-generated method stub
		logger.info("获取所有菜品类别信息...");
		//定义参数集合
		Map<String, Object> paramsMap = new HashMap<String, Object>();
	    //将菜品类别编号封装入参数集合
		paramsMap.put("categoryId", categoryId);
		return categoryDao.getAllCategory(paramsMap);
	}
	
	@Override
	public String getCategoryTree() {
		// TODO Auto-generated method stub
		logger.info("构造菜品类别树...");
		//获得所有菜品类别列表
		List<DishCategory> categoryList = getAllCategory(null);
		//定义树子节点集合
		List<Map<String, Object>> childList = new ArrayList<Map<String,Object>>();
		//遍历所有菜品类别列表
		for(DishCategory category:categoryList){
			Map<String, Object> treeMap = new HashMap<String, Object>();
			treeMap.put("id", category.getId());
			treeMap.put("pid", 0);
			treeMap.put("text", category.getCategoryName());
			childList.add(treeMap);
		}
		//定义菜品类别树集合
		List<Map<String, Object>> treeList = new ArrayList<Map<String,Object>>();
		//构造树的根节点
		Map<String, Object> rootMap = new HashMap<String, Object>();
		rootMap.put("id", 0);
		rootMap.put("pid", -1);
		rootMap.put("text", "菜品类别列表");
		rootMap.put("nodes", childList);
		treeList.add(rootMap);
		return JSONUtil.getJson(treeList);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		logger.info("获取菜品类别总数...");
		return categoryDao.getCount();
	}
	
	@Override
	public int getCategoryNumber(DishCategory category, String op) {
		// TODO Auto-generated method stub
		logger.info("获取菜品类别数量...");
		//定义参数集合
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		//将菜品类别名称、操作类型、菜品类别编号封装入参数集合
		paramsMap.put("categoryName", category.getCategoryName());
		paramsMap.put("op", op);
		paramsMap.put("id", category.getId());
		return categoryDao.getCategoryNumber(paramsMap);
	}

	@Override
	public int insert(DishCategory category) {
		// TODO Auto-generated method stub
		logger.info("插入一条菜品类别记录...");
		return categoryDao.insert(category);
	}

	@Override
	public int updateByPrimaryKeySelective(DishCategory category) {
		// TODO Auto-generated method stub
		logger.info("根据主键有选择的更新菜品类别信息...");
		return categoryDao.updateByPrimaryKeySelective(category);
	}

	@Override
	public int deleteByPrimiaryKey(Long id) {
		// TODO Auto-generated method stub\
		logger.info("根据主键删除菜品类别信息...");
		return categoryDao.deleteByPrimiaryKey(id);
	}

}
