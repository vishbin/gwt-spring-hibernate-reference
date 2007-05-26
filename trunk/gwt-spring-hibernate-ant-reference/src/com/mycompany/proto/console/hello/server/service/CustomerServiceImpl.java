package com.mycompany.proto.console.hello.server.service;

import java.util.ArrayList;
import java.util.List;

import com.mycompany.proto.console.hello.client.CustomerAdaptor;
import com.mycompany.proto.console.hello.client.CustomerInterface;
import com.mycompany.proto.console.hello.client.CustomerService;
import com.mycompany.proto.domain.Customer;
import com.mycompany.proto.domain.dao.CustomerDao;

public class CustomerServiceImpl implements CustomerService {
	
	private CustomerDao customerDao;
	
	/**
	 * IoC Setter.
	 * @param customerDao
	 */
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public void deleteCustomer(Long customerId) {
		customerDao.delete(customerId);
	}

	@SuppressWarnings("unused")
	private List getCustomerInterfaces() {
		return customerDao.getAllCustomers();
	}
	
	private List getCustomerAdaptors() {
		List<Customer> customers = customerDao.getAllCustomers();
		ArrayList<CustomerAdaptor> adaptors = new ArrayList<CustomerAdaptor>(customers.size());
		for (Customer customer : customers) {
			adaptors.add(convert(customer));
		}
		return adaptors;
	}
	
	private CustomerAdaptor convert(Customer customer) {
		return new CustomerAdaptor(customer.getId(),customer.getFirstName(),customer.getLastName());
	}

	public CustomerInterface createCustomer(String firstName, String lastName) {
		Customer customer = new Customer(firstName, lastName);
		customerDao.save(customer);
		return convert(customer);
	}

	public List getCustomers() throws Exception {
		return getCustomerAdaptors();
	}
}
