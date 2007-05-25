package com.mycompany.proto.console.hello.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class HelloModule implements EntryPoint {

	public void onModuleLoad() {
		final Button button = new Button("Click me");
		final Label label = new Label();

		button.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				HelloServiceAsync instance = HelloService.Util.getInstance();
				instance.sayHello(new AsyncCallback() {

					public void onFailure(Throwable error) {
						Window.alert("Error occured:" + error.toString());
					}

					public void onSuccess(Object value) {
						label.setText(value.toString());
					}
				});
			}
		});

		RootPanel.get("slot1").add(button);
		RootPanel.get("slot2").add(label);
	}
}
