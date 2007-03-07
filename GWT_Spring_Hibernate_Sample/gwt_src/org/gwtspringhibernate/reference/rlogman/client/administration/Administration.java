package org.gwtspringhibernate.reference.rlogman.client.administration;

import org.gwtspringhibernate.reference.rlogman.client.MenuItem;
import org.gwtspringhibernate.reference.rlogman.client.administration.classification.ClassificationAdministration;


/**
 * Controla los módulos de administración de GWT_Spring_Hibernate_Sample
 */
public class Administration
		extends MenuItem {

	public static MenuItemInfo init() {
		return new MenuItemInfo(
				"Administration",
				"Table administration. This module shows integration with Hibernate.") {
			public MenuItem createInstance() {
				return new Administration();
			}
		};
	}

	private AdministrationMenu list = new AdministrationMenu();

	public Administration() {
		list.addItem(new ClassificationAdministration());
		initWidget(list);
	}

	public void onShow() {
	}
}
