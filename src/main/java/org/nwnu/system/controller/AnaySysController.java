package org.nwnu.system.controller;

import org.nwnu.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
}
