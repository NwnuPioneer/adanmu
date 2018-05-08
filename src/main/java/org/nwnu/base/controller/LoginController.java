package org.nwnu.base.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nwnu.pub.util.PasswordUtil;
import org.nwnu.pub.util.RandomValidateCodeUtil;
import org.nwnu.system.entity.SysUser;
import org.nwnu.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;

@Controller
@RequestMapping(value="/Login")
public class LoginController extends BaseController{ 
    
	@Autowired
    private HttpServletRequest request;
	
    @Autowired
    private SysUserService sysuserService;
	
    //系统用户登陆界面
    @RequestMapping(value = "SysLogin")
    public String SysLogin() {
        return "Login/SysLogin";
    }  
    
    //登录获取验证码
    @RequestMapping("/getCode")
    @ResponseBody
    public String getSysManageLoginCode(HttpServletResponse response,
                                        HttpServletRequest request) {
        response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Set-Cookie", "name=value; HttpOnly");//设置HttpOnly属性,防止Xss攻击
        response.setDateHeader("Expire", 0);
        RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
        try {
            randomValidateCode.getRandcode(request, response, "imagecode");// 输出图片方法
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    //验证码验证
    @RequestMapping(value = "/checkimagecode")
    @ResponseBody
    public boolean checkTcode(HttpServletRequest request, HttpServletResponse response) {
        String validateCode = request.getParameter("validecode");
        String code = null;
        //1:获取cookie里面的验证码信息
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("imagecode".equals(cookie.getName())) {
                code = cookie.getValue();
                break;
            }
        }
        if (!StringUtils.isEmpty(validateCode) && validateCode.equals(code))
            return true;
        return false;
    } 
    
    //在登陆前清除session
    private void delSession(HttpSession session) {
    	if (session.getAttribute("sysLoginUser") != null) {
            session.invalidate();
        } 
	}
    
    /***
    *
    * 系统用户登陆
    */
   @RequestMapping(value = "/SysLogin", method = RequestMethod.POST)
   @ResponseBody
   public Map<String, Object> SysLogin(SysUser sysuser, HttpSession session) {
   	   delSession(session);
   	   HttpSession httpsession = request.getSession();
       Map<String, Object> result = new HashMap<String, Object>();
       if (sysuser.getTel() == null || sysuser.getTel().equals("")) {
           result.put("IsSuccess", false);
           result.put("info", "用户名不能为空");
           return result;
       }
       if (sysuser.getPwd() == null || sysuser.getPwd().equals("")) {
           result.put("IsSuccess", false);
           result.put("info", "密码不能为空");
           return result;
       }
       String validateCode = request.getParameter("validecode");
       String code = null;
       //1:获取cookie里面的验证码信息
       Cookie[] cookies = request.getCookies();
       for (Cookie cookie : cookies) {
           if ("imagecode".equals(cookie.getName())) {
               code = cookie.getValue();
               break;
           }
       }
       if (StringUtils.isEmpty(validateCode) || !validateCode.equals(code)){
       	 result.put("IsSuccess", false);
            result.put("info", "验证码错误");
            return result;
       }
       SysUser sysLoginUsers = sysuserService.selectOne(new EntityWrapper<SysUser>().eq("tel", sysuser.getTel()));
       if (sysLoginUsers != null) {
           byte[] salt = PasswordUtil.getStaticSalt();
           String cipherText = PasswordUtil.encrypt("0000", sysuser.getPwd(), salt);//由于管理员角色可能发生变化，默认用0000作为盐值
           if (sysLoginUsers.getPwd().equals(cipherText)) {
               httpsession.setAttribute("sysLoginUser", sysLoginUsers);
               result.put("IsSuccess", true);
               result.put("info", "登录成功");
           } else {
               result.put("IsSuccess", false);
               result.put("info", "用户名或密码错误");
           }
       } else {
           result.put("IsSuccess", false);
           result.put("info", "用户名或密码错误");
       }
       return result;
   }

}
