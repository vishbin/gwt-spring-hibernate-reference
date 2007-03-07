package org.gwtspringhibernate.reference.rlogman.client.administration;


import org.gwtspringhibernate.reference.rlogman.client.MenuItem;
import org.gwtspringhibernate.reference.rlogman.client.administration.classification.ClassificationDetail;
import org.gwtspringhibernate.reference.rlogman.client.administration.classification.ClassificationList;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.ui.VerticalPanel;


/**
 * Administración de entidades en general
 */
public abstract class EntityAdministration extends MenuItem {
	private static EntityAdministration singleton;

	private EntityList entityList;
	private EntityDetail entityDetail;

	protected abstract MenuItemInfo init();
	protected abstract Class getEntityListClass();
	protected abstract Class getEntityDetailClass();

	public static EntityAdministration get() {
		return singleton;
	}

	public EntityAdministration() {
		singleton = this;
		entityList = (EntityList) createEntityList(
				getEntityListClass());
		entityDetail = (EntityDetail) createEntityDetail(
				getEntityDetailClass());
		entityList.setSize("95%", "100%");
		entityDetail.setSize("95%", "90%");
		VerticalPanel panel = new VerticalPanel();
	    panel.add(entityList);
	    panel.add(entityDetail);
		panel.setSpacing(0);
		panel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);


		initWidget(panel);
		setStyleName("ks-layouts");
	}

	/**
	 * Workaround para combatir el problema de GWT que no acepta
	 * reflection. Se hace para crear una instancia del manejador
	 * del detalle de una entidad 
	 * @param entityDetailClass
	 * @return la instancia de la clase especificada
	 */
	private EntityDetail createEntityDetail(Class entityDetailClass) {
		if (ClassificationDetail.class.equals(entityDetailClass)) {
			return new ClassificationDetail();
		}
		throw new IllegalArgumentException("Invalid EntityDetail class:"
				+ entityDetailClass);
	}

	/**
	 * Workaround para combatir el problema de GWT que no acepta
	 * reflection. Se hace para crear una instancia del manejador
	 * del detalle de la lista de registros de una entidad 
	 * @param entutyListClass
	 * @return la instancia de la clase especificada
	 */
	private EntityList createEntityList(Class entutyListClass) {
		if (ClassificationList.class.equals(entutyListClass)) {
			return new ClassificationList();
		}
		throw new IllegalArgumentException("Invalid EntityList class:"
				+ entutyListClass);
	}


	public void onShow() {
	}

	/**
	 * Muestra un item
	 * @param item
	 */
	public void displayItem(IsSerializable item) {
		  entityDetail.setItem(item);
	}
}
