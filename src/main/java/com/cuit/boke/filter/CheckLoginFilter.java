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
	        
	        String contextPath=request.getContextPath();    
	        System.out.println("checking......");
            String user=(String)session.getAttribute("username");    
            if(user==null){//转入管理员登陆页面    
                 response.sendRedirect(contextPath+"/admin");   
                 return;    
            }    
 
	          filterChain.doFilter(sRequest, sResponse);    
	}
	public void init(FilterConfig arg0) throws ServletException {  
		   
    }  
}
