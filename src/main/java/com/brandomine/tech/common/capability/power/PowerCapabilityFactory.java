package com.brandomine.tech.common.capability.power;

import java.util.concurrent.Callable;

public class PowerCapabilityFactory implements Callable<IPowerCapability> {
	@Override
	public IPowerCapability call() throws Exception {
		return new PowerCapability();
	}
}
