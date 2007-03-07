package org.gwtspringhibernate.reference.rlogman.client;


import java.util.HashMap;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;


public class ClientHelper {
	
	
	private static HashMap yesNoTextValues = new HashMap(2);
	
	static {
		yesNoTextValues.put(new Character('?'), "--");
		yesNoTextValues.put(new Character('Y'), "Yes");
		yesNoTextValues.put(new Character('N'), "No");
	}
	
	public static void fillYesNoList(ListBox lista) {
		lista.addItem((String) yesNoTextValues.get(new Character('?')));
		lista.addItem((String) yesNoTextValues.get(new Character('Y')));
		lista.addItem((String) yesNoTextValues.get(new Character('N')));
	}

	public static char getBooleanValueFromYesNoList(ListBox lista) {
		return yesNoTextValues.get(new Character('Y')).equals(lista.getItemText(lista.getSelectedIndex()))
				? 'Y' : 'N';
	}

	public static HTML createStandardLabel(String label) {
		HTML html = new HTML(label);
		html.setStyleName("bw-formLabel");
		html.setWidth("150px");
		return html;
	}

	public static TextBox addTextBox(String label, FlexTable formTable) {
		int fila = formTable.insertRow(formTable.getRowCount());
		formTable.addCell(fila);
		formTable.addCell(fila);
		formTable.setWidget(fila, 0, createStandardLabel(label));
		TextBox textbox = new TextBox();
		formTable.setWidget(fila, 1, textbox);
		return textbox;
	}

	public static TextArea addTextArea(String label, FlexTable formTable) {
		int fila = formTable.insertRow(formTable.getRowCount());
		formTable.addCell(fila);
		formTable.addCell(fila);
		TextArea textarea = new TextArea();
		formTable.setWidget(fila, 0, createStandardLabel(label));
		formTable.setWidget(fila, 1, textarea);
		return textarea;
	}

	public static ListBox addYesNoListBox(String label, FlexTable formTable) {
		int fila = formTable.insertRow(formTable.getRowCount());
		formTable.addCell(fila);
		formTable.addCell(fila);
		ListBox lista = new ListBox();
		fillYesNoList(lista);
		formTable.setWidget(fila, 0, createStandardLabel(label));
		formTable.setWidget(fila, 1, lista);
		return lista;
	}

	public static void updateYesNoListBox(ListBox lista, char valor) {
		updateListBox(lista, (String) yesNoTextValues.get(new Character(valor)));
	}

	public static void updateListBox(ListBox lista, String valor) {
		int itemCount = lista.getItemCount();
		for (int i = 0; i < itemCount; i++) {
			if (valor.equals(lista.getItemText(i))) {
				lista.setSelectedIndex(i);
				return;
			}
		}
		lista.setSelectedIndex(-1);
	}

}
