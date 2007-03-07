package org.gwtspringhibernate.reference.rlogman.client.administration;


import org.gwtspringhibernate.reference.rlogman.client.administration.classification.ClassificationItems;
import org.gwtspringhibernate.reference.rlogman.client.service.EntityService;
import org.gwtspringhibernate.reference.rlogman.client.service.EntityServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;


/**
 * Área con el detalle de la información del ítem
 */
public abstract class EntityDetail
		extends Composite {

	private VerticalPanel panel = new VerticalPanel();

	private VerticalPanel headerPanel = new VerticalPanel();

	private FlowPanel detailTitle = new FlowPanel();
	private Button insertButton = new Button("Create", new ClickListener() {
		public void onClick(Widget sender) {
			entityNameInTitle.setHTML("<< New item >>");
			form.setVisible(true);
			clearCaptureFormOnNew(form);
			commandZone.clear();
			commandZone.add(newRecordCommands);
		}
	});

	protected abstract void clearCaptureFormOnNew(FlexTable forma);

	protected HTML entityNameInTitle = new HTML();

	private FlexTable form = new FlexTable();

	private ScrollPanel scroller = new ScrollPanel(form);

	private HorizontalPanel commandZone = new HorizontalPanel();
	private HorizontalPanel existingRecordCommands = new HorizontalPanel();
	private HorizontalPanel newRecordCommands = new HorizontalPanel();
	
	EntityItems entityItems;


	protected abstract IsSerializable readEntityFromForm();

	private EntityServiceAsync entityService = (EntityServiceAsync) GWT.create(EntityService.class);
	Button create = new Button("Create " + getEntitySingularName(), new ClickListener() {
		public void onClick(Widget sender) {
			IsSerializable entity = readEntityFromForm();

			AsyncCallback callback = new AsyncCallback() {
				public void onSuccess(Object result) {
					GWT.log("Inserted: " + getEntitySingularName(), null);
					entityItems.updateEntityList(EntityItems.getEntityItemCount() + 1);
				}
				public void onFailure(Throwable caught) {
					GWT.log("Error inserting " + getEntitySingularName(), caught);
				}
			};
			entityService.insert(entity, callback);
		}
	});
	Button update = new Button("Update", new ClickListener() {
		public void onClick(Widget sender) {
			IsSerializable entity = readEntityFromForm();

			AsyncCallback callback = new AsyncCallback() {
				public void onSuccess(Object result) {
					GWT.log("Updated: " + getEntitySingularName(), null);
					entityItems.updateEntityList(-1);
				}
				public void onFailure(Throwable caught) {
					GWT.log("Error updating " + getEntitySingularName(), caught);
				}
			};
			entityService.update(entity, callback);
		}
	});
	Button remove = new Button("Delete", new ClickListener() {
		public void onClick(Widget sender) {
			IsSerializable entity = readEntityFromForm();

			AsyncCallback callback = new AsyncCallback() {
				public void onSuccess(Object result) {
					GWT.log("Deleted: " + getEntitySingularName(), null);
					entityItems.updateEntityList(-1);
				}
				public void onFailure(Throwable caught) {
					GWT.log("Error deleting " + getEntitySingularName(), caught);
				}
			};
			entityService.remove(entity, callback);
		}
	});

	public EntityDetail() {
		ServiceDefTarget endpoint = (ServiceDefTarget) entityService;
		endpoint.setServiceEntryPoint(getServiceUrl());
GWT.log("EntryPoint=" + endpoint.getServiceEntryPoint(), null);
	
		entityItems = (EntityItems) createEntityItems(getEntityItemsClass());
		
		detailTitle.add(insertButton);
		detailTitle.add(entityNameInTitle);

		existingRecordCommands.add(update);
		existingRecordCommands.add(remove);

		newRecordCommands.add(create);
		
		headerPanel.add(detailTitle);
		headerPanel.setWidth("100%");

		VerticalPanel innerPanel = new VerticalPanel();
		innerPanel.add(headerPanel);
		innerPanel.add(scroller);

		innerPanel.setCellHeight(scroller, "100%");
		panel.add(innerPanel);
		innerPanel.setSize("100%", "100%");
		form.setSize("100%", "100%");
		form.setVisible(false);
		panel.setSize("100%", "100%");
		scroller.setSize("100%", "150");
		initWidget(panel);

		setStyleName("admin-Detail");
		headerPanel.setStyleName("admin-DetailHeader");
		innerPanel.setStyleName("admin-DetailInner");
		entityNameInTitle.setStyleName("admin-DetailNombre");
		form.setStyleName("admin-DetailBody");

		buildCaptureForm(form);

		int fila = form.insertRow(form.getRowCount());
		form.addCell(fila);
		form.setWidget(fila, 0, commandZone);
		form.getFlexCellFormatter().setColSpan(fila, 0, 2);
	}

	protected abstract void buildCaptureForm(FlexTable forma);

	protected abstract String getEntitySingularName();

	protected abstract String getServiceUrl();

	/**
	 * Workaround para combatir el problema de GWT que no acepta
	 * reflection. Se hace para crear una instancia del manejador del
	 * detalle de la lista en memoria de registros de una entidad 
	 * @param entityItemsClass
	 * @return la instancia de la clase especificada
	 */
	private EntityItems createEntityItems(Class entityItemsClass) {
		if (ClassificationItems.class.equals(entityItemsClass)) {
			return new ClassificationItems();
		}
		throw new IllegalArgumentException("Invalid EntityItems class " + entityItemsClass);
	}

	protected abstract Class getEntityItemsClass();

	public void setItem(IsSerializable item) {
		form.setVisible(true);
		updateFormFields(item);
		commandZone.clear();
		commandZone.add(existingRecordCommands);
	}

	protected abstract void updateFormFields(IsSerializable item);

	/**
	 * Ajusta el tamaño para que se ajuste al ancho del área cliente
	 */
	public void adjustSize(int windowWidth, int windowHeight) {
		int scrollWidth = windowWidth - scroller.getAbsoluteLeft() - 9;
		if (scrollWidth < 1) {
			scrollWidth = 1;
		}

		int scrollHeight = windowHeight - scroller.getAbsoluteTop() - 9;
		if (scrollHeight < 1) {
			scrollHeight = 1;
		}

		scroller.setSize("" + scrollWidth, "" + scrollHeight);
	}
}
