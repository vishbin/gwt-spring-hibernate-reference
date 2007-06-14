package com.mycompany.proto.domain.dao;

import java.util.List;

import org.hibernate.criterion.Expression;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.mycompany.proto.domain.Customer;
import com.mycompany.proto.log.Log;

public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao{

	public void save(Customer customer) {
		Log.trace(this, "save(customer="+customer+")");
		getHibernateTemplate().save(customer);
	}
	
	@SuppressWarnings("unchecked")
	public List<Customer> getAllCustomers() {
		Log.trace(this, "getAllCustomers()");
		return getSession().createQuery("FROM Customer").list();
	}

	public void deleteAll() {
		getSession().createQuery("DELETE FROM Customer").executeUpdate();
	}

	public void delete(Long customerId) {
		Customer customer = find(customerId);
		 if (customer != null) {
			getSession().delete(customer);
		} else {
			throw new IllegalArgumentException("Customer ID not found ("+customerId+").");
		}
	}

	public Customer find(Long customerId) {
		return (Customer) getSession()
			.createCriteria(Customer.class)
			.add(Expression.eq("id", customerId))
			.uniqueResult();
	}
}
