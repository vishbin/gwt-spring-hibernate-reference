package org.gwtspringhibernate.reference.rlogman.client.administration.classification;


import org.gwtspringhibernate.reference.rlogman.client.administration.EntityItems;


/**
 * Maneja la lista de clasificaciones disponibles para administrar
 */
public class ClassificationItems
		extends EntityItems {

	protected String getServiceUrl() {
		return "http://localhost:8588/GWT_Spring_Hibernate_Sample/service/classificationService";
	}
}
