package cn.edu.zzti.biz.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.zzti.biz.dto.DishDto;
import cn.edu.zzti.biz.entity.Bill;
import cn.edu.zzti.biz.entity.DiningTable;
import cn.edu.zzti.biz.entity.Dish;
import cn.edu.zzti.biz.service.IBillService;
import cn.edu.zzti.biz.service.IDiningTableService;
import cn.edu.zzti.biz.service.IDishService;
import cn.edu.zzti.common.util.CommonUtil;
import cn.edu.zzti.common.util.Constants;
import cn.edu.zzti.common.util.DateUtil;
import cn.edu.zzti.sys.base.BaseController;
import cn.edu.zzti.sys.entity.LoginUser;

/**
 * 账单 控制层
 * @author sunwj
 *
 */
@Controller
@Scope(value="prototype")
@RequestMapping("/bill")
public class BillController extends BaseController{

	private static Logger logger = Logger.getLogger(BillController.class);
	
	@Autowired
	private IBillService billService;
	
	@Autowired
	private IDiningTableService tableService;
	
	@Autowired
	private IDishService dishService;
	
	/**
	 * 跳转至账单列表页面
	 * @return
	 */
	@RequestMapping("/billList")
	public ModelAndView billList(){
		logger.info("跳转至账单列表页面...");
		ModelAndView view = new ModelAndView();
		view.setViewName("biz/bill/bill_list");
		return view;
	}
	
	/**
	 * 获取账单列表，分页
	 * @param page  当前页
	 * @param rows  页的大小
	 */
	@RequestMapping("/getBillList")
	public void getBillList(String page, String rows){
		logger.info("获取账单列表，分页...");
		String data = billService.getBillList(page, rows);
		//输出返回结果至页面
		out(data);
	}
	
	/**
	 * 跳转至添加账单页面
	 * @return
	 */
	@RequestMapping("/addBill")
	public ModelAndView addBill(){
		logger.info("跳转至添加账单页面...");
		ModelAndView view = new ModelAndView();
		//创建账单对象
		Bill bill = new Bill();
		//获得所有餐桌列表
		List<DiningTable> tableList = tableService.getAllTables(null);
		//获得所有菜品列表
		List<Dish> dishList = dishService.getAllDishes();
		view.addObject("bill", bill);
		view.addObject("tableList", tableList);
		view.addObject("dishList", dishList);
		view.addObject("op", Constants.OP_ADD);
		view.setViewName("biz/bill/bill_edit");
		return view;
	}
	
	@RequestMapping("/modifyBill")
	public ModelAndView modifyBill(Long id){
		logger.info("跳转至修改账单页面...");
		ModelAndView view = new ModelAndView();
		//根据主键获得账单对象
		Bill  bill = billService.selectByPrimaryKey(id);
		//获得餐桌对象
		DiningTable table = tableService.selectByPrimiaryKey(bill.getTableId());
		//获得餐桌列表
		List<DiningTable> tableList = tableService.getAllTables(bill.getTableId());
		//获得选择的菜品数量数组
		String [] dishNumArr = bill.getDishNums().split(",");
		//获得选择的菜品列表
		List<Dish> cList = dishService.getCheckedDishes(bill.getDishIds());
		//定义菜品值传递对象集合
		List<DishDto> checkedList = new ArrayList<DishDto>();
		//遍历选择的菜品列表  
		for(int i  = 0; i < cList.size(); i++){
			Dish dish = cList.get(i);
			DishDto dishDto = new DishDto();
			dishDto.setId(dish.getId());
			dishDto.setDishName(dish.getDishName());
			dishDto.setPrice(dish.getPrice());
			dishDto.setNumber(Integer.parseInt(dishNumArr[i]));
			checkedList.add(dishDto);
		}
		//获得未选择的菜品列表
		List<Dish> uncheckedList = dishService.getUnCheckedDishes(bill.getDishIds());
		view.addObject("bill", bill);
		view.addObject("table", table);
		view.addObject("tableList", tableList);
		view.addObject("checkedList", checkedList);
		view.addObject("uncheckedList", uncheckedList);
		view.addObject("op", Constants.OP_MODIFY);
		view.setViewName("biz/bill/bill_edit");
		return view;
	}
	
