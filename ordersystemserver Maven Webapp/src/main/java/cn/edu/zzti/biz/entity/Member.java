package cn.edu.zzti.biz.entity;

import java.util.Date;

/**
 * 会员 实体类
 * @author sunwj
 *
 */
public class Member {

	//属性
	/**
	 * 主键
	 */
	private Long id;
	
	/**
	 * 会员姓名
	 */
	private String memberName;
	
	/**
	 * 会员电话
	 */
	private String phone;
	
	/**
	 * 创建时间
	 */
    private Date createDate;
    
    /**
	 * 更新时间
	 */
    private Date updateDate;
    
    /**
	 * 状态 0禁用 1启用
	 */
    private int memberStatus;

    //setters and getters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public int getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(int memberStatus) {
		this.memberStatus = memberStatus;
	}
	
}
