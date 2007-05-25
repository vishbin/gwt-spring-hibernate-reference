package com.mycompany.proto.test;

public class TestUtils {

	public static void deleteAllDatabaseData() {
		ComponentFactory.getInstance().getCustomerDao().deleteAll();
	}
}
