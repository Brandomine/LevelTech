package com.brandomine.tech.common.capability.leveling;

import java.util.concurrent.Callable;

public class LevelCapabilityFactory implements Callable<ILevelCapability>{

	@Override
	public ILevelCapability call() throws Exception {
		return new LevelCapability();
	}

}
