package com.iiht.evaluation.coronokit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iiht.evaluation.coronokit.dao.ProductMasterDao;
import com.iiht.evaluation.coronokit.exception.CoronakitException;
import com.iiht.evaluation.coronokit.model.ProductMaster;
import com.iiht.evaluation.coronokit.service.CoronaKitServiceImpl;
import com.iiht.evaluation.coronokit.service.CoronakitService;

@WebServlet({"/newuser","/insertuser","/showproducts","/addnewitem","/deleteitem","/showkit","/ordersummary"})
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CoronakitService coronakitService;

	

	
	public void init(ServletConfig config) {
		String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config. getServletContext().getInitParameter("jdbcPassword");
		

		new ProductMasterDao(jdbcURL, jdbcUsername, jdbcPassword);
		this.coronakitService = new CoronaKitServiceImpl(jdbcURL, jdbcUsername, jdbcPassword);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action =  request.getServletPath();
		String viewName = "";
		try {
			switch (action) {
			case "/newuser":
				viewName = showNewUserForm(request, response);
				break;
			case "/insertuser":
				viewName = insertNewUser(request, response);
				break;
			case "/showproducts":
				viewName = showAllProducts(request, response);
				break;	
			case "/addnewitem":
				viewName = addNewItemToKit(request, response);
				break;
			case "/deleteitem":
				viewName = deleteItemFromKit(request, response);
				break;
			case "/showkit":
				viewName = showKitDetails(request, response);
				break;			
			case "/ordersummary":
				viewName = showOrderSummary(request, response);
				break;	
			default : viewName = "notfound.jsp"; break;	
			}
		} catch (Exception ex) {
			
			throw new ServletException(ex.getMessage());
		}
			RequestDispatcher dispatch = 
					request.getRequestDispatcher(viewName);
			dispatch.forward(request, response);
	
	}

	private String showOrderSummary(HttpServletRequest request, HttpServletResponse response) {
		String view = "";	
		Map<Integer, Double> tempmap=(HashMap<Integer, Double>) request.getSession().getAttribute("totalprice");
		Collection<Double> values = tempmap.values();
		Double totalAmount=0.0;
		for (Double value : values) {
			totalAmount=totalAmount+value;
		}
		request.setAttribute("totalamount", totalAmount);
		view = "showordersummary.jsp";
		
		
	
		return view;
	}

	
	private String showKitDetails(HttpServletRequest request, HttpServletResponse response) {
		String view = "";	
		
		HashMap<Integer, Integer> tempmap=(HashMap<Integer, Integer>) request.getSession().getAttribute("quantitymap");
		List<ProductMaster> products =(List<ProductMaster>) request.getSession().getAttribute("products");
		HashMap<Integer,Double> totalprice =new HashMap<Integer,Double>();
		for (ProductMaster productMaster : products) {
			 totalprice.put(productMaster.getId(), Double.parseDouble(productMaster.getCost())*tempmap.get(productMaster.getId()));
		}
		request.getSession().setAttribute("totalprice", totalprice);		
		view = "showkit.jsp";
		return view;
	}

	private String deleteItemFromKit(HttpServletRequest request, HttpServletResponse response) {
		String view = "";	
		HashMap<Integer, Integer> tempmap=(HashMap<Integer, Integer>) request.getSession().getAttribute("quantitymap");
		if (tempmap.get(Integer.parseInt(request.getParameter("id")))>0) {
			tempmap.put(Integer.parseInt(request.getParameter("id")), tempmap.get(Integer.parseInt(request.getParameter("id")))-1);
			request.getSession().setAttribute("quantitymap", tempmap);
		} 				
		view = "showproductstoadd.jsp";
		return view;
	}

	private String addNewItemToKit(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, CoronakitException, SQLException {
		String view = "";			
		HashMap<Integer, Integer> tempmap=(HashMap<Integer, Integer>) request.getSession().getAttribute("quantitymap");
		tempmap.put(Integer.parseInt(request.getParameter("id")), tempmap.get(Integer.parseInt(request.getParameter("id")))+1);
		request.getSession().setAttribute("quantitymap", tempmap);
		view = "showproductstoadd.jsp";
		return view;
	}

	private String showAllProducts(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String view;
		try {
			
			List<ProductMaster> products = coronakitService.getAllProducts();
			request.getSession().setAttribute("products", products);
			HashMap<Integer,Integer> quantitymap =new HashMap<Integer,Integer>();
			for (ProductMaster productMaster : products) {
				quantitymap.put(productMaster.getId(), 0);			}
//			request.setAttribute("quantitymap", quantitymap);
			request.getSession().setAttribute("quantitymap", quantitymap);
			view = "showproductstoadd.jsp";
		} catch (CoronakitException e) {
			request.setAttribute("errMsg", e.getMessage());
			view = "errPage.jsp";
		}
		return view;
		
	}

	private String insertNewUser(HttpServletRequest request, HttpServletResponse response) {
		String view="";
		request.getSession().setAttribute("username", request.getParameter("username"));
		request.getSession().setAttribute("contactNumber", request.getParameter("contactNumber"));
		request.getSession().setAttribute("email", request.getParameter("email"));
		request.getSession().setAttribute("address", request.getParameter("address"));
		view = "/showproducts";
		return view;
	}

	private String showNewUserForm(HttpServletRequest request, HttpServletResponse response) {
		String view = "";
		view = "newuser.jsp";
		return view;
	}
}