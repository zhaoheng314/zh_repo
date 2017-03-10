package cn.edu.zzti.sys.entity;

import java.util.Date;

/**
 * 系统权限 实体类
 * @author sunwj
 *
 */
public class SysPrivilege {

	//属性
	/**
	 * 权限编号
	 */
	private Long id;
	
	/**
	 * 父权限编号
	 */
	private Long parentId;
	
	/**
	 * 权限名称
	 */
	private String privilegeName;
	
	/**
	 * 权限url
	 */
	private String privilegeUrl;
	
	/**
	 * 是否系统权限 0否 1是
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
     * 权限状态
     */
    private int privilegeStatus;

    //setters and getters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getPrivilegeName() {
		return privilegeName;
	}

	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}

	public String getPrivilegeUrl() {
		return privilegeUrl;
	}

	public void setPrivilegeUrl(String privilegeUrl) {
		this.privilegeUrl = privilegeUrl;
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

	public int getPrivilegeStatus() {
		return privilegeStatus;
	}

	public void setPrivilegeStatus(int privilegeStatus) {
		this.privilegeStatus = privilegeStatus;
	}
    
}
