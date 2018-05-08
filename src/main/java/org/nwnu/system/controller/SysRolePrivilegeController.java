package org.nwnu.system.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.nwnu.system.entity.SysRolePrivilege;
import org.nwnu.system.service.SysRolePrivilegeService;
import org.nwnu.base.controller.BaseController;//基础包
import org.nwnu.pub.util.StringUtil;//自定义字符串处理类，如果没有就取掉
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

/**
 * <p>
 * 角色权限 前端控制器
 * </p>
 *
 * @author dushik
 * @since 2018-05-08
 */
@Controller
@RequestMapping("/SysRolePrivilege")
public class SysRolePrivilegeController extends BaseController {
	@Autowired
	private SysRolePrivilegeService this_SysRolePrivilegeService;	
	
	/***
	 * 每个controller的首页
	 * 	 
	 */
	@RequestMapping(value = "/SysRolePrivilegeIndex")
	public ModelAndView SysRolePrivilegeIndex(ModelAndView modelAndView) {
		return modelAndView;
	}

	/***
	 * 列表
	 * 
	 * @param 
	 * @param page
	 *            起始页
	 * @param rows
	 *            页面大小 * @param sort 排序字段 * @param rows 排序顺序
	 * @return
	 */
	@RequestMapping(value = "/List", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> GetList(
			@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "10") int pagesizes) {
		EntityWrapper<SysRolePrivilege> wrapper=new EntityWrapper<SysRolePrivilege>();	
		//如果有查询条件，此处需要构造查询warpper
		//例如wrapper.eq();			
		List<SysRolePrivilege> SysRolePrivilegeList=this_SysRolePrivilegeService.selectPage(
				new Page<SysRolePrivilege>(page,pagesizes),
				wrapper.orderBy("id", false)//根据id倒序输出
				).getRecords();	
		Map<String, Object> result = new HashMap<String, Object>();		
		int total=this_SysRolePrivilegeService.selectList(wrapper).size();		
		result.put("total", total);
		result.put("data", SysRolePrivilegeList);		
		return result;
	}

	/***
	 * 单页，如果是修改，显示内容及表单，如果是添加显示表单
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/SysRolePrivilegeView", method = RequestMethod.GET)
	public ModelAndView view(ModelAndView modelAndView, SysRolePrivilege this_SysRolePrivilege, @RequestParam(value = "id", required = false) Integer id) {	
		if (id != null) {
            modelAndView.addObject("SysRolePrivilege", this_SysRolePrivilegeService.selectById(id));
        }else
        {        	
        	modelAndView.addObject("SysRolePrivilege",this_SysRolePrivilege);
        }
	     return modelAndView;
	}

	/***
	 * 保存，如果是新增，调用insert，如果是修改，调用updateById
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/Save", method = RequestMethod.POST)
	@ResponseBody
	public Object Save(SysRolePrivilege this_SysRolePrivilege,HttpSession session) {
	  		 	  		 	 	 if(StringUtil.isEmpty(this_SysRolePrivilege.getRolecode())){
		 	return renderError("角色编码不能为空");		}
			  		 	 	 if(StringUtil.isEmpty(this_SysRolePrivilege.getPrivilegecode())){
		 	return renderError("权限编码集不能为空");		}
			  		 	 	 if(StringUtil.isEmpty(this_SysRolePrivilege.getUid())){
		 	return renderError("操作员id不能为空");		}
			  		 	 	 if(StringUtil.isEmpty(this_SysRolePrivilege.getUptime())){
		 	return renderError("操作时间不能为空");		}
/*			  	   this_SysRolePrivilege.setOperator(((SysUser)session.getAttribute("loginedUser")).getName());
	   this_SysRolePrivilege.setUpTime(new Date());*/
	   if (this_SysRolePrivilege.getId() == null) {
            return this_SysRolePrivilegeService.insert(this_SysRolePrivilege) ? renderSuccess("添加成功") : renderError("添加失败");
        } else {
            return this_SysRolePrivilegeService.updateById(this_SysRolePrivilege) ? renderSuccess("修改成功") : renderError("修改失败");
        }		
	}

	/***
	 * 根据id删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/Delete")
	@ResponseBody
	public Object Delete(@RequestParam(value = "id", required = true) Integer id) {
		return this_SysRolePrivilegeService.deleteById(id) ? renderSuccess("删除成功") : renderError("删除失败");
	}

	/***
	 * 根据ids批量删除，此方法根据前端需要
	 * 
	 * @param ids 逗号拼接项
	 * @return
	 */
	@RequestMapping("/BatchDelete")
	@ResponseBody
	public Object BatchDelete(@RequestParam(value = "ids", required = true) String ids) {
		List<Integer> idList=new ArrayList<Integer>();
		if(StringUtil.isNotEmpty(ids))
		{
			if (ids.contains("all,")) {
				ids=ids.replace("all,", "");//checkbox全选的时候带入，要去掉
			}			
			String[] lsStrings=ids.split(",");			
			for (String id : lsStrings) {
				idList.add(Integer.parseInt(id));
			}
			return this_SysRolePrivilegeService.deleteBatchIds(idList) ? renderSuccess("删除成功") : renderError("删除失败");
		}else{
			return renderError("请选择需要删除的数据");
		}
		
	}
	
	
	
}
