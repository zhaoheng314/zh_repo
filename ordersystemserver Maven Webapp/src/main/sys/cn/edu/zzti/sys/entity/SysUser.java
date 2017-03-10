package cn.edu.zzti.sys.entity;

import java.util.Date;
/**
 * 实体类 系统用户
 * @author sunwj
 *
 */
public class SysUser {
	// 属性
	/**
	 * 用户编号
	 */
	private Long id;

	/**
	 * 用户名称
	 */
	private String username;

	/**
	 * 用户密码
	 */
	private String password;

	/**
	 * 用户真实姓名
	 */
	private String realname;

	/**
	 * 性别 0代表男，1代表女
	 */
	private int gender;

	/**
	 * 联系方式
	 */
	private String phone;

	/**
	 * 邮箱
	 */
	private String email;
	
	/**
	 * 联系地址
	 */
	private String address;

	/**
	 * 创建日期
	 */
	private Date createDate;
	
	/**
	 * 更新日期
	 */
	private Date updateDate;
	
	/**
	 * 用户状态
	 */
	private int userStatus;
	
	/**
	 * 备注
	 */
	private String remark;
	
	//setters and getters 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public int getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
