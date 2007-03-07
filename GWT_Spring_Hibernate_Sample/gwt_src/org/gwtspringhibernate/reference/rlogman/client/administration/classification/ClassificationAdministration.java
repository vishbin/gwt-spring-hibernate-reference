package org.gwtspringhibernate.reference.rlogman.client.administration.classification;


import org.gwtspringhibernate.reference.rlogman.client.MenuItem;
import org.gwtspringhibernate.reference.rlogman.client.administration.EntityAdministration;


/**
 * Administración de clasificaciones
 */
public class ClassificationAdministration
		extends EntityAdministration {
	private static ClassificationAdministration singleton;

	public static EntityAdministration get() {
		return singleton;
	}

	protected Class getEntityListClass() {
		return ClassificationList.class;
	}

	protected Class getEntityDetailClass() {
		return ClassificationDetail.class;
	}

	protected MenuItemInfo init() {
		return new MenuItemInfo("Classifications", "This is the module that manages CRUD operations of an Oracle table. This could be configured with Hibernate to work with any other mainstream DB.") {
			public MenuItem createInstance() {
				return new ClassificationAdministration();
			}
		};
	}
}
