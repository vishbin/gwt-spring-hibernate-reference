package org.gwtspringhibernate.reference.rlogman.client.service;


import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.rpc.RemoteService;


public interface EntityService
		extends RemoteService {
	IsSerializable findById(int id);
	void insert(IsSerializable entity);
	void update(IsSerializable entity);
	List getAll();
	void remove(IsSerializable entity);
}
