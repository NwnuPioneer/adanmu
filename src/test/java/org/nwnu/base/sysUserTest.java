package org.nwnu.base;

import java.util.List;

import org.junit.Test;
import org.nwnu.system.entity.SysDict;
import org.nwnu.system.entity.SysUser;
import org.nwnu.system.service.SysDictService;
import org.nwnu.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.mapper.EntityWrapper;

public class sysUserTest {
	
    @Autowired
    private SysUserService sysuserService;
    
    @Autowired
    private SysDictService sysdictService;
    
	@Test
	public void Select(){
		//SysUser sysLoginUsers = sysuserService.selectOne(new EntityWrapper<SysUser>().eq("tel", "13321224698"));
		List<SysDict> sydDict=sysdictService.selectList(new EntityWrapper<SysDict>());
		for(SysDict ss:sydDict){
			System.out.println("8888888888"+ss);
		}
	}
}
