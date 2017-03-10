package cn.edu.zzti.sys.entity;
/**
 * 系统登录用户 实体类
 * @author sunwj
 *
 */
public class LoginUser {

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
	 * 联系地址
	 */
	private String address;
	
	/**
	 * 用户状态
	 */
	private int userStatus;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}

}
