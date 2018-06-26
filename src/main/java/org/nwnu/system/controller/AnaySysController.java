package org.nwnu.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nwnu.base.controller.BaseController;
import org.nwnu.system.entity.SysContent;
import org.nwnu.system.entity.UserAnchor;
import org.nwnu.system.service.SysContentService;
import org.nwnu.system.service.UserAnchorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dushik
 * @since 2018-05-08
 */
@Controller
@RequestMapping("/AnaySys")
public class AnaySysController extends BaseController  {
	@Autowired
	private UserAnchorService this_UserAnchorService;	
	@Autowired
	private SysContentService this_SysContentService;	
	/***
	 * 实时弹幕量的首页
	 * 	 
	 */
	@RequestMapping(value = "/NowIndex")
	public ModelAndView nowIndex(ModelAndView modelAndView) {
		return modelAndView;
	}	
	
	/***
	 * 词云展示首页
	 * 	 
	 */
	@RequestMapping(value = "/WordIndex")
	public ModelAndView wordIndex(ModelAndView modelAndView) {
		return modelAndView;
	}	
	
	/***
	 * 真实弹幕首页
	 * 	 
	 */
	@RequestMapping(value = "/RealIndex")
	public ModelAndView realIndex(ModelAndView modelAndView) {
		return modelAndView;
	}	
	
	/***
	 * 词频首页
	 * 	 
	 */
	@RequestMapping(value = "/CipingIndex")
	public ModelAndView cipingIndex(ModelAndView modelAndView) {
		return modelAndView;
	}	
	/**
	 * 主播弹幕统计
	 * @return
	 */
	@RequestMapping(value = "/AchorIndex")
	public String achorIndex() {		
		return "AnaySys/AchorIndex";
	}
	/**
	 * 获取主播弹幕统计
	 */
	@RequestMapping(value = "/getAnchorList", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAnchorList() {
		List<UserAnchor> anchorList=this_UserAnchorService.selectList(new EntityWrapper<UserAnchor>());
		String[] acnhor=new String[anchorList.size()];
		int[] number=new int[anchorList.size()];
		int i=0;
		for(UserAnchor anchor:anchorList){
			acnhor[i]=anchor.getName();
			number[i]=this_SysContentService.selectList(new EntityWrapper<SysContent>().eq("pid",anchor.getRid().toString())).size();
			i++;
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("acnhor", acnhor);
		result.put("number", number);
		return result;
	}	
}
