package org.nwnu.system.service.impl;

import org.nwnu.system.entity.SysRole;
import org.nwnu.system.mapper.SysRoleMapper;
import org.nwnu.system.service.SysRoleService;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色 服务实现类
 * </p>
 *
 * @author dushik
 * @since 2018-05-08
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

	@Autowired
	private SysRoleMapper sysroleMapper;	
	
	public String getCode() {
		String obj=sysroleMapper.getCode();
		 if (obj != null)
        {
            return obj;
        }
        else
        {
            return "0001";
        }	
	}	
}
