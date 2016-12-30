package com.cuit.boke.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckLoginFilter extends HttpServlet implements javax.servlet.Filter{



	public void doFilter(ServletRequest sRequest, ServletResponse sResponse,
			FilterChain filterChain) throws IOException, ServletException {
			
			HttpServletRequest request = (HttpServletRequest) sRequest;
	        HttpServletResponse response = (HttpServletResponse) sResponse;
	        HttpSession session = request.getSession();        
	        String   request_uri   =   request.getRequestURI();  
	        String contextPath=request.getContextPath();
	        
	        System.out.println("checking......");
            String user=(String)session.getAttribute("isLogin");
            System.out.println("user:::"+user);
            //排除登录页过滤
            if(request_uri.substring(contextPath.length()).equals("/admin")){
            	filterChain.doFilter(request, response); 
                return;
            }
            //判断有登录直接跳入主页
            if(user != null && user.equals("true")){
            	 filterChain.doFilter(request, response); 
                 return;    
            }
          //转入管理员登陆页面    
            response.sendRedirect(contextPath+"/admin");   
	        filterChain.doFilter(sRequest, sResponse);    
	}
	public void init(FilterConfig arg0) throws ServletException {  
		   
    }  
}
