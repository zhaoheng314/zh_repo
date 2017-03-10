package cn.edu.zzti.biz.entity;

import java.util.Date;

/**
 * 餐桌 实体类
 * @author sunwj
 *
 */
public class DiningTable {

	//属性
	/**
	 * 主键
	 */
	private Long id;
	
	/**
	 * 餐桌代码
	 */
	private String tableCode;
	
	/**
	 * 餐桌大小 
	 */
	private int tableSize;
	
	/**
	 * 创建时间
	 */
	private Date createDate;
	
	/**
	 * 更新时间
	 */
	private Date updateDate;
	
	/**
	 * 餐桌状态  -1无法使用 0空闲 1正在使用
	 */
	private int tableStatus;

	// setters and getters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTableCode() {
		return tableCode;
	}

	public void setTableCode(String tableCode) {
		this.tableCode = tableCode;
	}

	public int getTableSize() {
		return tableSize;
	}

	public void setTableSize(int tableSize) {
		this.tableSize = tableSize;
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

	public int getTableStatus() {
		return tableStatus;
	}

	public void setTableStatus(int tableStatus) {
		this.tableStatus = tableStatus;
	}
	
}
