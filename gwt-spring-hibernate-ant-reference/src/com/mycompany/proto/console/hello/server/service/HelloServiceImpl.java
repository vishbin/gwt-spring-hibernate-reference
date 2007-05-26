package com.mycompany.proto.console.hello.server.service;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.mycompany.proto.componenta.ComponentAWorker;
import com.mycompany.proto.componentb.ComponentBWorker;
import com.mycompany.proto.console.hello.client.HelloService;

public class HelloServiceImpl extends RemoteServiceServlet implements HelloService {
	private static final long serialVersionUID = 1L;
	
	private ComponentAWorker aWorker;
	private ComponentBWorker bWorker;

	/**
	 * IOC Setter.
	 * @param worker
	 */
	public void setAWorker(ComponentAWorker worker) {
		aWorker = worker;
	}

	/**
	 * IOC Setter.
	 * @param worker
	 */
	public void setBWorker(ComponentBWorker worker) {
		bWorker = worker;
	}

	public String sayHello() throws Exception {
		return "Hello World!" + test();
	}

	private String test() {
		StringBuffer buffer = new StringBuffer();
		if (aWorker != null) {
			buffer.append(aWorker.doSomething()).append(",");
		} else {
			buffer.append("aWorker==null,");
		}
		if (bWorker != null) {
			buffer.append(bWorker.doSomething()).append(",");
		} else {
			buffer.append("bWorker==null,");
		}
		return buffer.toString();
	}
}
