package com.mycompany.proto.componenta;

import static junit.framework.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ComponentAWorkerFixture {
	
	private ComponentAWorker worker;

	@Before
	public void before() {
		worker = new ComponentAWorker();
	}
	
	@Test
	public void doSomething() {
		String value = worker.doSomething();
		assertEquals("Okay, I did something (A).", value);
	}
}
