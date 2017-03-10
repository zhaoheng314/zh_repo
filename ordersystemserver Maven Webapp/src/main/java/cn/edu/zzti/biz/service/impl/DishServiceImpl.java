package cn.edu.zzti.biz.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zzti.biz.dao.IDishDao;
import cn.edu.zzti.biz.entity.Dish;
import cn.edu.zzti.biz.service.IDishService;
import cn.edu.zzti.common.util.CommonUtil;
import cn.edu.zzti.common.util.JSONUtil;
import cn.edu.zzti.common.util.PageUtil;

/**
 * 菜品 业务层实现
 * @author sunwj
 *
 */
@Service("dishService")
public class DishServiceImpl implements IDishService{

	private static Logger logger = Logger.getLogger(DishServiceImpl.class);
	
	@Autowired
	private IDishDao dishDao;

	@Override
	public Dish selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		logger.info("根据主键获取菜品对象...");
		return dishDao.selectByPrimaryKey(id);
	}

	@Override
	public String getDishList(String page, String pagesize, Long categoryId) {
		// TODO Auto-generated method stub
		logger.info("获取菜品列表，分页...");
		//定义参数集合
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		//获取分页参数
		PageUtil pageUtil = CommonUtil.getPage(page, pagesize);
		//将分页参数封装入参数集合
		paramsMap.put("page", pageUtil);
		//将菜品类别编号封装入参数集合
		paramsMap.put("categoryId", categoryId);
		//定义菜品列表
		List<Dish> dishList = null;
		//获取菜品总数
		int count = getCount(paramsMap);
		if(count <= 0){
			dishList = new ArrayList<Dish>();
		}else{
			//获取分页内容
			dishList = dishDao.getDishList(paramsMap);
			 
			if(null == dishList || dishList.isEmpty()){
				dishList = new ArrayList<Dish>();
			}
		}
		return JSONUtil.getListPageGridJson(dishList, count, Integer.parseInt(pagesize));
	}

	@Override
	public int getCount(Map<String, Object> paramsMap) {
		// TODO Auto-generated method stub
		logger.info("获取菜品总数 ...");
		return dishDao.getCount(paramsMap);
	}

	@Override
	public int getDishNumber(Dish dish, String op) {
		// TODO Auto-generated method stub
		logger.info("通过菜品名称获取菜品数量...");
		//定义参数集合
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		//将菜品名称、操作类型和菜品编号封装入参数集合
		paramsMap.put("dishName", dish.getDishName());
		paramsMap.put("id", dish.getId());
		paramsMap.put("op", op);
		return dishDao.getDishNumber(paramsMap);
	}

	@Override
	public List<Dish> getAllDishes() {
		// TODO Auto-generated method stub
		logger.info("获得所有的菜品列表...");
		return dishDao.getAllDishes();
	}

	
	@Override
	public List<Dish> getCheckedDishes(String dishIds) {
		// TODO Auto-generated method stub
		logger.info("获得选择的菜品列表...");
		//获得选择的菜品的编号数组
		String [] dishIdArr = dishIds.split(",");
		return dishDao.getCheckedDishes(dishIdArr);
	}
	
	@Override
	public List<Dish> getUnCheckedDishes(String dishIds) {
		// TODO Auto-generated method stub
		logger.info("获得未选择的菜品列表...");
		//获得选择的菜品的编号数组
		String [] dishIdArr = dishIds.split(",");
		return dishDao.getUnCheckedDishes(dishIdArr);
	}
	
	@Override
	public int insert(Dish dish) {
		// TODO Auto-generated method stub
		logger.info("插入一条菜品记录...");
		return dishDao.insert(dish);
	}

	@Override
	public int updateByPrimaryKeySelective(Dish dish) {
		logger.info("有选择的更新菜品信息...");
		// TODO Auto-generated method stub
		return dishDao.updateByPrimaryKeySelective(dish);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		logger.info("根据主键删除菜品信息...");
		// TODO Auto-generated method stub
		return dishDao.deleteByPrimaryKey(id);
	}

}
