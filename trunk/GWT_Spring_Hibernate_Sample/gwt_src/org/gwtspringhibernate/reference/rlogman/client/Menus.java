/*
 * Copyright 2006 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.gwtspringhibernate.reference.rlogman.client;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.MenuBar;

/**
 * Demonstrates {@link com.google.gwt.user.client.ui.MenuBar} and
 * {@link com.google.gwt.user.client.ui.MenuItem}.
 */
public class Menus extends MenuItem implements Command {

	public static MenuItemInfo init() {
		return new MenuItemInfo("Menus", "Menu examples.") {
			public MenuItem createInstance() {
				return new Menus();
			}
		};
	}

	private MenuBar menu = new MenuBar();

	public Menus() {
		MenuBar subMenu = new MenuBar(true);
		subMenu.addItem("<code>Code</code>", true, this);
		subMenu.addItem("<strike>Strikethrough</strike>", true, this);
		subMenu.addItem("<u>Underline</u>", true, this);

		MenuBar menu0 = new MenuBar(true);
		menu0.addItem("<b>Bold</b>", true, this);
		menu0.addItem("<i>Italics</i>", true, this);
		menu0.addItem("More &#187;", true, subMenu);
		MenuBar menu1 = new MenuBar(true);
		menu1
				.addItem("<font color='#FF0000'><b>Apple</b></font>", true,
						this);
		menu1.addItem("<font color='#FFFF00'><b>Banana</b></font>", true, this);
		menu1.addItem("<font color='#FFFFFF'><b>Coconut</b></font>", true, this);
		menu1.addItem("<font color='#8B4513'><b>Strawbery</b></font>", true, this);
		MenuBar menu2 = new MenuBar(true);
		menu2.addItem("Diatriba", this);
		menu2.addItem("Consuetudinario", this);

		menu
				.addItem(new com.google.gwt.user.client.ui.MenuItem("Style",
						menu0));
		menu
				.addItem(new com.google.gwt.user.client.ui.MenuItem("Fruit",
						menu1));
		menu.addItem(new com.google.gwt.user.client.ui.MenuItem("Spanish",
				menu2));

		menu.setWidth("100%");

		initWidget(menu);
	}

	public void execute() {
		Window.alert("Please select a menu option.");
	}

	public void onShow() {
	}
}
