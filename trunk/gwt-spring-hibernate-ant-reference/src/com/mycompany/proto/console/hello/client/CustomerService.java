package com.mycompany.proto.console.hello.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public interface CustomerService extends RemoteService {

	public static final String SERVICE_URI = "service/customerService";
	
	List getCustomers() throws Exception;
	CustomerInterface createCustomer(String firstName, String lastName);
	void deleteCustomer(CustomerAdaptor customer);

	public static class Util {

		public static CustomerServiceAsync getInstance() {
			CustomerServiceAsync instance = (CustomerServiceAsync) GWT.create(CustomerService.class);
			ServiceDefTarget target = (ServiceDefTarget) instance;
			target.setServiceEntryPoint(GWT.getModuleBaseURL() + SERVICE_URI);
			return instance;
		}
	}
}
