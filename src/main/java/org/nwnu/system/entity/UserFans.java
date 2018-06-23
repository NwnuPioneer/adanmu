package org.nwnu.system.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author dushik
 * @since 2018-05-08
 */

@TableName("user_fans")
public class UserFans implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * uid
	 */
	private Integer uid;

	/**
	 * 昵称
	 */
	private String namme;

	/**
	 * 徽章昵称
	 */
	private String bnn;

	/**
	 * 用户等级
	 */
	private Integer level;



	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNamme() {
		return namme;
	}

	public void setNamme(String namme) {
		this.namme = namme;
	}

	public String getBnn() {
		return bnn;
	}

	public void setBnn(String bnn) {
		this.bnn = bnn;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

}
