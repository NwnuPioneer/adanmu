package org.nwnu.base.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nwnu.system.entity.SysPrivilege;
import org.nwnu.system.entity.SysRole;
import org.nwnu.system.entity.SysRolePrivilege;
import org.nwnu.system.entity.SysUser;
import org.nwnu.system.service.SysPrivilegeService;
import org.nwnu.system.service.SysRolePrivilegeService;
import org.nwnu.system.service.SysRoleService;
import org.nwnu.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.mapper.EntityWrapper;

@Controller
@RequestMapping(value="/Home")
public class HomeController extends BaseController{
	@Autowired
    private SysRolePrivilegeService sysRolePrivilegeService;

    @Autowired
    private SysPrivilegeService sysPrivilegeService;
    
    @Autowired
    private SysUserService sysuserService;
    
    @Autowired
    private SysRoleService this_SysRoleService;
    
	@Autowired
	private SysUserService this_SysUserService;
	
    @Autowired
    private HttpServletRequest request;
	
    @RequestMapping(value = "/Index", method = RequestMethod.GET)
    public ModelAndView Index(ModelAndView modelAndView, HttpSession session) {
    	SysUser sysUser = (SysUser)session.getAttribute("sysLoginUser");
    	if(sysUser!=null){
            String roleCode = sysUser.getRolecode();
            List<SysRolePrivilege> rolePrivilege = sysRolePrivilegeService.selectList(new EntityWrapper<SysRolePrivilege>().eq("roleCode", roleCode));
            List<SysRolePrivilege> rolePrivileges = new ArrayList<SysRolePrivilege>();
            for(SysRolePrivilege sysRolePrivilege : rolePrivilege){
            	SysPrivilege sysPrivilege = sysPrivilegeService.selectOne(new EntityWrapper<SysPrivilege>().eq("privilegeCode", sysRolePrivilege.getPrivilegecode()));
            	if (!sysPrivilege.getIsshow().equals("a")) {
    				continue;
    			}
            	sysRolePrivilege.setSysPrivilege(sysPrivilege);
            	rolePrivileges.add(sysRolePrivilege);
            }
            Collections.sort(rolePrivileges);
            modelAndView.addObject("SysRolePrivilege", rolePrivileges);
        	modelAndView.addObject("user", sysUser);
    	}
    	modelAndView.addObject("sysRole", this_SysRoleService
    			.selectOne(new EntityWrapper<SysRole>().eq("rolecode", sysUser.getRolecode())));
    	return modelAndView;
    }
    
    @RequestMapping(value = "/Welcome", method = RequestMethod.GET)
    public ModelAndView Welcome(ModelAndView modelAndView) {
    	return modelAndView;
    }
    
    @RequestMapping(value = "logout")
    public String logout(HttpSession session) throws Exception {
        session.invalidate();
        return "redirect:/Login/SysLogin.do";
    }
}
