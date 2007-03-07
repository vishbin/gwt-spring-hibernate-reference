/**
 * Creada 15/06/2006
 */
package org.gwtspringhibernate.reference.rlogman.server.dao;

import java.util.List;

import org.gwtspringhibernate.reference.rlogman.server.data.Classification;



/**
 * @author rlopez
 *
 */
public interface ClassificationDAO {
	Classification findClassificationById(int id);
	void insertClassification(Classification classification);
	void updateClassification(Classification classification);
	List getAllClassifications();
	void removeClassification(Classification classification);
}
