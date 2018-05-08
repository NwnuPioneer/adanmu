package org.nwnu.system.service.impl;

import java.util.Date;

import org.nwnu.pub.util.StringUtil;
import org.nwnu.system.entity.SysRolePrivilege;
import org.nwnu.system.mapper.SysRolePrivilegeMapper;
import org.nwnu.system.service.SysRolePrivilegeService;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * <p>
 * 角色权限 服务实现类
 * </p>
 *
 * @author dushik
 * @since 2018-05-08
 */
@Service
public class SysRolePrivilegeServiceImpl extends ServiceImpl<SysRolePrivilegeMapper, SysRolePrivilege> implements SysRolePrivilegeService {


	@Override
	public boolean roletgrant(String privilegelist, String rolecode,
			String operator, Date updateDate) {
		try{
		EntityWrapper<SysRolePrivilege> wrapper = new EntityWrapper<SysRolePrivilege>();
		wrapper.eq("roleCode", rolecode);
		boolean flag = true;
		if (this.selectList(wrapper).size() > 0) // 如果曾经有授权，先删除授权
		{
			if (this.delete(wrapper))// 删除成功
			{
				if (StringUtil.isEmpty(privilegelist)) {
					return true;
				} else {
					String[] plst = privilegelist.split(",");
					for (int j = 0; j < plst.length; j++) {
						SysRolePrivilege srdao = new SysRolePrivilege();
						srdao.setPrivilegecode(plst[j]);
						srdao.setRolecode(rolecode);
						// todo
						srdao.setUid(1);
						srdao.setUptime(updateDate);
						if (this.insert(srdao)) {
							continue;
						} else {
							flag = false;
							break;
						}
					}
					if (flag) {
						return true;
					} else {
						return false;
					}
				}
			} else {
				return false;
			}
		} else {
			if (privilegelist == null || privilegelist == "") {
				return true;
			} else {
				String[] plst = privilegelist.split(",");
				for (int j = 0; j < plst.length; j++) {
					SysRolePrivilege srdao = new SysRolePrivilege();
					srdao.setPrivilegecode(plst[j]);
					srdao.setRolecode(rolecode);
					// todo
					srdao.setUid(1);
					srdao.setUptime(updateDate);
					if (this.insert(srdao)) {
						continue;
					} else {
						flag = false;
						break;
					}

				}
				if (flag) {
					return true;
				} else {
					return false;
				}
			}
		}
		 }catch(Exception e){
	            //异常处理手动回滚
	        	TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();  
	            return false;
	        }

	}
}
