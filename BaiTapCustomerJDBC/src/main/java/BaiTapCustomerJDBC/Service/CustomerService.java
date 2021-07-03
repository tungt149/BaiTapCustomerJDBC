package BaiTapCustomerJDBC.Service;

import java.util.LinkedList;
import java.util.List;

import BaiTapCustomerJDBC.Model.CustomerModel;
import  BaiTapCustomerJDBC.repository.CustomerReponsitory;



public class CustomerService {
	
	
		private List<CustomerModel> customers;
		private CustomerReponsitory repository;
		
		public CustomerService() {
			repository = new CustomerReponsitory();
			customers = new LinkedList<CustomerModel>();
			
	}
//	
//	public List<CustomerModel> findAllCustomers(){
//		return repository.findAllCustomer();
//	}
//	
//	public CustomerModel findCustomerByCode(int code) {
//		
//		return repository.findCustomerByCode(code);
//	}
//	
//	public int deleteCustomerByCode(int code) {
//		return repository.deleteCustomerByCode(code);
//		customers.remove(code);
//	
//	}
//	
//	public void addNewCustomer(CustomerModel customer) {
//		repository.addNewCustomer(customer);
//	}
//
//	public void update(CustomerModel customerToUpdate, int codeToUpdate) {
//		repository.updateCustomer(customerToUpdate);
//}
		public List<CustomerModel> findAllCustomers(){
			return repository.findAllCustomer();
		}
		
		public CustomerModel findCustomerByCode(int code) {
			
			return repository.findCustomerByCode(code);
		}
		
		public void deleteCustomerByCode(int code) {
			for(CustomerModel cus: customers) {
				if(cus.getCode() != code)
					continue;
				
				customers.remove(cus);
				return;
			}
		
		}
		
		public void addNewCustomer(CustomerModel customer) {
			customer.setCode(0);
			for(CustomerModel cus: customers) {
				if(cus.getCode() != customer.getCode())
					continue;
				
				return;
			}
			customers.add(customer);
		}

		public void update(CustomerModel customerToUpdate, int codeToUpdate) {
			for(int i = 0; i < customers.size(); i++) {
				if(customers.get(i).getCode() != codeToUpdate)
					continue;
				
				customerToUpdate.setCode(codeToUpdate);
				customers.set(i, customerToUpdate);
			}
		}
	}



