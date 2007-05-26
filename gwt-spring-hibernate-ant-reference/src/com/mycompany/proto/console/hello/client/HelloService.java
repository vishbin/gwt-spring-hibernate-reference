package com.mycompany.proto.console.hello.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public interface HelloService extends RemoteService {

	public static final String SERVICE_URI = "service/helloService";
	
	String sayHello() throws Exception;
	List getCustomers() throws Exception;

	public static class Util {

		public static HelloServiceAsync getInstance() {

			HelloServiceAsync instance = (HelloServiceAsync) GWT
					.create(HelloService.class);
			ServiceDefTarget target = (ServiceDefTarget) instance;
			target.setServiceEntryPoint(GWT.getModuleBaseURL() + SERVICE_URI);
			return instance;
		}
	}

}
