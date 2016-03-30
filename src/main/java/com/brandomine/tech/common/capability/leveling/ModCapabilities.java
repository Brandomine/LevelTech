package com.brandomine.tech.common.capability.leveling;

import net.minecraftforge.common.capabilities.CapabilityManager;

public class ModCapabilities {
	public static void init(){
		CapabilityManager.INSTANCE.register(ILevelCapability.class, new LevelStorage(), new LevelCapabilityFactory());
	}
}
