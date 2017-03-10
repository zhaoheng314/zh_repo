package cn.edu.zzti.biz.entity;

import java.util.Date;

/**
 * 菜品类别 实体类
 * @author sunwj
 *
 */
public class DishCategory {

	//属性
	/**
	 * 主键
	 */
	private Long id;
	
	/**
	 * 类别名称
	 */
	private String categoryName;
	
	/**
	 * 创建时间
	 */
	private Date createDate;
	
	/**
	 * 修改时间
	 */
	private Date updateDate;
	
	/**
	 * 菜品类别状态
	 */
	private int categoryStatus;

	//setters and getters 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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

	public int getCategoryStatus() {
		return categoryStatus;
	}

	public void setCategoryStatus(int categoryStatus) {
		this.categoryStatus = categoryStatus;
	}

}
