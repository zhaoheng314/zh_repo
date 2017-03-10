package cn.edu.zzti.biz.entity;

import java.util.Date;

/**
 * 菜品 实体类
 * @author sunwj
 *
 */
public class Dish {
	
	//属性
    /**
     * 主键
     */
	private Long id;
	
	/**
	 * 菜品类别编号
	 */
	private Long categoryId;
	
	/**
	 * 菜品名称
	 */
	private String dishName;
	
	/**
	 * 价格
	 */
	private int price;
	
	/**
	 * 菜品描述
	 */
	private String dishDesc;
	
	/**
	 * 菜品图片路径
	 */
	private String imageUrl;
	
	/**
	 * 创建时间
	 */
	private Date createDate;
	
	/**
	 * 修改时间
	 */
	private Date updateDate;
	
	/**
	 * 菜品状态
	 */
	private int dishStatus;

	//setters and getters 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getDishDesc() {
		return dishDesc;
	}

	public void setDishDesc(String dishDesc) {
		this.dishDesc = dishDesc;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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

	public int getDishStatus() {
		return dishStatus;
	}

	public void setDishStatus(int dishStatus) {
		this.dishStatus = dishStatus;
	}

}