	/**
	 * 保存账单信息
	 * @param bill 账单对象
	 * @param op   操作类型 op
	 */
	@RequestMapping("/saveBill")
	public void saveBill(Bill bill, String op){
		logger.info("保存账单信息...");
		//定义结果集合
	    Map<String, Object> resultMap = new HashMap<String, Object>();
	    //获得当前登录用户编号
	    LoginUser loginUser = getLoginUser();
	    //设置账单操作员编号
	    bill.setUserId(loginUser.getId());
	    if(Constants.OP_MODIFY.equals(op)){
	    	//设置账单修改时间
	    	bill.setUpdateDate(DateUtil.getSystemTime());
	    	//执行修改操作
	    	int result = billService.updateByPrimaryKeySelective(bill);
	    	//判断修改是否成功
	    	resultMap = CommonUtil.checkSave(result);
	    }else{
	    	//设置账单创建时间和修改时间
	    	bill.setCreateDate(DateUtil.getSystemTime());
	    	bill.setUpdateDate(DateUtil.getSystemTime());
	    	//设置账单状态，初始状态为0表示未结算
	    	bill.setBillStatus(0);
	    	//执行添加操作
	    	int result =  billService.insert(bill);
	    	//判断修改是否成功
	    	resultMap = CommonUtil.checkSave(result);
	    }
	    //输出返回结果至页面
	    outJson(resultMap);
	}
	
	/**
	 * 跳转至结算界面
	 * @param id 主键，账单编号
	 * @return
	 */
	@RequestMapping("/balance")
	public ModelAndView balance(Long id){
		logger.info("执行结算...");
		ModelAndView view = new ModelAndView();
		//根据主键获得账单对象
		Bill bill = billService.selectByPrimaryKey(id);
	    //获得菜品编号串
		String dishIds = bill.getDishIds();
		//获得选择的菜品数量数组
	    String [] dishNumArr = bill.getDishNums().split(",");
		//获得选择的菜品列表
		List<Dish> checkedList = dishService.getCheckedDishes(dishIds);
		//定义菜品值传递对象集合
		List<DishDto> dishList =  new ArrayList<DishDto>();
		//定义总金额
		int totalMoney = 0;
		//遍历选择的菜品列表
		for(int i  = 0; i < checkedList.size(); i++){
			Dish dish = checkedList.get(i);
			DishDto dishDto = new DishDto();
			dishDto.setId(dish.getId());
			dishDto.setDishName(dish.getDishName());
			dishDto.setPrice(dish.getPrice());
			dishDto.setNumber(Integer.parseInt(dishNumArr[i]));
			//计算单个菜品的总金额(单价乘以数量)
			int money = dishDto.getPrice() * dishDto.getNumber();
			//总金额等于单个菜品的总金额累计相加
			totalMoney += money;
			dishDto.setMoney(money);
			dishList.add(dishDto);
		}
		bill.setTotalMoney(totalMoney);
		view.addObject("bill", bill);
		view.addObject("dishList", dishList);
		view.setViewName("biz/bill/bill_detail");
		return view;
	}
	
	/**
	 * 账单付款
	 * @param id         主键，账单编号
	 * @param ifMember   是否会员
	 * @param totalMoney 总金额
	 */
	@RequestMapping("/payment")
	public void payment(Long id, int ifMember, int totalMoney){
		logger.info("账单付款...");
		//定义结果集合
		 Map<String, Object> resultMap = new HashMap<String, Object>();
	    //获得账单对象
		Bill bill = billService.selectByPrimaryKey(id);
		//修改账单信息
		bill.setIfMember(ifMember);           
		bill.setTotalMoney(totalMoney);
		bill.setBillStatus(1);               //修改账单状态为1，表示已结算
		//执行修改操作
		int result = billService.updateByPrimaryKeySelective(bill);
		//判断是否修改成功
		resultMap = CommonUtil.checkSave(result);
		//返回输出结果至页面
		outJson(resultMap);
	}
	
	/**
	 * 根据主键删除账单信息
	 * @param id 主键，账单编号
	 */
	@RequestMapping("/deleteBill")
	public void deleteBill(Long id){
		logger.info("根据主键删除账单信息...");
		//定义结果集合
	    Map<String, Object> resultMap = new HashMap<String, Object>();
		//执行删除操作
		int result = billService.deleteByPrimaryKey(id);
		//判断删除是否成功
		resultMap = CommonUtil.checkDel(result);
		//输出返回结果至页面
		outJson(resultMap);
	}
}
