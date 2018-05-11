package org.nwnu.system.service.impl;

import org.nwnu.system.entity.SysPrivilege;
import org.nwnu.system.mapper.SysPrivilegeMapper;
import org.nwnu.system.service.SysPrivilegeService;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author dushik
 * @since 2018-05-08
 */
@Service
public class SysPrivilegeServiceImpl extends ServiceImpl<SysPrivilegeMapper, SysPrivilege> implements SysPrivilegeService {

	@Autowired
	private SysPrivilegeMapper sysPrivilegeMapper;	
	
	@Override
    public String getCode() {
        String conStr = sysPrivilegeMapper.getCode();
        if(conStr != null){
            return conStr;
        } else{
            return "0001";
        }
    }	
}
