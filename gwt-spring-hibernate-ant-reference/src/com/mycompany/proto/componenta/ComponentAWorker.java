package com.mycompany.proto.componenta;

import com.mycompany.proto.log.Log;

public class ComponentAWorker {
	public String doSomething() {
		Log.trace(this, "doSomething()");
		return "Okay, I did something (A).";
	}
}
