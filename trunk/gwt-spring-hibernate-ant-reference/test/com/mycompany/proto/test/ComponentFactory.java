package com.mycompany.proto.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mycompany.proto.domain.dao.CustomerDao;
import com.mycompany.proto.log.Log;

public class ComponentFactory {

	private static final String[] BEAN_DEFINITIONS = new String[] {
		"applicationContext.xml",
		"applicationContext-jdbc.xml",
	    "applicationContext-dao.xml",
		"applicationContext-services.xml"
	};
	
    private static ComponentFactory instance;
    private ApplicationContext applicationContext;

    /**
     * Retrieves an instance of the ComponentFactory.
     * 
     * @return instance
     */
    public static ComponentFactory getInstance() {
        if (instance == null) {
            synchronized (ComponentFactory.class) {
                if (instance == null) {
                    instance = new ComponentFactory();
                }
            }
        }
        return instance;
    }
    
    /**
     * @param selector The selector for loading the BeanFactoryReference
     */
    private ComponentFactory() {
        Log.info(this, "Initializing spring context.");
        applicationContext = new ClassPathXmlApplicationContext(BEAN_DEFINITIONS);
        Log.info(this, "Done initializing spring application context.");
    }
    
    /**
     * Retrieves a pre-configured component.
     * @param beanName name of component
     * @return component
     */
    public Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }
    
    public CustomerDao getCustomerDao() {
    	return (CustomerDao) getBean("customerDao");
    }
}
