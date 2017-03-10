package cn.edu.zzti.biz.entity;

import java.util.Date;

/**
 * 账单 实体类
 * @author sunwj
 *
 */
public class Bill {

	//属性
	/**
	 * 主键，账单编号
	 */
	private Long id;
	
	/**
	 * 餐桌编号
	 */
	private Long tableId;
	
	/**
	 * 操作员编号
	 */
	private Long userId;
	
	/**
	 * 菜品编号字符串
	 */
	private String dishIds;
	
	/**
	 * 菜品数量字符串
	 */
	private String dishNums;
	
	/**
	 * 是否会员
	 */
	private int ifMember;
	
	/**
	 * 总金额
	 */
	private int totalMoney;
	
	/**
	 * 创建时间
	 */
	private Date createDate;
	
	/**
	 * 更新时间
	 */
	private Date updateDate;
	
	/**
	 * 订单状态
	 */
	private int billStatus;

	//setters and getters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTableId() {
		return tableId;
	}

	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getDishIds() {
		return dishIds;
	}

	public void setDishIds(String dishIds) {
		this.dishIds = dishIds;
	}

	public String getDishNums() {
		return dishNums;
	}

	public void setDishNums(String dishNums) {
		this.dishNums = dishNums;
	}

	public int getIfMember() {
		return ifMember;
	}

	public void setIfMember(int ifMember) {
		this.ifMember = ifMember;
	}

	public int getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(int totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public int getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(int billStatus) {
		this.billStatus = billStatus;
	}
	
}
