package org.gwtspringhibernate.reference.rlogman.client.administration.classification;


import org.gwtspringhibernate.reference.rlogman.client.ClientHelper;
import org.gwtspringhibernate.reference.rlogman.client.administration.EntityDetail;
import org.gwtspringhibernate.reference.rlogman.client.data.Classification;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;


/**
 * Área con el detalle de la información de la clasificación
 */
public class ClassificationDetail
			extends EntityDetail {

	TextBox id;
	TextBox name;
	TextArea description;
	ListBox mandatory;
	ListBox multiple;

	protected void buildCaptureForm(FlexTable form) {
		id = ClientHelper.addTextBox("Id", form);
		name = ClientHelper.addTextBox("Name", form);
		description = ClientHelper.addTextArea("Description", form);
		mandatory = ClientHelper.addYesNoListBox("Is mandatory?", form);
		multiple = ClientHelper.addYesNoListBox("Allows multiple values?", form);
	}

	protected void clearCaptureFormOnNew(FlexTable form) {
		id.setText("");
		name.setText("");
		description.setText("");
		mandatory.setSelectedIndex(0);
		multiple.setSelectedIndex(0);
	}

	protected void updateFormFields(IsSerializable sourceItem) {
		Classification item = (Classification) sourceItem;
		entityNameInTitle.setHTML(item.getClassificationName());
		id.setText(String.valueOf(item.getClassificationId()));
		name.setText(item.getClassificationName());
		description.setText(item.getDescription());
		ClientHelper.updateYesNoListBox(multiple, item.getMultiple());
		ClientHelper.updateYesNoListBox(mandatory, item.getMandatory());
	}

	protected IsSerializable readEntityFromForm() {
		Classification clasificacion = new Classification();
		clasificacion.setClassificationId(Short.parseShort(id.getText()));
		clasificacion.setClassificationName(name.getText());
		clasificacion.setDescription(description.getText());
		clasificacion.setMandatory(ClientHelper.getBooleanValueFromYesNoList(mandatory));
		clasificacion.setMultiple(ClientHelper.getBooleanValueFromYesNoList(multiple));
		return clasificacion;
	}

	protected Class getEntityItemsClass() {
		return ClassificationItems.class;
	}

	protected String getEntitySingularName() {
		return "classification";
	}

	protected String getServiceUrl() {
		return "http://localhost:800/GWT_Spring_Hibernate_Sample/service/classificationService";
	}
}
