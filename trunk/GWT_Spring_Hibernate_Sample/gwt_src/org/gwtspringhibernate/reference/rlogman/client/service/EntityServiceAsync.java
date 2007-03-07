package org.gwtspringhibernate.reference.rlogman.client.service;


import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.rpc.RemoteService;


public interface EntityServiceAsync
		extends RemoteService {
	void findById(int id, AsyncCallback callback);
	void insert(IsSerializable entity, AsyncCallback callback);
	void update(IsSerializable entity, AsyncCallback callback);
	void getAll(AsyncCallback callback);
	void remove(IsSerializable entity, AsyncCallback callback);
}

