package com.iiht.evaluation.coronokit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iiht.evaluation.coronokit.exception.CoronakitException;
import com.iiht.evaluation.coronokit.model.ProductMaster;
import com.iiht.evaluation.coronokit.service.CoronaKitServiceImpl;
import com.iiht.evaluation.coronokit.service.CoronakitService;

@WebServlet({"/login","/list","/deleteproduct","/editproduct","/newproduct","/insertproduct","/updateproduct","/logout"})
public class AdminController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private CoronakitService coronakitService;
	
	
//	public void setProductMasterDao(ProductMasterDao productMasterDao) {
//		this.productMasterDao = productMasterDao;
//	}

	public void init(ServletConfig config) {
		String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config.getServletContext().getInitParameter("jdbcPassword");

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
			case "/login" : 
				viewName = adminLogin(request, response);
				break;
			case "/newproduct":
				viewName = showNewProductForm(request, response);
				break;
			case "/insertproduct":
				viewName = addOrSaveProduct(request, response);
				break;
			case "/deleteproduct":
				viewName = deleteProduct(request, response);
				break;
			case "/editproduct":
				viewName = showEditProductForm(request, response);
				break;
			case "/updateproduct":
				viewName = addOrSaveProduct(request, response);
				break;
			case "/list":
				viewName = listAllProducts(request, response);
				break;	
			case "/logout":
				viewName = adminLogout(request, response);
				break;	
			default : viewName = "notfound.jsp"; break;		
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		request.getRequestDispatcher(viewName).forward(request, response);
		
		
	}

	private String adminLogout(HttpServletRequest request, HttpServletResponse response) {
		String view="";
		request.getSession().setAttribute("logincheck", false);		
		view="index.jsp";	
		return view;
	}

	private String listAllProducts(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String view;
		try {
			List<ProductMaster> products = coronakitService.getAllProducts();
			request.setAttribute("products", products);
			view = "listproducts.jsp";
		} catch (CoronakitException e) {
			request.setAttribute("errMsg", e.getMessage());
			view = "errPage.jsp";
		}
		return view;
	}

	
	private String showEditProductForm(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		String view = "";

		int productId = Integer.parseInt(request.getParameter("id"));
		try {
			ProductMaster product = coronakitService.getProductbyId(productId);
			request.setAttribute("Product", product);
			request.setAttribute("isNew", false);
			view = "newproduct.jsp";
			
		} catch (CoronakitException e) {
			request.setAttribute("errMsg", e.getMessage());
			view = "errPage.jsp";
		}

		return view;
	}

	private String deleteProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String view = "";
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			coronakitService.deleteproduct(id);
			request.setAttribute("msg", "Product Deleted!");			
			view = "/list";
		} catch (CoronakitException e) {
			request.setAttribute("errMsg", e.getMessage());
			view = "errPage.jsp";
		}

		return view;
	}

	private String showNewProductForm(HttpServletRequest request, HttpServletResponse response) {
		String view = "";

		ProductMaster product = new ProductMaster();
		request.setAttribute("Product", product);
		request.setAttribute("isNew", true);
		view = "newproduct.jsp";
		return view;
	
	}
	
	private String addOrSaveProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		ProductMaster product = new ProductMaster();
		
		
		product.setId(Integer.parseInt(request.getParameter("pid")));
		product.setProductName((request.getParameter("pname")));
		product.setCost(request.getParameter("cost"));
		product.setProductDescription(request.getParameter("pdescription"));		
		
		String view = "";
		
		try {
			
			if(request.getServletPath().equals("/insertproduct")) {
				coronakitService.validateAndAdd(product);
			}else {
				coronakitService.validateAndSave(product);
			}
			
			request.setAttribute("msg", "Product is saved");
			view="index.jsp";
		} catch (CoronakitException e) {
			request.setAttribute("errMsg", e.getMessage());
			view = "errPage.jsp";
		e.printStackTrace();;
		}

		return view;
	
	}

	private String adminLogin(HttpServletRequest request, HttpServletResponse response) {
		String view="";
		try {			
				request.setAttribute("msg", "Login successful");
				request.getSession().setAttribute("logincheck", true);
			
				view="index.jsp";				
				
		} catch (Exception e) {
			request.setAttribute("errMsg", e.getMessage());
			view = "errPage.jsp";
		
		}


		return view;
	}

	

	
}