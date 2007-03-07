package org.gwtspringhibernate.reference.rlogman.client.service;


import java.util.List;

import org.gwtspringhibernate.reference.rlogman.client.data.Classification;

import com.google.gwt.user.client.rpc.RemoteService;


public interface ClassificationService
		extends RemoteService {
	Classification findClassificationById(int id);
	void insertClassification(Classification classification);
	void updateClassification(Classification classification);
	List getAllClassifications();
	void removeClassification(Classification classification);
}
