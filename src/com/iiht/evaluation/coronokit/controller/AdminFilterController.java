package com.iiht.evaluation.coronokit.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
@WebFilter("/login")
public class AdminFilterController implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if ((request.getParameter("loginid").equals("admin")) && (request.getParameter("password").equals("admin"))) {
//			request.setAttribute("msg", "Login successful");		
			chain.doFilter(request, response);
		} else {
			request.setAttribute("msg", "Invalid Userid or Password!");		
			 RequestDispatcher rd=request.getRequestDispatcher("index.jsp");  
			 rd.include(request, response);  
		}			
		
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
