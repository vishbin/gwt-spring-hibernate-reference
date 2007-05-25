package com.mycompany.proto.componentb;

import com.mycompany.proto.log.Log;

public class ComponentBWorker {

	public String doSomething() {
		Log.trace(this, "doSomething()");
		return "Okay, I did something (B).";
	}
}
