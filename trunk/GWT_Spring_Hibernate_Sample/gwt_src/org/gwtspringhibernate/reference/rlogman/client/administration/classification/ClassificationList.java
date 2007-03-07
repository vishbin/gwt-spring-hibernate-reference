package org.gwtspringhibernate.reference.rlogman.client.administration.classification;


import org.gwtspringhibernate.reference.rlogman.client.administration.EntityList;
import org.gwtspringhibernate.reference.rlogman.client.data.Classification;

import com.google.gwt.user.client.ui.FlexTable;


/**
 * Controla la lista de clasificaciones mostrada en una página
 */
public class ClassificationList
		extends EntityList {

	protected void fillTableRow(FlexTable table, int i, Object sourceItem) {
		Classification item = (Classification) sourceItem;
		table.setText(i + 1, 0, String.valueOf(item.getClassificationId()));
		table.setText(i + 1, 1, item.getClassificationName());
		table.setText(i + 1, 2, (item.getMandatory() == 'T') ? "Yes" : "No");
		table.setText(i + 1, 3, (item.getMultiple() == 'T') ? "Yes" : "No");
	}

	protected void fillTableHeader(FlexTable table) {
		table.setText(0, 0, "id");
		table.setText(0, 1, "Name");
		table.setText(0, 2, "Mandatory");
		table.setText(0, 3, "Multiple");
	}

	protected int getListColumnCount() {
		return 4;
	}
}
