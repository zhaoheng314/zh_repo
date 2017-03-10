package cn.edu.zzti.biz.dto;
/**
 * 菜品类 值传递对象
 * @author sunwj
 *
 */
public class DishDto {
	
	//属性
	/**
	 * 主键，菜品编号
	 */
	private Long id;
	
	/**
	 * 菜品价格
	 */
	private int price;
	
	/**
	 * 菜品名称
	 */
	private String dishName;
	
	/**
	 * 菜品的选择数量
	 */
	private int number;
	
	/**
	 * 单个菜品的总金额(单价乘以数量)
	 */
	private int money;

	//setters and getters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
}
