package cn.edu.zzti.sys.dto;
/**
 * 权限类 值传递对象
 * @author sunwj
 *
 */
public class SysPrivilegeDto {

	//属性
	/**
	 * 主键 权限编号
	 */
	private Long id;
	
	/**
	 * 父权限编号
	 */
	private Long pid;
	
	/**
	 * 节点名称
	 */
    private String text;
    
    /**
     * 是否选中
     */
    private String isChecked;

    //setters and getters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(String isChecked) {
		this.isChecked = isChecked;
	}
    
}
