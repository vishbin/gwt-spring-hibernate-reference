package com.mycompany.proto.console.hello.client;

import com.google.gwt.user.client.rpc.IsSerializable;

public interface CustomerInterface extends IsSerializable {
	Long getId();
	void setId(Long id);
	String getFirstName();
	void setFirstName(String firstName);
	String getLastName();
	void setLastName(String lastName);
}
