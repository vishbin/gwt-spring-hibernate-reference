package com.mycompany.proto.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.CompareToBuilder;

@Entity
@Table(name = "customers")
public class Customer implements Serializable, Comparable {
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	@Column(name = "customer_id")
	private Long id;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "first_name")
	private String firstName;
	
	public Customer() { }
	
	public Customer(String firstName, String lastName) {
		setFirstName(firstName);
		setLastName(lastName);
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String toString() {
		return "[" + id + "," + lastName + "," + firstName + "]";
	}
	
	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return id.hashCode();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public boolean equals(Object other) {
		Customer that = (Customer) other;
		return this.id == that.id;
	}

	public int compareTo(Customer that) {
		return CompareToBuilder.reflectionCompare(this, that);
	}

	public int compareTo(Object other) {
		return compareTo((Customer)other);
	}
}
