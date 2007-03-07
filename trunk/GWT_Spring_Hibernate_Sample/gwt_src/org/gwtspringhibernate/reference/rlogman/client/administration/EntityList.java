package org.gwtspringhibernate.reference.rlogman.client.administration;


import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SourcesTableEvents;
import com.google.gwt.user.client.ui.TableListener;
import com.google.gwt.user.client.ui.Widget;


/**
 * Controla la lista de elementos mostrada en una página
 */
public abstract class EntityList extends Composite implements TableListener,
		ClickListener, EntityListUpdatedListener {
	

	private static final int VISIBLE_ITEM_COUNT = 6;

	private HTML countLabel = new HTML();

	private HTML previousButton = new HTML(
			"<a href='javascript:;'>&lt; back</a>", true);

	private HTML nextButton = new HTML(
			"<a href='javascript:;'>next &gt;</a>", true);

	private int startIndex, selectedRow = -1;

	private FlexTable table = new FlexTable();

	private HorizontalPanel navBar = new HorizontalPanel();

	public EntityList() {
		// Se prepara la tabla
		table.setCellSpacing(0);
		table.setCellPadding(2);
		table.setWidth("100%");

		// Se enlazan los eventos
		table.addTableListener(this);
		previousButton.addClickListener(this);
		nextButton.addClickListener(this);
		EntityItems.addEntityListUpdatedListener(this);

		// Se crea la barra de navegación y se pone arriba a la derecha
		HorizontalPanel innerNavBar = new HorizontalPanel();
		innerNavBar.setStyleName("admin-ListNavBar");
		innerNavBar.setSpacing(8);
		innerNavBar.add(previousButton);
		innerNavBar.add(countLabel);
		innerNavBar.add(nextButton);

		navBar.setHorizontalAlignment(HorizontalPanel.ALIGN_RIGHT);
		navBar.add(innerNavBar);
		navBar.setWidth("100%");

		initWidget(table);
		setStyleName("admin-List");

		initTable();
		update();
	}

	public void onCellClicked(SourcesTableEvents sender, int row, int cell) {
		update();
		// Selecciona la fila a la que le dieron click (-1 para contar la fila de encabezados).
		if (row > 0) {
			selectRow(row - 1);
		}
	}

	public void onClick(Widget sender) {
		if (sender == nextButton) {
			// Página siguiente
			startIndex += VISIBLE_ITEM_COUNT;
			if (startIndex >= EntityItems.getEntityItemCount()) {
				startIndex -= VISIBLE_ITEM_COUNT;
			} else {
				styleRow(selectedRow, false);
				selectedRow = -1;
				update();
			}
		} else if (sender == previousButton) {
			// Página anterior
			startIndex -= VISIBLE_ITEM_COUNT;
			if (startIndex < 0) {
				startIndex = 0;
			} else {
				styleRow(selectedRow, false);
				selectedRow = -1;
				update();
			}
		}
	}

	/**
	 * Inicializa la tabla para que contenga suficientes filas para una
	 * página llena de ítems.
	 */
	private void initTable() {
		// Fila de encabezados
		fillTableHeader(table);
		table.setWidget(0, 4, navBar);
		table.getRowFormatter().setStyleName(0, "admin-ListHeader");

		// Resto de filas
		for (int i = 0; i < VISIBLE_ITEM_COUNT; ++i) {
			int numCols = getListColumnCount();
			for (int j = 0; j <= numCols; j++) {
				table.setText(i + 1, j, "");
				table.getCellFormatter().setWordWrap(i + 1, j, false);
			}
			table.getFlexCellFormatter().setColSpan(i + 1, numCols, 2);
		}
	}

	protected abstract int getListColumnCount();

	protected abstract void fillTableHeader(FlexTable table);

	/**
	 * Selecciona la fila, relativa a la página actual.
	 * @param row la fila a seleccionar
	 */
	private void selectRow(int row) {
		// Cuando una fila (diferente a la primera, que es de encabezados)
		// se selecciona, muestra su detalle asociado
		IsSerializable item = EntityItems.getEntityItem(startIndex + row);
		if (item == null) {
			return;
		}

		styleRow(selectedRow, false);
		styleRow(row, true);

		selectedRow = row;
		EntityAdministration.get().displayItem(item);
	}

	private void styleRow(int row, boolean selected) {
		if (row != -1) {
			if (selected) {
				table.getRowFormatter().addStyleName(row + 1,
						"admin-SelectedRow");
			} else {
				table.getRowFormatter().removeStyleName(row + 1,
						"admin-SelectedRow");
			}
		}
	}

	private void update() {
		// Actualiza los botones de siguiente anterior
		int count = EntityItems.getEntityItemCount();
		int max = startIndex + VISIBLE_ITEM_COUNT;
		if (max > count) {
			max = count;
		}

		previousButton.setVisible(startIndex != 0);
		nextButton.setVisible(startIndex + VISIBLE_ITEM_COUNT < count);
		countLabel
				.setText("" + (startIndex + 1) + " to " + max + " of " + count);

		// Muestra los ítems seleccionados
		int i = 0;
		for (; i < VISIBLE_ITEM_COUNT; ++i) {
			// No lee más allá del final de la página
			if (startIndex + i >= EntityItems.getEntityItemCount()) {
				break;
			}

			Object item = EntityItems
					.getEntityItem(startIndex + i);

			// Le adiciona una fila a la tabla y luego fija los valores
			// para sus celdas
			fillTableRow(table, i, item);
		}

		// Borra las filas restantes (por ejemplo, en la última página no siempre hay registros suficientes
		for (; i < VISIBLE_ITEM_COUNT; ++i) {
			for (int j = 0; j <= getListColumnCount(); j++) {
				table.setHTML(i + 1, j, "&nbsp;");
			}
		}

		// Selecciona la primera fila si no hay ninguna seleccionada
		if (selectedRow == -1) {
			selectRow(0);
		}
	}

	protected abstract void fillTableRow(FlexTable table, int i, Object item);

	public void onEntityListUpdated(int idx) {
		if (idx > 0) {
			startIndex = ((EntityItems.getEntityItemCount() - 1) / VISIBLE_ITEM_COUNT)
					* VISIBLE_ITEM_COUNT;
			if (startIndex < 0) {
				startIndex = 0;
			}
			selectRow(idx - startIndex - 1);
		}
		update();
	}
}
