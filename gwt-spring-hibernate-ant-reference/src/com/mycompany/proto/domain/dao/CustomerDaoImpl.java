package com.mycompany.proto.domain.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mycompany.proto.domain.Customer;
import com.mycompany.proto.log.Log;

public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao{

	public void save(Customer customer) {
		Log.trace(this, "save(customer="+customer+")");
		getSession().save(customer);
	}
	
	@SuppressWarnings("unchecked")
	public List<Customer> getAllCustomers() {
		Log.trace(this, "getAllCustomers()");
		return getSession().createQuery("FROM Customer").list();
	}

	public void deleteAll() {
		getSession().createQuery("DELETE FROM Customer").executeUpdate();
	}
}
