package cn.edu.zzti.biz.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.zzti.biz.entity.Dish;
import cn.edu.zzti.biz.entity.DishCategory;
import cn.edu.zzti.biz.service.IDishCategoryService;
import cn.edu.zzti.biz.service.IDishService;
import cn.edu.zzti.common.util.CommonUtil;
import cn.edu.zzti.common.util.Constants;
import cn.edu.zzti.common.util.DateUtil;
import cn.edu.zzti.sys.base.BaseController;

/**
 * 菜品 控制层
 * @author sunwj
 *
 */
@Controller
@Scope(value="prototype")
@RequestMapping(value="/dish")
public class DishController extends BaseController {

	private static Logger logger = Logger.getLogger(DishController.class);
	
	@Autowired
	private IDishService dishService;
	
	@Autowired
	private IDishCategoryService categoryService;
	
	/**
	 * 跳转至菜品列表页面
	 * @return
	 */
	@RequestMapping("/dishList")
	public ModelAndView dishList(){
		logger.info("跳转至菜品列表页面...");
		ModelAndView view = new ModelAndView();
		view.setViewName("biz/dish/dish_list");
		return view;
	}
	
	/**
	 * 获取菜品列表，分页
	 * @param page       当前页
	 * @param rows       页的大小
	 * @param categoryId 菜品类别编号
	 */
	@RequestMapping("/getDishList")
	public void getDishList(String page, String rows, Long categoryId){
		logger.info("获取菜品列表，分页...");
		String data = dishService.getDishList(page, rows, categoryId);
		//输出返回结果至页面
		out(data);
	}
	
	/**
	 * 获得菜品类别树
	 */
	@RequestMapping("/getCategoryTree")
	public void getCategoryTree(){
		logger.info("获得菜品类别树...");
		String data = categoryService.getCategoryTree();
		//输出返回结果至页面
		out(data);
	}
	
	/**
	 * 跳转至添加菜品页面
	 * @return
	 */
	@RequestMapping("/addDish")
	public ModelAndView addDish(){
		logger.info("跳转至添加菜品页面...");
		ModelAndView view = new ModelAndView();
		//创建菜品对象
		Dish dish = new Dish();
		//获取菜品类别列表
		List<DishCategory> categoryList = categoryService.getAllCategory(null);
		view.addObject("dish", dish);
		view.addObject("cateList", categoryList);
		view.addObject("op", Constants.OP_ADD);
		view.setViewName("biz/dish/dish_edit");
		return view;
	}
	
	/**
	 * 跳转至修改菜品页面
	 * @param id 主键，菜品编号
	 * @return
	 */
	@RequestMapping("/modifyDish")
	public ModelAndView modifyDish(Long id){
		logger.info("跳转至修改菜品页面...");
		ModelAndView view = new ModelAndView();
		System.out.println("id= "+id);
		//根据主键获取菜品对象
		Dish dish = dishService.selectByPrimaryKey(id);
		//获取菜品类别对象
		DishCategory category = categoryService.selectByPrimiaryKey(dish.getCategoryId());
		//获取菜品类别列表
		List<DishCategory> categoryList = categoryService.getAllCategory(dish.getCategoryId());
		view.addObject("dish", dish);
		view.addObject("catgegory", category);
		view.addObject("cateList", categoryList);
		view.addObject("op", Constants.OP_MODIFY);
		view.setViewName("biz/dish/dish_edit");
		return view;
	}
	
	/**
	 * 保存菜品信息
	 * @param dish 菜品对象
	 * @param op   操作类型
	 */
	@RequestMapping("/saveDish")
	public void saveDish(Dish dish, String op){
		logger.info("保存菜品信息...");
		//定义结果集合
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//先判断菜品名称是否存在
		int count = dishService.getDishNumber(dish, op);
		//count等于0说明菜品名称不存在，可以添加或者修改
		if(count == 0){
			if(Constants.OP_MODIFY.equals(op)){   //修改菜品信息
				//设置菜品修改时间
				dish.setUpdateDate(DateUtil.getSystemTime());
				//执行修改操作
				int result = dishService.updateByPrimaryKeySelective(dish);
				//判断修改是否成功
				resultMap = CommonUtil.checkSave(result);
			}else{                                //添加菜品信息
				//设置菜品创建时间，修改时间和菜品状态
				dish.setCreateDate(DateUtil.getSystemTime());
				dish.setUpdateDate(DateUtil.getSystemTime());
				dish.setDishStatus(1);
				//执行添加操作
				int result = dishService.insert(dish);
				//判断是否添加成功
				resultMap = CommonUtil.checkSave(result);
			}
		}else{
			resultMap.put("msg", "此菜品名称已存在，请更换菜品名称!");
		}
		//输出返回结果至页面
		outJson(resultMap);
	}
	
	/**
	 * 根据主键删除菜品信息
	 * @param id 主键，菜品编号
	 */
	@RequestMapping("/deleteDish")
	public void deleteMember(Long id){
		logger.info("根据主键删除会员信息...");
		//定义结果集合
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//执行删除操作
		int result = dishService.deleteByPrimaryKey(id);
		//判断是否删除成功
		resultMap = CommonUtil.checkDel(result);
		//输出返回结果至页面
		outJson(resultMap);
	}
}
