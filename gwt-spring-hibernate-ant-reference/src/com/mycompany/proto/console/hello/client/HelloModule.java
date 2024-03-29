package com.mycompany.proto.console.hello.client;

import java.util.Iterator;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.mycompany.proto.console.hello.client.log.Log;

public class HelloModule implements EntryPoint {
	
	private Button createCustomerButton = new Button("Create Customer");
	private Label statusLabel = new Label();
	private Button refreshButton = new Button("Refresh");
	private TextBox firstNameTextBox = new TextBox();
	private TextBox lastNameTextBox = new TextBox();
	
	private DockPanel tableWrapper = new DockPanel();
	
	public void onModuleLoad() {
		Log.setLevelToTrace("com.mycompany.proto.console.hello.client");
		firstNameTextBox.setTitle("First Name");
		firstNameTextBox.setName("firstName");
		lastNameTextBox.setTitle("Last Name");
		lastNameTextBox.setName("lastName");
		lastNameTextBox.addKeyboardListener(createCreateCustomerEnterListener());

		createCustomerButton.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				createCustomer();
				showCustomers();
			}
		});
		
		refreshButton.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				showCustomers();
			}
		});
		
		VerticalPanel panel = new VerticalPanel();
		panel.add(createCustomerButton);
		panel.add(statusLabel);
		panel.add(refreshButton);
		panel.add(firstNameTextBox);
		panel.add(lastNameTextBox);
		panel.add(tableWrapper);
		
		RootPanel.get().add(panel);
		
		//Window.alert("Starting Logging test...");
		Log.trace(this, "TRACE MESSAGE!");
		Log.debug(this, "DEBUG MESSAGE!");
		Log.info(this, "INFO MESSAGE!");
		Log.warn(this, "WARN MESSAGE!");
		Log.error(this, "ERROR MESSAGE!");
		Log.fatal(this, "FATAL MESSAGE!");
		Log.fatal(this, "FATAL WITH EXCEPTION", new RuntimeException("Example Runtime Exception!"));
		//Window.alert("Logging test concluded.");
		
		showCustomers();
	}
	
	private KeyboardListener createCreateCustomerEnterListener() {
		return new KeyboardListener() {
			public void onKeyDown(Widget sender, char keyCode, int modifiers) { }
			public void onKeyPress(Widget sender, char keyCode, int modifiers) {
				if ((keyCode == 13) && (modifiers == 0)) {
					createCustomer();
				}
			}
			public void onKeyUp(Widget sender, char keyCode, int modifiers) { }
		};
	}

	private void createCustomer() {
		createCustomerButton.setEnabled(false);
		CustomerServiceAsync instance = CustomerService.Util.getInstance();
		instance.createCustomer(firstNameTextBox.getText(), lastNameTextBox.getText(), new AsyncCallback() {
			public void onFailure(Throwable caught) {
				Window.alert("Error occured:" + caught.toString());
				createCustomerButton.setEnabled(true);
			}
			public void onSuccess(Object result) {
				statusLabel.setText("Success!");
				createCustomerButton.setEnabled(true);
				showCustomers();
			}
		});
	}
	
	private void showCustomers() {
		refreshButton.setEnabled(false);
		CustomerServiceAsync instance = CustomerService.Util.getInstance();
		instance.getCustomers(new AsyncCallback() {

			public void onFailure(Throwable error) {
				Window.alert("Error occured:" + error.toString());
				refreshButton.setEnabled(true);
			}

			public void onSuccess(Object value) {
				try {
					rebuildCustomersTable((List)value);
				} catch (Exception e) {
					Window.alert("Error occured:" + e.getMessage());
				}
				refreshButton.setEnabled(true);
			}
		});
	}
	
	/**
	 * @gwt.typeArgs customers <com.mycompany.proto.console.hello.client.CustomerAdaptor>
	 */
	private void rebuildCustomersTable(List customers) {
		tableWrapper.clear();
		FlexTable table = new FlexTable();
		int rows = -1;
		table.setHTML(++rows, 0, "ID");
		table.setHTML(rows, 1, "First Name");
		table.setHTML(rows, 2, "Last Name");
		table.setHTML(rows, 3, "Delete?");
		for (Iterator iter = customers.iterator(); iter.hasNext();) {
			CustomerAdaptor customer = (CustomerAdaptor) iter.next();
			table.setHTML(++rows, 0, customer.getId().toString());
			table.setHTML(rows, 1, customer.getFirstName());
			table.setHTML(rows, 2, customer.getLastName());
			table.setWidget(rows, 3, new CustomerDeleteButton(customer));
		}
		tableWrapper.add(table, DockPanel.CENTER);
	}
	
	private class CustomerDeleteButton extends Button {
		
		private CustomerAdaptor customer; 

		public CustomerDeleteButton(CustomerAdaptor customer) {
			super("Delete");
			this.customer = customer;
			addClickListener(createClickListener());
		}

		private ClickListener createClickListener() {
			return new ClickListener() {
				public void onClick(Widget sender) {
					handleClick();
				}
			};
		}
		
		private void handleClick() {
			setEnabled(false);
			CustomerServiceAsync instance = CustomerService.Util.getInstance();
			instance.deleteCustomer(customer, new AsyncCallback() {
				public void onFailure(Throwable caught) {
					Window.alert("Error occured:" + caught.toString());
					setEnabled(true);
				}

				public void onSuccess(Object result) {
					setText("Deleted!");
				}
			});
		}
	}
}
