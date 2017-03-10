package cn.edu.zzti.sys.entity;

import java.util.Date;

/**
 * 角色权限 实体类
 * @author sunwj
 *
 */
public class SysRolePrivilege {

	//属性
	/**
	 * 主键
	 */
	private Long id;
	
	/**
	 * 角色编号
	 */
	private Long roleId;
	
	/**
	 * 权限编号
	 */
	private Long privilegeId;
	
	/**
	 * 创建时间
	 */
	private Date createDate;
	
	//setters and getters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getPrivilegeId() {
		return privilegeId;
	}

	public void setPrivilegeId(Long privilegeId) {
		this.privilegeId = privilegeId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	 
}
