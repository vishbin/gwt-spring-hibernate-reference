package com.mycompany.proto.console.hello.server.service;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mycompany.proto.componenta.ComponentAWorker;
import com.mycompany.proto.componentb.ComponentBWorker;
import com.mycompany.proto.console.hello.client.HelloService;
import com.mycompany.proto.domain.Customer;
import com.mycompany.proto.domain.dao.CustomerDao;

public class HelloServiceImpl extends RemoteServiceServlet implements HelloService {
	private static final long serialVersionUID = 1L;
	
	private CustomerDao customerDao;
	private ComponentAWorker aWorker;
	private ComponentBWorker bWorker;

	/**
	 * IOC Setter.
	 * @param customerDao
	 */
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	/**
	 * IOC Setter.
	 * @param worker
	 */
	public void setAWorker(ComponentAWorker worker) {
		aWorker = worker;
	}

	/**
	 * IOC Setter.
	 * @param worker
	 */
	public void setBWorker(ComponentBWorker worker) {
		bWorker = worker;
	}

	public String sayHello() throws Exception {
		return "Hello World!!!" + test() + ", " + doDbStuff();
	}

	private String doDbStuff() {
		Customer customer = new Customer();
		customer.setFirstName("John");
		customer.setLastName("Doe");
		customerDao.save(customer);
		List<Customer> customers = customerDao.getAllCustomers();
		return customer.toString() + " - " + customers.size() + " customers in DB.";
	}

	private String test() {
		StringBuffer buffer = new StringBuffer();
		if (aWorker != null) {
			buffer.append(aWorker.doSomething()).append(",");
		} else {
			buffer.append("aWorker==null,");
		}
		if (bWorker != null) {
			buffer.append(bWorker.doSomething()).append(",");
		} else {
			buffer.append("bWorker==null,");
		}
		return buffer.toString();
	}
}
