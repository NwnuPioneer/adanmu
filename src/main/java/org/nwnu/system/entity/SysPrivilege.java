package org.nwnu.system.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 权限
 * </p>
 *
 * @author dushik
 * @since 2018-05-08
 */

@TableName("sys_privilege")
public class SysPrivilege implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * 权限编码
	 */
	private String privilegecode;

	/**
	 * 权限名称
	 */
	private String privilegename;

	/**
	 * 父编码
	 */
	private String parentcode;

	/**
	 * 地址
	 */
	private String url;
	
	/**
	 * 地址
	 */
	private String iconfont;
	
	/**
	 * 是否显示
	 */
	private String isshow;

	/**
	 * 显示顺序
	 */
	private Integer sequence;

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



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPrivilegecode() {
		return privilegecode;
	}

	public void setPrivilegecode(String privilegecode) {
		this.privilegecode = privilegecode;
	}

	public String getPrivilegename() {
		return privilegename;
	}

	public void setPrivilegename(String privilegename) {
		this.privilegename = privilegename;
	}

	public String getParentcode() {
		return parentcode;
	}

	public void setParentcode(String parentcode) {
		this.parentcode = parentcode;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIsshow() {
		return isshow;
	}

	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
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

	public String getIconfont() {
		return iconfont;
	}

	public void setIconfont(String iconfont) {
		this.iconfont = iconfont;
	}
	
	

}