package org.nwnu.base;

import java.util.List;

import org.junit.Test;
import org.nwnu.pub.util.AppendFile;
import org.nwnu.system.entity.SysContent;
import org.nwnu.system.entity.UserAnchor;
import org.nwnu.system.entity.UserFans;
import org.nwnu.system.service.SysContentService;
import org.nwnu.system.service.UserAnchorService;
import org.nwnu.system.service.UserFansService;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.mapper.EntityWrapper;

public class filePathTest {
	@Autowired
	private UserAnchorService this_UserAnchorService;	
	@Autowired
	private SysContentService this_SysContentService;
	@Autowired
	private UserFansService this_UserFansService;
	
	@Test
	public void showPath(){
		AppendFile appendFile=new AppendFile();
		List<UserFans> userFansList=this_UserFansService.selectList(new EntityWrapper<UserFans>().groupBy("uid").orderBy("level", false));
		String path="temp.md";
		for(UserFans fans:userFansList){
			System.out.println("*****"+fans.getNamme());
			appendFile.method2(path, fans.getNamme());
		}
	}
}
