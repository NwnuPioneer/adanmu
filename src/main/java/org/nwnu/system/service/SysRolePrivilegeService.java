package org.nwnu.system.service;

import java.util.Date;

import org.nwnu.system.entity.SysRolePrivilege;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 角色权限 服务类
 * </p>
 *
 * @author dushik
 * @since 2018-05-08
 */
public interface SysRolePrivilegeService extends IService<SysRolePrivilege> {
	boolean roletgrant(String privilegelist, String rolecode,String operator,Date updateDate);
	
}
