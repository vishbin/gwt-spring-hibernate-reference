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
	public void saveAndFind() {
		Customer c = new Customer();
		c.setFirstName("John");
		c.setLastName("Doe");
		dao.save(c);
		
		Customer found = dao.find(c.getId());
		assertNotNull(found);
		assertEquals("John", found.getFirstName());
		assertEquals("Doe", found.getLastName());
	}
	
	@Test
	public void getAllCustomers() {
		Customer c1 = new Customer();
		c1.setFirstName("John");
		c1.setLastName("Doe");
		dao.save(c1);
		
		Customer c2 = new Customer();
		c2.setFirstName("Jane");
		c2.setLastName("Doe");
		dao.save(c2);
		
		List<Customer> customers = dao.getAllCustomers();
		assertEquals(2, customers.size());
	}
	
	@Test
	public void delete() {
		Customer c = new Customer();
		c.setFirstName("John");
		c.setLastName("Doe");
		dao.save(c);
		
		Customer found = dao.find(c.getId());
		assertNotNull(found);
		dao.delete(found.getId());
		Customer deleted = dao.find(c.getId());
		assertNull(deleted);
	}
}
