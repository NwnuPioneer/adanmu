package org.nwnu.system.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户角色
 * </p>
 *
 * @author dushik
 * @since 2018-05-08
 */

@TableName("sys_role")
public class SysRole implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * 角色编码
	 */
	private String rolecode;

	/**
	 * 角色名称
	 */
	private String rolename;

	/**
	 * 角色名称
	 */
	private String roletype;
	/**
	 * 显示顺序
	 */
	private Integer sequence;

	/**
	 * 是否启用
	 */
	private String status;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 操作员id
	 */
	private Integer uid;

	/**
	 * 操作时间
	 */
	private Date uptime;
	
	@TableField(exist = false)
	private SysUser sysUser;

	
	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRolecode() {
		return rolecode;
	}

	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getRoletype() {
		return roletype;
	}

	public void setRoletype(String roletype) {
		this.roletype = roletype;
	}
	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Date getUptime() {
		return uptime;
	}

	public void setUptime(Date uptime) {
		this.uptime = uptime;
	}

}
