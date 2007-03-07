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

import java.util.ArrayList;

import org.gwtspringhibernate.reference.rlogman.client.MenuItem.MenuItemInfo;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * The left panel that contains all of the sinks, along with a short description
 * of each.
 */
public class Menu extends Composite {

	private VerticalPanel list = new VerticalPanel();

	private ArrayList items = new ArrayList();

	private int selectedItem = -1;

	public Menu() {
		initWidget(list);
		setStyleName("ks-List");
	}

	public void addItem(final MenuItemInfo info) {
		String name = info.getName();
		Hyperlink link = new Hyperlink(name, name);
		link.setStyleName("ks-SinkItem");

		list.add(link);
		items.add(info);
	}

	public MenuItemInfo find(String itemName) {
		for (int i = 0; i < items.size(); ++i) {
			MenuItemInfo info = (MenuItemInfo) items.get(i);
			if (info.getName().equals(itemName))
				return info;
		}

		return null;
	}

	public void setItemSelection(String name) {
		if (selectedItem != -1)
			list.getWidget(selectedItem)
					.removeStyleName("ks-SinkItem-selected");

		for (int i = 0; i < items.size(); ++i) {
			MenuItemInfo info = (MenuItemInfo) items.get(i);
			if (info.getName().equals(name)) {
				selectedItem = i;
				list.getWidget(selectedItem).addStyleName(
						"ks-SinkItem-selected");
				return;
			}
		}
	}
}
