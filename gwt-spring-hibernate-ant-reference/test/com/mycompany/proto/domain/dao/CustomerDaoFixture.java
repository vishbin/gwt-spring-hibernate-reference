package com.mycompany.proto.domain.dao;

import java.util.List;

import static junit.framework.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.mycompany.proto.domain.Customer;
import com.mycompany.proto.test.ComponentFactory;
import com.mycompany.proto.test.TestUtils;

public class CustomerDaoFixture {
	
	private CustomerDao dao;

	@Before
	public void before() {
		TestUtils.deleteAllDatabaseData();
		dao = ComponentFactory.getInstance().getCustomerDao();
	}
	
	@Test
	public void save() {
		Customer c = new Customer();
		c.setFirstName("John");
		c.setLastName("Doe");
		dao.save(c);
		
		List<Customer> customers = dao.getAllCustomers();
		assertEquals(1, customers.size());
		Customer found = customers.get(0);
		assertEquals("John", found.getFirstName());
		assertEquals("Doe", found.getLastName());
	}
}
