package org.nwnu.system.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

import org.nwnu.system.entity.SysDict;
import org.nwnu.system.entity.SysUser;
import org.nwnu.system.service.SysDictService;
import org.nwnu.system.service.SysUserService;
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
 * 系统字典 前端控制器
 * </p>
 *
 * @author dushik
 * @since 2018-05-08
 */
@Controller
@RequestMapping("/SysDict")
public class SysDictController extends BaseController {
	@Autowired
	private SysDictService this_SysDictService;	
	@Autowired
	private SysUserService this_SysUserService;
	
	/***
	 * 每个controller的首页
	 * 	 
	 */
	@RequestMapping(value = "/SysDictIndex")
	public ModelAndView SysDictIndex(ModelAndView modelAndView) {
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
		EntityWrapper<SysDict> wrapper=new EntityWrapper<SysDict>();	
		//如果有查询条件，此处需要构造查询warpper
		//例如wrapper.eq();			
		List<SysDict> SysDictList=this_SysDictService.selectPage(
				new Page<SysDict>(page,pagesizes),
				wrapper.orderBy("seq", true)//根据顺序倒序输出
				).getRecords();	
		for(SysDict sd:SysDictList){
			SysUser sysUser=this_SysUserService.selectById(sd.getUid());
			if(sysUser!=null){
				sd.setUName(sysUser.getName());
			}
			else{
				sd.setUName("该用户已不存在");
			}
		}
		Map<String, Object> result = new HashMap<String, Object>();		
		int total=this_SysDictService.selectList(wrapper).size();		
		result.put("total", total);
		result.put("data", SysDictList);		
		return result;
	}

	/***
	 * 单页，如果是修改，显示内容及表单，如果是添加显示表单
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/SysDictView", method = RequestMethod.GET)
	public ModelAndView view(ModelAndView modelAndView, SysDict this_SysDict, @RequestParam(value = "id", required = false) Integer id) {	
		if (id != null) {
            modelAndView.addObject("SysDict", this_SysDictService.selectById(id));
        }else
        {        	
        	modelAndView.addObject("SysDict",this_SysDict);
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
	public Object Save(SysDict this_SysDict,HttpSession session) {
	  		 	  		 	 	 if(StringUtil.isEmpty(this_SysDict.getDict())){
		 	return renderError("字段名称不能为空");		}
			  		 	 	 if(StringUtil.isEmpty(this_SysDict.getDictzh())){
		 	return renderError("字段中文名称不能为空");		}
			  		 	 	 if(StringUtil.isEmpty(this_SysDict.getDictname())){
		 	return renderError("字段显示不能为空");		}
			  		 	 	 if(StringUtil.isEmpty(this_SysDict.getDictvalue())){
		 	return renderError("字段存储值不能为空");		}
			  		 	 	 if(StringUtil.isEmpty(this_SysDict.getSeq())){
		 	return renderError("显示顺序不能为空");		}
	   this_SysDict.setUid(((SysUser)session.getAttribute("sysLoginUser")).getId());
	   this_SysDict.setUptime(new Date());
	   if (this_SysDict.getId() == null) {
            return this_SysDictService.insert(this_SysDict) ? renderSuccess("添加成功") : renderError("添加失败");
        } else {
            return this_SysDictService.updateById(this_SysDict) ? renderSuccess("修改成功") : renderError("修改失败");
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
		return this_SysDictService.deleteById(id) ? renderSuccess("删除成功") : renderError("删除失败");
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
			return this_SysDictService.deleteBatchIds(idList) ? renderSuccess("删除成功") : renderError("删除失败");
		}else{
			return renderError("请选择需要删除的数据");
		}
		
	}
	
}
