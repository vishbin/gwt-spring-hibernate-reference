package com.mycompany.proto.console.hello.client;

import java.util.Iterator;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class HelloModule implements EntryPoint {
	
	private Button createJohnButton = new Button("Click me");
	private Label createJohnLabel = new Label();
	private Button refreshButton = new Button("Refresh");
	private FlexTable customersTable = new FlexTable();

	public void onModuleLoad() {
		

		createJohnButton.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				dearJohn();
				showJohns();
			}
		});
		
		refreshButton.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				showJohns();
			}
		});

		RootPanel.get("helloButton").add(createJohnButton);
		RootPanel.get("helloOutput").add(createJohnLabel);
		RootPanel.get("customersRefreshButton").add(refreshButton);
		RootPanel.get("customersTable").add(customersTable);
	}
	
	private void dearJohn() {
		createJohnButton.setEnabled(false);
		HelloServiceAsync instance = HelloService.Util.getInstance();
		instance.sayHello(new AsyncCallback() {

			public void onFailure(Throwable error) {
				Window.alert("Error occured:" + error.toString());
				createJohnButton.setEnabled(true);
			}

			public void onSuccess(Object value) {
				createJohnLabel.setText(value.toString());
				createJohnButton.setEnabled(true);
			}
		});
	}
	
	private void showJohns() {
		refreshButton.setEnabled(false);
		HelloServiceAsync instance = HelloService.Util.getInstance();
		instance.getCustomers(new AsyncCallback() {

			public void onFailure(Throwable error) {
				Window.alert("Error occured:" + error.toString());
				refreshButton.setEnabled(true);
			}

			public void onSuccess(Object value) {
				try {
					updateCustomersTable(customersTable, (List)value);
				} catch (Exception e) {
					Window.alert("Error occured:" + e.getMessage());
				}
				refreshButton.setEnabled(true);
			}
		});
	}
	
	private void updateCustomersTable(FlexTable table, List customers) {
		table.clear();
		int rows = -1;
		table.setHTML(++rows, 0, "ID");
		table.setHTML(rows, 1, "First Name");
		table.setHTML(rows, 2, "Last Name");
		for (Iterator iter = customers.iterator(); iter.hasNext();) {
			CustomerInterface customer = (CustomerInterface) iter.next();
			table.setHTML(++rows, 0, customer.getId().toString());
			table.setHTML(rows, 1, customer.getFirstName());
			table.setHTML(rows, 2, customer.getLastName());
		}
	}
}
