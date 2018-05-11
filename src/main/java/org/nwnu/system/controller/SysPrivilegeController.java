package org.nwnu.system.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

import org.nwnu.system.entity.SysPrivilege;
import org.nwnu.system.entity.SysRolePrivilege;
import org.nwnu.system.entity.SysUser;
import org.nwnu.system.service.SysPrivilegeService;
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
 * 权限 前端控制器
 * </p>
 *
 * @author dushik
 * @since 2018-05-08
 */
@Controller
@RequestMapping("/SysPrivilege")
public class SysPrivilegeController extends BaseController {
	@Autowired
	private SysPrivilegeService this_SysPrivilegeService;
	@Autowired
	private SysRolePrivilegeService sysroleprivilegeService;


	/**
     * index page.
     * @return
     */
    @RequestMapping(value = "/SysPrivilegeIndex")
    @ResponseBody
    public ModelAndView permission(ModelAndView modelAndView,
                                   @RequestParam(value = "page", defaultValue = "1") Integer page,
                                   @RequestParam(value = "offset", defaultValue = "15") Integer offset){
        List<SysPrivilege> sysPrivilegeList = new ArrayList<>();

        List<SysPrivilege> parentSysPrivilegeList = this_SysPrivilegeService
        		.selectList(new EntityWrapper<SysPrivilege>().eq("parentcode", "0000"));

        for (SysPrivilege sysPrivilege : parentSysPrivilegeList){
            sysPrivilegeList.add(sysPrivilege);
            List<SysPrivilege> subSysPrivilegeList = this_SysPrivilegeService
                    .selectList(new EntityWrapper<SysPrivilege>().eq("parentcode", sysPrivilege.getPrivilegecode()));
            sysPrivilegeList.addAll(subSysPrivilegeList);
        }
        modelAndView.addObject("sysPrivilegeLists", sysPrivilegeList);
        modelAndView.addObject("count", sysPrivilegeList.size());
        return modelAndView;
    }
    
    /**
     * get parent SysPrivilegeList.
     * @return
     */
    private List<SysPrivilege> getSysPrivileges(){
        List<SysPrivilege> sysPrivileges = this_SysPrivilegeService
        		.selectList(new EntityWrapper<SysPrivilege>().eq("parentcode", "0000"));
        return sysPrivileges;
    }


	 /**
     * add and modify view.
     * @param modelAndView
     * @param this_SysPrivilege
     * @param id
     * @return
     */
    @RequestMapping(value = "/SysPrivilegeView")
    public ModelAndView permissionAdd(ModelAndView modelAndView,
                                      SysPrivilege this_SysPrivilege,
                                      @RequestParam(value = "id", required = false) Integer id){
        if(id != null){
            this_SysPrivilege = this_SysPrivilegeService.selectById(id);
        }
        else {
            this_SysPrivilege.setPrivilegecode(this_SysPrivilegeService.getCode());
        }

        modelAndView.addObject("sysPrivilegeList", getSysPrivileges());
        modelAndView.addObject("sysPrivilege", this_SysPrivilege);
        return modelAndView;
    }

    /**
     * save action.
     * @param sysPrivilege
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Object save(SysPrivilege sysPrivilege, HttpSession session){
    	sysPrivilege.setUid(((SysUser)session.getAttribute("sysLoginUser")).getId());
        sysPrivilege.setUptime(new Date());

        if(sysPrivilege.getId() == null){
            if(this_SysPrivilegeService.insert(sysPrivilege)){
                return renderSuccess("保存成功！");
            } else{
                return renderError("保存失败！");
            }
        }else{
            if (this_SysPrivilegeService.updateById(sysPrivilege)){
                return renderSuccess("修改成功！");
            } else{
                return renderError("修改失败！");
            }
        }
    }


    /**
     * Delete item.
     * @param id
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public Object delete(@RequestParam(value = "id", required = false) Integer id){
    	SysPrivilege sysPrivilege = this_SysPrivilegeService.selectById(id);
    	sysroleprivilegeService.delete(new EntityWrapper<SysRolePrivilege>()
    			.eq("privilegecode", sysPrivilege.getPrivilegecode()));
        boolean retInt = this_SysPrivilegeService.deleteById(id);
        if (retInt){
            return renderSuccess("菜单删除成功！");
        }
        else {
            return renderError("菜单删除失败！");
        }
    }

    /**
     * Batch delete items.
     * @param ids
     * @return
     */
    @RequestMapping(value = "batchDelete", method = RequestMethod.POST)
    @ResponseBody
    public Object batchDelete(@RequestParam(value = "ids", required = false) String ids){
        String[] idsArray =  ids.split(",");
        List<Integer> idsList = new ArrayList<>();
        for(String id : idsArray){
            idsList.add(Integer.parseInt(id));
            SysPrivilege sysPrivilege = this_SysPrivilegeService.selectById(id);
            if(sysroleprivilegeService.delete(new EntityWrapper<SysRolePrivilege>()
            		.eq("privilegecode", sysPrivilege.getPrivilegecode()))){
            	continue;
            }else{
            	return renderError("批量删除失败！");
            }
        }
        boolean retInt = this_SysPrivilegeService.deleteBatchIds(idsList);

        if (retInt){
            return renderSuccess("批量删除成功！");
        }
        else {
            return renderError("批量删除失败！");
        }
    }

    /**
     * 停用权限
     * @param id
     * @return
     */
    @RequestMapping(value = "stop", method = RequestMethod.POST)
    @ResponseBody
    public Object stop(@RequestParam(value = "id", required = false) Integer id){
        SysPrivilege sysPrivilege = this_SysPrivilegeService.selectById(id);
        sysPrivilege.setIsshow("b");
        boolean retInt = this_SysPrivilegeService.updateById(sysPrivilege);
        if (retInt){
            return renderSuccess("已停用！");
        }
        else {
            return renderError("停用失败！");
        }
    }

    /**
     * 启用权限
     * @param id
     * @return
     */
    @RequestMapping(value = "start", method = RequestMethod.POST)
    @ResponseBody
    public Object start(@RequestParam(value = "id", required = false) Integer id){
        SysPrivilege sysPrivilege = this_SysPrivilegeService.selectById(id);
        sysPrivilege.setIsshow("a");
        boolean retInt = this_SysPrivilegeService.updateById(sysPrivilege);
        if (retInt){
            return renderSuccess("已启用！");
        }
        else {
            return renderError("启用失败！");
        }
    }

}
