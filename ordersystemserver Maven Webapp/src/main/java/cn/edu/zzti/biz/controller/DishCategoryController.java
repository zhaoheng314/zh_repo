package cn.edu.zzti.biz.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.zzti.biz.entity.DishCategory;
import cn.edu.zzti.biz.service.IDishCategoryService;
import cn.edu.zzti.common.util.CommonUtil;
import cn.edu.zzti.common.util.Constants;
import cn.edu.zzti.common.util.DateUtil;
import cn.edu.zzti.sys.base.BaseController;

/**
 * 菜品类别 控制层
 * @author sunwj
 *
 */
@Controller
@Scope(value="prototype")
@RequestMapping(value="/category")
public class DishCategoryController extends BaseController {

	private static Logger logger = Logger.getLogger(DishCategoryController.class);
	
	@Autowired
	private IDishCategoryService categoryService;
	
	/**
	 * 跳转至菜品类别列表
	 * @return category_list.jsp
	 */
	@RequestMapping("/categoryList")
	public ModelAndView categoryList(){
		logger.info("跳转至菜品类别列表页面...");
		ModelAndView view = new ModelAndView();
		view.setViewName("biz/dish/category_list");
		return view;
	}
	
	/**
	 * 获取菜品分类列表，分页
	 * @param page 当前页
	 * @param rows 页的大小
	 */
	@RequestMapping("/getCategoryList")
	public void getCategoryList(String page, String rows){
		logger.info("获取菜品分类列表...");
		String data = categoryService.getCategoryList(page, rows);
		//输出返回结果至页面
		out(data);
	}
	
	/**
	 * 跳转至添加菜品类别页面
	 * @return category_edit.jsp
	 */
	@RequestMapping("/addCategory")
	public ModelAndView addCategory(){
		logger.info("跳转至添加菜品类别页面...");
		ModelAndView view = new ModelAndView();
		//定义菜品类别对象
		DishCategory category = new DishCategory();
		view.addObject("category", category);
		view.addObject(Constants.OP, Constants.OP_ADD);
		view.setViewName("biz/dish/category_edit");
		return view;
	}
	
	/**
	 * 跳转至修改菜品类别页面
	 * @param id 主键
	 * @return category_edit.jsp
	 */
	@RequestMapping("/modifyCategory")
	public ModelAndView modifyCategory(Long id){
		logger.info("跳转至修改菜品类别页面...");
		ModelAndView view = new ModelAndView();
		DishCategory category = categoryService.selectByPrimiaryKey(id);
		view.addObject(Constants.OP, Constants.OP_MODIFY);
		view.addObject("category", category);
		view.setViewName("biz/dish/category_edit");
		return view;
	}
	
	/**
	 * 保存菜品类别信息
	 * @param category 菜品类别对象
	 * @param op       操作类型
	 */
	@RequestMapping("/saveCategory")
	public void saveCategory(DishCategory category, String op){
		logger.info("保存菜品类别信息...");
		//定义结果集合
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//先判断菜品类别名称是否存在
		int count = categoryService.getCategoryNumber(category, op);
		//count等于0说明菜品类别名称不存在，可以添加或者修改
		if(count == 0){
			if(Constants.OP_MODIFY.equals(op)){   //修改菜品类别信息
				//设置菜品类别修改时间
				category.setUpdateDate(DateUtil.getSystemTime());
				//执行修改操作
				int result = categoryService.updateByPrimaryKeySelective(category);
				//判断修改是否成功
				resultMap = CommonUtil.checkSave(result);
			}else{                                //添加菜品类别信息
				//设置菜品类别创建时间
				category.setCreateDate(DateUtil.getSystemTime());
				//设置菜品类别修改时间
				category.setUpdateDate(DateUtil.getSystemTime());
				//设置菜品类别状态，默认状态为1
				category.setCategoryStatus(1);
				//执行添加操作
				int result = categoryService.insert(category);
				//判断是否添加成功
				resultMap = CommonUtil.checkSave(result);
			}
		}else{
			resultMap.put("msg", "此菜品类别名称已存在，请更换菜品类别名称!");
		}
		//输出返回结果至页面
		outJson(resultMap);
	}
	
	/**
	 * 根据主键删除菜品类别信息
	 * @param id 主键，菜品类别编号
	 */
	@RequestMapping("/deleteCategory")
	public void deleteCategory(Long id){
		logger.info("根据主键删除菜品类别信息...");
		//定义结果集合
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//执行删除操作
		int result = categoryService.deleteByPrimiaryKey(id);
		//判断是否删除成功
		resultMap = CommonUtil.checkDel(result);
		//输出返回结果至页面
		outJson(resultMap);
	}
}
