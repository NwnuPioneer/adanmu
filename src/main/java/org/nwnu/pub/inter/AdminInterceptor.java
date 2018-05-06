package org.nwnu.pub.inter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AdminInterceptor implements HandlerInterceptor{

	public AdminInterceptor(){
		
	}
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		String url = request.getRequestURI();
		// 开放后台登录
		if (url.indexOf("SysLogin") >= 0){
			return true;
		}
		if(request.getSession().getAttribute("sysLoginUser") != null){
			return true;
		}
		response.sendRedirect("/Login/SysLogin.do");
		request.getSession().invalidate();
		return false;
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
