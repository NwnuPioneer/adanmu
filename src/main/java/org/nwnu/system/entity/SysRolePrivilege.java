package org.nwnu.system.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 角色权限
 * </p>
 *
 * @author dushik
 * @since 2018-05-08
 */

@TableName("sys_role_privilege")
public class SysRolePrivilege implements Serializable, Comparable<SysRolePrivilege> {
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
	 * 权限编码集
	 */
	private String privilegecode;

	/**
	 * 操作员id
	 */
	private Integer uid;

	/**
	 * 操作时间
	 */
	private Date uptime;
	
	@TableField(exist = false)
	private SysPrivilege sysPrivilege;

	public SysPrivilege getSysPrivilege() {
		return sysPrivilege;
	}

	public void setSysPrivilege(SysPrivilege sysPrivilege) {
		this.sysPrivilege = sysPrivilege;
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

	public String getPrivilegecode() {
		return privilegecode;
	}

	public void setPrivilegecode(String privilegecode) {
		this.privilegecode = privilegecode;
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

	@Override
	public int compareTo(SysRolePrivilege o) {
		// TODO Auto-generated method stub
		
		int sequence = o.getSysPrivilege().getSequence();
		
		return this.getSysPrivilege().getSequence().compareTo(sequence);
	}

}
