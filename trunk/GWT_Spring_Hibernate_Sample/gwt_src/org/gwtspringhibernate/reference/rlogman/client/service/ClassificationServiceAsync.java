package org.gwtspringhibernate.reference.rlogman.client.service;


import org.gwtspringhibernate.reference.rlogman.client.data.Classification;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;


public interface ClassificationServiceAsync
		extends RemoteService {
	void findClassificationById(int id, AsyncCallback callback);
	void insertClassification(Classification classification, AsyncCallback callback);
	void updateClassification(Classification classification, AsyncCallback callback);
	void getAllClassifications(AsyncCallback callback);
	void removeClassification(Classification classification, AsyncCallback callback);
}
