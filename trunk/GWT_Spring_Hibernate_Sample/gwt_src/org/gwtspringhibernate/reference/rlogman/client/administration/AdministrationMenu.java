package org.gwtspringhibernate.reference.rlogman.client.administration;


import java.util.ArrayList;

import org.gwtspringhibernate.reference.rlogman.client.MenuItem.MenuItemInfo;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TabPanel;


/**
 * Las pestañas del módulo de administración
 */
public class AdministrationMenu extends Composite {

	private TabPanel pestagnas = new TabPanel();

	private ArrayList items = new ArrayList();

	public AdministrationMenu() {
		pestagnas.setSize("100%", "100%");
		initWidget(pestagnas);
//		setStyleName("ks-List");
	}

	public void addItem(EntityAdministration entidad) {
		MenuItemInfo info = entidad.init();
		pestagnas.add(info.getInstance(), info.getName());
		pestagnas.selectTab(0);
		items.add(info);
	}

	public MenuItemInfo find(String itemName) {
		for (int i = 0; i < items.size(); ++i) {
			MenuItemInfo info = (MenuItemInfo) items.get(i);
			if (info.getName().equals(itemName))
				return info;
		}

		return null;
	}

}
