package org.nwnu.base.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nwnu.pub.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;



/**
 * Describe: 基础控制器
 */
public class BaseController {

	@Autowired
	private HttpServletRequest request;
	
	/**
	 * 渲染失败数据
	 *
	 * @return result
	 */
	protected JsonResult renderError() {
		JsonResult result = new JsonResult();
		result.setSuccess(false);
		result.setStatus("500");
		return result;
	}
	
	protected JsonResult renderError(Object obj) {
		JsonResult result = renderError();
		result.setObj(obj);
		return result;
	}

	/**
	 * 渲染失败数据（带消息）
	 *
	 * @param msg
	 *            需要返回的消息
	 * @return result
	 */
	protected JsonResult renderError(String msg) {
		JsonResult result = renderError();
		result.setMsg(msg);
		return result;
	}

	/**
	 * 渲染成功数据
	 *
	 * @return result
	 */
	protected JsonResult renderSuccess() {
		JsonResult result = new JsonResult();
		result.setSuccess(true);
		result.setStatus("200");
		return result;
	}

	/**
	 * 渲染成功数据（带信息）
	 *
	 * @param msg
	 *            需要返回的信息
	 * @return result
	 */
	protected JsonResult renderSuccess(String msg) {
		JsonResult result = renderSuccess();
		result.setMsg(msg);
		return result;
	}

	/**
	 * 渲染成功数据（带数据）
	 *
	 * @param obj
	 *            需要返回的对象
	 * @return result
	 */
	protected JsonResult renderSuccess(Object obj) {
		JsonResult result = renderSuccess();
		result.setObj(obj);
		return result;
	}

	protected String getCurrTime() {
		Date now = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String s = outFormat.format(now);
		return s;
	}

	/**
	 *
	 * @param sDate
	 *            日期格式化
	 * @return result
	 */
	protected String recover(String sDate) throws ParseException {
		SimpleDateFormat sdf1 = new SimpleDateFormat ("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
		Date date=sdf1.parse(sDate);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String result=sdf.format(date);

		return result;
	}

	/**
	 * 取出一个指定长度大小的随机正整数.
	 *
	 * @param length
	 *            int 设定所取出随机数的长度。length小于11
	 * @return int 返回生成的随机数。
	 */
	protected int buildRandom(int length) {
		int num = 1;
		double random = Math.random();
		if (random < 0.1) {
			random = random + 0.1;
		}
		for (int i = 0; i < length; i++) {
			num = num * 10;
		}
		return (int) ((random * num));
	}


	/*
	 * Java文件操作 获取文件扩展名
	 */
	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}
	

	// 文件上传
	public boolean upload(String path, String fileName, MultipartFile file) {
		File targetFile = new File(path, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		// 保存
		try {
			file.transferTo(targetFile);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	   /** 
	    * 获得该月第一天 
	    * @param year 
	    * @param month 
	    * @return 
	    */  
	    public static String getFirstDayOfMonth(){  
	            Calendar cal = Calendar.getInstance();    
	            //设置月份  
	            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));  
	            //获取某月最小天数  
	            int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);  
	            //设置日历中月份的最小天数  
	            cal.set(Calendar.DAY_OF_MONTH, firstDay);  
	            //格式化日期  
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	            String firstDayOfMonth = sdf.format(cal.getTime());  
	            return firstDayOfMonth;  
	        }  
	      
	    /** 
	    * 获得该月最后一天 
	    * @param year 
	    * @param month 
	    * @return 
	    */  
	    public static String getLastDayOfMonth(){  
	            Calendar cal = Calendar.getInstance();  
	            //设置月份  
	            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH));  
	            //获取某月最大天数  
	            int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);  
	            //设置日历中月份的最大天数  
	            cal.set(Calendar.DAY_OF_MONTH, lastDay);  
	            //格式化日期  
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	            String lastDayOfMonth = sdf.format(cal.getTime());  
	            return lastDayOfMonth;  
	        }
	    /**
	     * 当前日期的月份-1
	     * @return
	     */
	    public static String getLastCurrentDate(){
	        Calendar calendar = Calendar.getInstance();//日历对象  
	        calendar.setTime(new Date());//设置当前日期  
	        calendar.add(Calendar.MONTH, -1);//月份减一  
	    	return (new SimpleDateFormat("yyyy-MM-dd")).format(calendar.getTime());
	    }
		/**
	     * 
	     * @Title: processFileName
	     * 
	     * @Description: ie,chrom,firfox下处理文件名显示乱码
	     */
	    public static String processFileName(HttpServletRequest request, String fileNames) {
	        String codedfilename = null;
	        try {
	            String agent = request.getHeader("USER-AGENT");
	            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
	                    && -1 != agent.indexOf("Trident")) {// ie

	                String name = java.net.URLEncoder.encode(fileNames, "UTF8");

	                codedfilename = name;
	            } else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等


	                codedfilename = new String(fileNames.getBytes("UTF-8"), "iso-8859-1");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return codedfilename;
	    }
}
