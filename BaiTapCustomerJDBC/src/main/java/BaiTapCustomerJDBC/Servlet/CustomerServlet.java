package BaiTapCustomerJDBC.Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import BaiTapCustomerJDBC.Model.CustomerModel;

import BaiTapCustomerJDBC.Service.CustomerService;
import BaiTapCustomerJDBC.Utils.urlUtil;

import BaiTapCustomerJDBC.Utils.PathUtil;




@WebServlet(name = "customerServlet", urlPatterns = {urlUtil.CUSTOMER_ADD,urlUtil.CUSTOMER_DELETE,urlUtil.CUSTOMER_HOMEs,urlUtil.CUSTOMER_UPDATE
})
public class CustomerServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CustomerService service;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		switch (path) {
		case urlUtil.CUSTOMER_HOMEs: 
			
			List<CustomerModel> customers = service.findAllCustomers();
			
			req.setAttribute("customers", customers);
			
			req.getRequestDispatcher(PathUtil.HOME)
				.forward(req, resp);
			break;
			
		case urlUtil.CUSTOMER_ADD:
			req.getRequestDispatcher(PathUtil.ADD)
			.forward(req, resp);
			break;
		
		case urlUtil.CUSTOMER_UPDATE:
			int codeToUpdate = Integer.parseInt(req.getParameter("code"));
			
			CustomerModel customerToUpdate = service.findCustomerByCode(codeToUpdate);
			
			req.setAttribute("customer", customerToUpdate);
			
			req.getRequestDispatcher(PathUtil.UPDATE)
			.forward(req, resp);
			break;
			
		case urlUtil.CUSTOMER_DELETE:
			int codeToBeDeleted = Integer.parseInt(req.getParameter("code"));
			
			service.deleteCustomerByCode(codeToBeDeleted);
			
			resp.sendRedirect(req.getContextPath() + urlUtil.CUSTOMER_HOMEs);
			break;
		
		default:
			
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
			case urlUtil.CUSTOMER_ADD:
				CustomerModel newCustomer = new CustomerModel();
				newCustomer.setCode(Integer.parseInt(req.getParameter("code")));
				newCustomer.setName(req.getParameter("name"));
				newCustomer.setAddress(req.getParameter("address"));
				newCustomer.setEmail(req.getParameter("email"));			
				service.addNewCustomer(newCustomer);				
				resp.sendRedirect(req.getContextPath() + urlUtil.CUSTOMER_HOMEs);
				break;
			case urlUtil.CUSTOMER_UPDATE:
				int codeToUpdate = Integer.parseInt(req.getParameter("code"));			
				CustomerModel customerToUpdate = service.findCustomerByCode(codeToUpdate);			
				customerToUpdate.setName(req.getParameter("name"));
				customerToUpdate.setAddress(req.getParameter("address"));
				customerToUpdate.setEmail(req.getParameter("email"));
				
				// service.update(customerToUpdate, codeToUpdate);
				
				resp.sendRedirect(req.getContextPath() + urlUtil.CUSTOMER_HOMEs);
				break;
		} 
	}

@Override
public void init() throws ServletException {
	service = new CustomerService();
}
}
