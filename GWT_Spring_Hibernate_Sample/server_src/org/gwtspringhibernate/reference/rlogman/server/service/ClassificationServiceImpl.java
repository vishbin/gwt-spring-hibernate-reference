package org.gwtspringhibernate.reference.rlogman.server.service;


import java.util.ArrayList;
import java.util.List;

import org.gwtspringhibernate.reference.rlogman.client.data.Classification;
import org.gwtspringhibernate.reference.rlogman.client.service.EntityService;
import org.gwtspringhibernate.reference.rlogman.server.GeneralHelper;
import org.gwtspringhibernate.reference.rlogman.server.dao.ClassificationDAO;

import com.google.gwt.user.client.rpc.IsSerializable;


public class ClassificationServiceImpl
		implements EntityService {
    ClassificationDAO dao;

    /**
     * findClassificationById
     *
     * @param id int
     * @return Classification
     */
    public IsSerializable findById(int id) {
    	org.gwtspringhibernate.reference.rlogman.server.data.Classification classification =
    			dao.findClassificationById(id);
    	Classification clientClassification = new Classification();
    	GeneralHelper.copyBeanProperties(classification, clientClassification);
    	return clientClassification;
    }

    /**
     * getAllClassifications
     *
     * @return List
     */
    public List getAll() {
        List<org.gwtspringhibernate.reference.rlogman.server.data.Classification> classifications =
        		dao.getAllClassifications();
System.out.println("ya estoy acá; clasificaciones=" + classifications.size());
        List<Classification> clientClassifications = new ArrayList<Classification>();
        Classification clientClassification;
        for (org.gwtspringhibernate.reference.rlogman.server.data.Classification classification : classifications) {
        	clientClassification = new Classification();
        	GeneralHelper.copyBeanProperties(classification, clientClassification);
			clientClassifications.add(clientClassification);
System.out.println(clientClassification.getClassificationName());
		}
        return clientClassifications;
    }

    /**
     * insertClassification
     *
     * @param clientClassification Classification
     */
    public void insert(IsSerializable clientClassification) {
    	org.gwtspringhibernate.reference.rlogman.server.data.Classification classification =
    			new org.gwtspringhibernate.reference.rlogman.server.data.Classification();
    	GeneralHelper.copyBeanProperties(clientClassification, classification);
        dao.insertClassification(classification);
    }

    /**
     * removeClassification
     *
     * @param clientClassification Classification
     */
    public void remove(IsSerializable clientClassification) {
    	org.gwtspringhibernate.reference.rlogman.server.data.Classification classification =
    			new org.gwtspringhibernate.reference.rlogman.server.data.Classification();
    	GeneralHelper.copyBeanProperties(clientClassification, classification);
        dao.removeClassification(classification);
    }

    /**
     * updateClassification
     *
     * @param clientClassification Classification
     */
    public void update(IsSerializable clientClassification) {
    	org.gwtspringhibernate.reference.rlogman.server.data.Classification classification =
    			new org.gwtspringhibernate.reference.rlogman.server.data.Classification();
    	GeneralHelper.copyBeanProperties(clientClassification, classification);
    	dao.updateClassification(classification);
    }

    public void setDAO(ClassificationDAO dao) {
        this.dao = dao;
    }
}
