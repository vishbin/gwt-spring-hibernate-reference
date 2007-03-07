package org.gwtspringhibernate.reference.rlogman.client.administration;


import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.gwtspringhibernate.reference.rlogman.client.service.EntityService;
import org.gwtspringhibernate.reference.rlogman.client.service.EntityServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.rpc.ServiceDefTarget;


/**
 * Maneja la lista de registros disponibles para administrar
 */
public abstract class EntityItems {

	private static List items = new Vector();
	private static Vector listUpdatedListeners = new Vector();

	protected EntityItems() {
		updateEntityList(-1);
	}

	void updateEntityList(final int idx) {
		EntityServiceAsync entityService = (EntityServiceAsync) GWT.create(EntityService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) entityService;
		endpoint.setServiceEntryPoint(getServiceUrl());
GWT.log("EntryPoint=" + endpoint.getServiceEntryPoint(), null);
		AsyncCallback callback = new AsyncCallback() {
			public void onSuccess(Object result) {
				items = (List) result;
				GWT.log("Cargados los elementos", null);
				notifyEntityListUpdateListeners(idx);
			}
			public void onFailure(Throwable t) {
				GWT.log("No se pudo cargar la lista de elementos", t);
			}
		};
		entityService.getAll(callback);
	}

	protected abstract String getServiceUrl();

	public static int getEntityItemCount() {
		return items.size();
	}

	public static IsSerializable getEntityItem(int index) {
		if (index >= items.size())
			return null;
		return (IsSerializable) items.get(index);
	}

	public static void addEntityListUpdatedListener(EntityListUpdatedListener lsnr) {
		listUpdatedListeners.add(lsnr);
	}

	private static void notifyEntityListUpdateListeners(int idx) {
		for (Iterator i = listUpdatedListeners.iterator(); i.hasNext();) {
			EntityListUpdatedListener lsnr = (EntityListUpdatedListener) i.next();
			lsnr.onEntityListUpdated(idx);
		}
	}
}
