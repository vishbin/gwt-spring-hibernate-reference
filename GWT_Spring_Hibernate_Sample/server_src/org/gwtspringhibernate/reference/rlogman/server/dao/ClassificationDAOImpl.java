/**
 * Creada 15/06/2006
 */
package org.gwtspringhibernate.reference.rlogman.server.dao;


import java.util.List;

import org.gwtspringhibernate.reference.rlogman.server.data.Classification;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;




public class ClassificationDAOImpl
		extends HibernateDaoSupport
		implements ClassificationDAO {

	public Classification findClassificationById(int id) {
		List list=getHibernateTemplate().find(
				"from Classification where id=?",id);
		return (Classification) list.get(0);
	}

	public void insertClassification(Classification classification) {
		getHibernateTemplate().save(classification);
	}

	public void updateClassification(Classification classification) {
		getHibernateTemplate().update(classification);
	}

	public List<Classification> getAllClassifications() {
		List<Classification> loadAll = getHibernateTemplate().loadAll(Classification.class);
		return loadAll;
	}

	public void removeClassification(Classification classification) {
		getHibernateTemplate().delete(classification);
	}

}
