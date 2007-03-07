package org.gwtspringhibernate.reference.rlogman.client;


import org.gwtspringhibernate.reference.rlogman.client.MenuItem.MenuItemInfo;
import org.gwtspringhibernate.reference.rlogman.client.administration.Administration;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.HistoryListener;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;


/**
 * Centro de operaciones de GWT_Spring_Hibernate_Sample
 */
public class GWT_Spring_Hibernate_Sample
		implements EntryPoint, HistoryListener {

	private MenuItemInfo curInfo;
	private MenuItem curItem;
	private HTML description = new HTML();
	private Menu list = new Menu();
	private DockPanel panel = new DockPanel();
	private DockPanel mainContainer;

	public void onHistoryChanged(String token) {
		// Encuentra el módulo asociado con el contexto de historia. Si
		// se encuentra alguno, lo muestra (es posible que no se encuentre,
		// por ejemplo si el usuario escribe mal un URL o al inicio,
		// cuando el contexto es "").
		MenuItemInfo info = list.find(token);
		if (info == null) {
			showInfo();
			return;
		}
		show(info, false);
	}

	public void onModuleLoad() {
		// Carga los módulos de biz watch
		loadItems();

		// Pone la lista de opciones a la izquierda y el área de trabajo
		// a la derecha.
		mainContainer = new DockPanel();
		mainContainer.setStyleName("ks-Sink");

		VerticalPanel vp = new VerticalPanel();
		vp.setWidth("100%");
		vp.add(description);
		vp.add(mainContainer);

		description.setStyleName("ks-Home");

		panel.add(list, DockPanel.WEST);
		panel.add(vp, DockPanel.CENTER);

		panel.setCellVerticalAlignment(list, HasAlignment.ALIGN_TOP);
		panel.setCellWidth(vp, "100%");

		History.addHistoryListener(this);
		RootPanel.get().add(panel);

		// Muestra la pantalla inicial
		String initToken = History.getToken();
		if (initToken.length() > 0)
			onHistoryChanged(initToken);
		else
			showInfo();
	}

	public void show(MenuItemInfo info, boolean affectHistory) {
		// Se evita el reprocesamiento de la opción si es la actual
		if (info == curInfo)
			return;
		curInfo = info;

		// Quita el módulo anterior
		if (curItem != null) {
			curItem.onHide();
			mainContainer.remove(curItem);
		}

		// Muestra el módulo y su texto descriptivo.
		curItem = info.getInstance();
		list.setItemSelection(info.getName());
		description.setHTML(info.getDescription());

		// Si affectHistory is verdadero, crea una entrada en la pila de
		// historia. Esto provoca una llamada a onHistoryChanged(). Ésta
		// invocará show() nuevamente, pero no pasará nada por el
		// control que se hace al comienzo.
		if (affectHistory)
			History.newItem(info.getName());

		// Se muestra el nuevo módulo
		mainContainer.add(curItem, DockPanel.CENTER);
		mainContainer.setCellWidth(curItem, "100%");
		mainContainer.setCellHeight(curItem, "100%");
		mainContainer.setCellVerticalAlignment(curItem, DockPanel.ALIGN_TOP);
		curItem.onShow();
	}

	/**
	 * Se agregan los módulos a la lista.
	 */
	private void loadItems() {
		list.addItem(Home.init());
		list.addItem(Administration.init());
		list.addItem(Menus.init());
	}

	private void showInfo() {
		show(list.find("Home"), false);
	}
}
