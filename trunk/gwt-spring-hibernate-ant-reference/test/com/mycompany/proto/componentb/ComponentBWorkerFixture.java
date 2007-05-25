package com.mycompany.proto.componentb;

import static junit.framework.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ComponentBWorkerFixture {
	private ComponentBWorker worker;

	@Before
	public void before() {
		worker = new ComponentBWorker();
	}
	
	@Test
	public void doSomething() {
		String value = worker.doSomething();
		assertEquals("Okay, I did something (B).", value);
	}
}
