package com.mycompany.proto.domain.dao;

import java.util.List;

import com.mycompany.proto.domain.Customer;

public interface CustomerDao {

	void save(Customer customer);
	List<Customer> getAllCustomers();
	void deleteAll();
	void delete(Long customerId);
	Customer find(Long customerId);
}
