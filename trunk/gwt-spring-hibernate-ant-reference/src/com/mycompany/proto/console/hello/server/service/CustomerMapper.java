package com.mycompany.proto.console.hello.server.service;

import com.mycompany.proto.console.hello.client.CustomerAdaptor;
import com.mycompany.proto.domain.Customer;
import com.mycompany.proto.domain.dao.CustomerDao;

import net.sf.dozer.util.mapping.MapperIF;

public class CustomerMapper {
	
	private MapperIF beanMapper;
	private CustomerDao customerDao;
	
	/**
	 * IoC Setter.
	 * @param customerDao
	 */
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	/**
	 * IoC Setter.
	 * @param beanMapper
	 */
	public void setBeanMapper(MapperIF beanMapper) {
		this.beanMapper = beanMapper;
	}

	public CustomerAdaptor map(Customer customer) {
		return (CustomerAdaptor) beanMapper.map(customer, CustomerAdaptor.class);
	}
	
	public Customer map(CustomerAdaptor adaptor) {
		return customerDao.find(adaptor.getId());
	}
}
