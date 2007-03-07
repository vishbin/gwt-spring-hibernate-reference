package org.gwtspringhibernate.reference.rlogman.server;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class GeneralHelper {

	protected final static Log logger = LogFactory.getLog(GeneralHelper.class);

	public static void copyBeanProperties(Object source, Object target) {
		String propertyName = "";
		try {
			Class sourceClass = source.getClass();
			Method [] sourceMethods = sourceClass.getMethods();
			int estimatedProperties = sourceMethods.length /2;
			Map<String, Object> sourceValues = new HashMap<String, Object>(estimatedProperties);
			String methodName;
			for (int i = 0; i < sourceMethods.length; i++) {
				methodName = sourceMethods[i].getName();
				
				if ((methodName.length() > 3)
						&& (sourceMethods[i].getParameterTypes().length == 0)
						&& methodName.startsWith("get")
						&& Character.isUpperCase(methodName.charAt(3))) {
					propertyName = getPropertyName(methodName);
					sourceValues.put(propertyName, sourceMethods[i].invoke(source, (Object []) null));
				}
			}
			
			
			Class targetClass = target.getClass();
			Method [] targetMethods = targetClass.getMethods();
			Object [] valor = new Object[1];
			for (int i = 0; i < targetMethods.length; i++) {
				methodName = targetMethods[i].getName();
				
				if ((methodName.length() > 3)
						&& (targetMethods[i].getParameterTypes().length == 1)
						&& methodName.startsWith("set")
						&& Character.isUpperCase(methodName.charAt(3))) {
					propertyName = getPropertyName(methodName);
					if (sourceValues.containsKey(propertyName)) {
						valor[0] = sourceValues.get(propertyName);
						if (valor[0] instanceof Date) {
							valor[0] = new Date(((Date) valor[0]).getTime());
						}
						targetMethods[i].invoke(target, valor);
					}
					
				}
			}
		} catch (Exception e) {
			throw new java.lang.RuntimeException(
					"Could not copy properties from a bean of type "
					+ source.getClass().getName()
					+ " to another of type "
					+ target.getClass().getName()
					+ "; failed with property '" + propertyName + "'", e);
		}
	}

	private static String getPropertyName(String methodName) {
		return (String.valueOf(methodName.charAt(3)).toLowerCase() +
				methodName.substring(4));
	}

}
