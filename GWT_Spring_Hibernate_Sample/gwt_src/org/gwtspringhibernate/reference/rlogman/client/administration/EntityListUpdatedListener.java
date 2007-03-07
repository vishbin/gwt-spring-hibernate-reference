package org.gwtspringhibernate.reference.rlogman.client.administration;


public interface EntityListUpdatedListener {

	/**
	 * Se dispara cuando la lista de items de la entidad se actualizó.
	 * @param idx índice del ítem a seleccionar
	 */
	void onEntityListUpdated(int idx);
}
