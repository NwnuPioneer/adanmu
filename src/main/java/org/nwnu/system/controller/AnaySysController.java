package org.nwnu.system.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nwnu.base.controller.BaseController;
import org.nwnu.pub.util.AppendFile;
import org.nwnu.system.entity.SysContent;
import org.nwnu.system.entity.UserAnchor;
import org.nwnu.system.entity.UserFans;
import org.nwnu.system.service.SysContentService;
import org.nwnu.system.service.UserAnchorService;
import org.nwnu.system.service.UserFansService;
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
	@Autowired
	private UserFansService this_UserFansService;
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
	public String wordIndex() {
		return "AnaySys/WordIndex";
	}	
	/**
	 * 词云
	 */
	@RequestMapping(value="/getWord")
	@ResponseBody
	public Map<String,Object> getWord(){
		Map<String,Object> result=new HashMap<String,Object>();
		String[] word=new String[]{"{name: '皮鞋剑谱',value: 4055},",
				"{name: '风男风男',value: 100},",
				"{name: '米勒',value: 85},",
				"{name: '虐菜',value: 30},",
				"{name: '狗牌',value: 20},",
				"{name: '声音',value: 5},",
				"{name: '刷新',value: 80},",
				"{name: '经济',value: 75},",
				"{name: '姿态',value: 66}",
				"{name: '蒙多',value: 78},",
				"{name: '版本',value: 26},",
				"{name: 'lgd',value: 78},",
				"{name: '皮鞋',value: 88},",
				"{name: '辛德拉',value: 89},",
				"{name: '压力',value: 45},",
				"{name: '世界杯',value: 56},",
				"{name: '秒表',value: 78}"};
		result.put("word", word);
		return result;
	}
	
	/***
	 * 真实弹幕首页
	 * 	 
	 */
	@RequestMapping(value = "/RealIndex")
	public String realIndex() {
		return "AnaySys/RealIndex";
	}	
	/**
	 * 计算粉丝等级发言占比
	 */
	@RequestMapping(value="/getReal")
	@ResponseBody
	public Map<String,Object> getReal(){
		List<UserFans> userFansList=this_UserFansService.selectList(new EntityWrapper<UserFans>().orderBy("level", false));
		int levels=100;
		int[] count=new int[11];
		int sum=0,index=-1;
		for(UserFans fans:userFansList){
			if(fans.getLevel()<levels){
				levels-=10;
				index+=1;
				count[index]=sum;
				sum=0;
			}
			sum+=1;
		}
		Map<String,Object> result=new HashMap<String,Object>();
		String[] level=new String[]{"101~","91~100","81~90","71~80","61~70","51~60","41~50","31~40","21~30","11~20","0~10"};
		result.put("count", count);
		result.put("level", level);
		return result;
	}
	
	/***
	 * 词频首页
	 * 	 
	 */
	@RequestMapping(value = "/CipingIndex")
	public ModelAndView cipingIndex(ModelAndView modelAndView) {
/*		AppendFile appendFile=new AppendFile();
		List<UserFans> userFansList=this_UserFansService.selectList(new EntityWrapper<UserFans>().groupBy("uid").orderBy("level", false));
		String path="temp.md";
		for(UserFans fans:userFansList){
			System.out.println("*****"+fans.getNamme());
			appendFile.method2(path, fans.getNamme());
		}*/
/*		AppendFile appendFile=new AppendFile();
		List<SysContent> userFansList=this_SysContentService.selectList(new EntityWrapper<SysContent>().eq("pid", 520));
		String path="temp1.md";
		int i=0;
		for(SysContent fans:userFansList){
			i+=1;
			System.out.println("*****"+fans.getTxt());
			appendFile.method2(path, fans.getTxt());
			if(i>=500){
				return modelAndView;
			}
		}
		File file=new File("temp.md");
		System.out.println(file.getAbsolutePath());*/
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
