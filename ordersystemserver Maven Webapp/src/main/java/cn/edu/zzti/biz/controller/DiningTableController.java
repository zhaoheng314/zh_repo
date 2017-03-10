package cn.edu.zzti.biz.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.zzti.biz.entity.DiningTable;
import cn.edu.zzti.biz.service.IDiningTableService;
import cn.edu.zzti.common.util.CommonUtil;
import cn.edu.zzti.common.util.Constants;
import cn.edu.zzti.common.util.DateUtil;
import cn.edu.zzti.sys.base.BaseController;

/**
 * 餐桌 控制层
 * @author sunwj
 *
 */
@Controller
@Scope(value="prototype")
@RequestMapping(value="/table")
public class DiningTableController extends BaseController{

	private static Logger logger = Logger.getLogger(DiningTableController.class);
	
	@Autowired
	private IDiningTableService tableService;
	
	/**
	 * 跳转至餐桌列表页面...
	 * @return table_list.jsp
	 */
	@RequestMapping("/tableList")
	public ModelAndView tableList(){
		logger.info("跳转餐桌列表页面...");
		ModelAndView view = new ModelAndView();
		view.setViewName("biz/table/table_list");
		return view;
	}
	
	/**
	 * 获取餐桌列表页面，分页
	 * @param page 当前页
	 * @param rows 页的大小
	 */
	@RequestMapping("/getTableList")
	public void getTableList(String page, String rows){
		logger.info("获取餐桌列表...");
		String data = tableService.getDiningTableList(page, rows);
		//输出返回结果至页面
		out(data);
	}
	
	/**
	 * 跳转至添加餐桌页面
	 * @return table_edit.jsp
	 */
	@RequestMapping("/addTable")
	public ModelAndView addTable(){
		logger.info("跳转至添加餐桌页面...");
		ModelAndView view = new ModelAndView();
		//创建餐桌对象
		DiningTable table = new DiningTable();
		view.addObject("table", table);
		view.addObject(Constants.OP, Constants.OP_ADD);
		view.setViewName("biz/table/table_edit");
		return view;
	}
	
	/**
	 * 跳转至修改餐桌页面
	 * @param id 主键，餐桌编号
	 * @return
	 */
	@RequestMapping("/modifyTable")
	public ModelAndView modifyTable(Long id){
		logger.info("跳转至修改餐桌页面...");
		ModelAndView view = new ModelAndView();
		DiningTable table = tableService.selectByPrimiaryKey(id);
		view.addObject("table", table);
		view.addObject(Constants.OP, Constants.OP_MODIFY);
		view.setViewName("biz/table/table_edit");
		return view;
	}
	
	/**
	 * 保存餐桌信息
	 * @param table 餐桌对象
	 * @param op    操作类型
	 */
	@RequestMapping("/saveTable")
	public void saveTable(DiningTable table, String op){
		logger.info("保存餐桌信息...");
		//定义结果集合
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//判断餐桌代码是否存在
		int count = tableService.getTableNumber(table, op);
		//count等于0说明餐桌代码不存在，可以添加或者修改
		if(count == 0){    
			if(Constants.OP_MODIFY.equals(op)){   //修改餐桌信息
				//设置餐桌修改时间
				table.setUpdateDate(DateUtil.getSystemTime());
			    //执行修改操作
				int result = tableService.updateByPrimaryKeySelective(table);
				//判断是否修改成功
				resultMap = CommonUtil.checkSave(result);
			}else{                                //添加餐桌信息
				//设置餐桌创建时间、修改时间
				table.setCreateDate(DateUtil.getSystemTime());
				table.setUpdateDate(DateUtil.getSystemTime());
				//执行添加操作
				int result = tableService.insert(table);
				//判断是否添加成功
				resultMap = CommonUtil.checkSave(result);
			}
		}else{ 
			resultMap.put("msg", "此餐桌代码已存在，请更换餐桌代码!");
		}
		//输出返回结果至页面
		outJson(resultMap);
	}
	
	/**
	 * 根据主键删除餐桌信息
	 * @param id 主键，餐桌编号
	 */
	@RequestMapping("/deleteTable")
	public void deleteTable(Long id){
		logger.info("根据主键删除餐桌信息...");
		//定义结果集合
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//执行删除操作
		int result = tableService.deleteByPrimiaryKey(id);
		//判断是否删除成功
		resultMap = CommonUtil.checkDel(result);
		//输出返回结果至页面
	    outJson(resultMap);
	}
}
