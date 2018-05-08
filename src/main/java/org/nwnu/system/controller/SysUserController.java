package org.nwnu.system.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

import org.nwnu.system.entity.SysRole;
import org.nwnu.system.entity.SysUser;
import org.nwnu.system.service.SysRoleService;
import org.nwnu.system.service.SysUserService;
import org.nwnu.base.controller.BaseController;//基础包
import org.nwnu.pub.util.PasswordUtil;
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
 * 管理员 前端控制器
 * </p>
 *
 * @author dushik
 * @since 2018-05-08
 */
@Controller
@RequestMapping("/SysUser")
public class SysUserController extends BaseController {
	@Autowired
	private SysRoleService sysroleService;
	
	@Autowired
	private SysUserService this_SysUserService;
	
	
	/***
	 * 每个controller的首页
	 * 	 
	 */
	@RequestMapping(value = "/SysUserIndex")
	public ModelAndView SysUserIndex(ModelAndView modelAndView) {
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
		EntityWrapper<SysUser> wrapper=new EntityWrapper<SysUser>();		
		List<SysUser> SysUserList=this_SysUserService.selectPage(
				new Page<SysUser>(page,pagesizes),
				wrapper.orderBy("id", false)//根据id倒序输出
				).getRecords();
		for(SysUser su : SysUserList){
			SysUser sysu=this_SysUserService.selectById(su.getUid());
			if(sysu!=null){
				su.setSysUser(sysu);
			}
			else{
				SysUser sys2=new SysUser();
				sys2.setName("该用户已不存在");
				su.setSysUser(sys2);
			}
			SysRole sysrole=sysroleService.selectOne(new EntityWrapper<SysRole>().eq("rolecode", su.getRolecode()));
			if(sysrole!=null){
				su.setRolename(sysrole.getRolename());
			}
			else{
				su.setRolename("此角色已被删除");
			}
		}
		Map<String, Object> result = new HashMap<String, Object>();		
		int total=this_SysUserService.selectList(wrapper).size();		
		result.put("total", total);
		result.put("data", SysUserList);		
		return result;
	}

	/***
	 * 单页，如果是修改，显示内容及表单，如果是添加显示表单
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/SysUserView", method = RequestMethod.GET)
	public ModelAndView view(ModelAndView modelAndView, SysUser this_SysUser, 
			@RequestParam(value = "id", required = false) Integer id) {	
		List<SysRole> sysRoles = sysroleService.selectList(new EntityWrapper<SysRole>().eq("status", "a"));
		if (id != null) {
            modelAndView.addObject("SysUser", this_SysUserService.selectById(id));
        }else
        {   
        	modelAndView.addObject("SysUser",this_SysUser);
        }
		modelAndView.addObject("SysRoleList", sysRoles);
	     return modelAndView;
	}

	/***
	 * 保存，如果是新增，调用insert，如果是修改，调用updateById
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/Save", method = RequestMethod.POST)
	//@Log(operationType="Save操作:",operationName="系统管理员新增")
	@ResponseBody
	public Object Save(SysUser this_SysUser, HttpSession session) {
		if (StringUtil.isEmpty(this_SysUser.getPwd())) {
			return renderError("密码不能为空");
		}
		if (StringUtil.isEmpty(this_SysUser.getRolecode())) {
			return renderError("角色编码不能为空");
		}
		if (StringUtil.isEmpty(this_SysUser.getName())) {
			return renderError("真实姓名不能为空");
		}
		if (StringUtil.isEmpty(this_SysUser.getTel())) {
			return renderError("手机号码不能为空");
		}
		if (StringUtil.isEmpty(this_SysUser.getQq())) {
			return renderError("QQ号码不能为空");
		}
		this_SysUser.setUid(((SysUser)session.getAttribute("sysLoginUser")).getId());
		this_SysUser.setUptime(new Date());
		byte[] salt = PasswordUtil.getStaticSalt();
		String cipherText = PasswordUtil.encrypt("0000",this_SysUser.getPwd(), salt);//同意加密0000
		if(this_SysUser.getId()==null){
			if(this_SysUserService.selectOne(new EntityWrapper<SysUser>().eq("tel", this_SysUser.getTel()))==null){
				this_SysUser.setPwd(cipherText);
				return this_SysUserService.insert(this_SysUser) ? renderSuccess("添加成功")
						: renderError("添加失败");
			}
			else{
				return  renderError("用户已存在");
			}
		}
		else{
			SysUser user=this_SysUserService.selectById(this_SysUser.getId());
			if(!this_SysUser.getPwd().equals(user.getPwd())){
				this_SysUser.setPwd(cipherText);
			}
			else{
				this_SysUser.setPwd(user.getPwd());
			}
			return this_SysUserService.updateById(this_SysUser) ? renderSuccess("修改成功")
					: renderError("修改失败"); 
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
		return this_SysUserService.deleteById(id) ? renderSuccess("删除成功") : renderError("删除失败");
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
			return this_SysUserService.deleteBatchIds(idList) ? renderSuccess("删除成功") : renderError("删除失败");
		}else{
			return renderError("请选择需要删除的数据");
		}
		
	}
}
