package com.mycompany.proto.console.hello.server.service;

import java.util.ArrayList;
import java.util.List;

import com.mycompany.proto.console.hello.client.CustomerAdaptor;
import com.mycompany.proto.console.hello.client.CustomerInterface;
import com.mycompany.proto.console.hello.client.CustomerService;
import com.mycompany.proto.domain.Customer;
import com.mycompany.proto.domain.dao.CustomerDao;

public class CustomerServiceImpl implements CustomerService {
	
	private CustomerMapper customerMapper;
	private CustomerDao customerDao;
	
	/**
	 * IoC Setter.
	 * @param customerMapper
	 */
	public void setCustomerMapper(CustomerMapper customerMapper) {
		this.customerMapper = customerMapper;
	}

	/**
	 * IoC Setter.
	 * @param customerDao
	 */
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public void deleteCustomer(CustomerAdaptor customer) {
		customerDao.delete(customer.getId());
	}

	@SuppressWarnings("unused")
	private List getCustomerInterfaces() {
		return customerDao.getAllCustomers();
	}
	
	private List getCustomerAdaptors() {
		List<Customer> customers = customerDao.getAllCustomers();
		ArrayList<CustomerAdaptor> adaptors = new ArrayList<CustomerAdaptor>(customers.size());
		for (Customer customer : customers) {
			adaptors.add(customerMapper.map(customer));
		}
		return adaptors;
	}
	
	public CustomerInterface createCustomer(String firstName, String lastName) {
		Customer customer = new Customer(firstName, lastName);
		customerDao.save(customer);
		return customerMapper.map(customer);
	}

	public List getCustomers() {
		return getCustomerAdaptors();
	}
}
