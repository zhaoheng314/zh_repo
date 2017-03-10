package cn.edu.zzti.sys.entity;

import java.util.Date;

/**
 * 系统角色 实体类
 * 
 * @author sunwj
 * 
 */
public class SysRole {

	// 属性
	/**
	 * 角色编号
	 */
	private Long id;

	/**
	 * 角色代码
	 */
	private String roleCode;

	/**
	 * 角色名称
	 */
	private String roleName;

	/**
	 * 角色描述
	 */
	private String roleDesc;

	/**
	 * 是否系统角色 0否 1是
	 */
	private int ifSys;

	/**
	 * 创建时间
	 */
	private Date createDate;

	/**
	 * 修改时间
	 */
	private Date updateDate;

	/**
	 * 角色状态
	 */
	private int roleStatus;

	//setters and getters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public int getIfSys() {
		return ifSys;
	}

	public void setIfSys(int ifSys) {
		this.ifSys = ifSys;
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

	public int getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(int roleStatus) {
		this.roleStatus = roleStatus;
	}
    
}
