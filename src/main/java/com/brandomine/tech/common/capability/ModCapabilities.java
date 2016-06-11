package com.brandomine.tech.common.capability;

import com.brandomine.tech.common.capability.leveling.ILevelCapability;
import com.brandomine.tech.common.capability.leveling.LevelCapabilityFactory;
import com.brandomine.tech.common.capability.leveling.LevelStorage;

import net.minecraftforge.common.capabilities.CapabilityManager;

public class ModCapabilities {
	public static void init(){
		CapabilityManager.INSTANCE.register(ILevelCapability.class, new LevelStorage(), new LevelCapabilityFactory());
	}
}
