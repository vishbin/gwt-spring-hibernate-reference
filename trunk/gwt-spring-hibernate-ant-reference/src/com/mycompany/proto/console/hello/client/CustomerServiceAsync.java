package com.mycompany.proto.console.hello.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CustomerServiceAsync {

	void getCustomers(AsyncCallback callback);
	void createCustomer(String firstName, String lastName, AsyncCallback callback);
	void deleteCustomer(Long customerId, AsyncCallback callback);
}
