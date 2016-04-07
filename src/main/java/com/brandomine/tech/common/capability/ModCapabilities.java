package com.brandomine.tech.common.capability;

import com.brandomine.tech.common.capability.leveling.ILevelCapability;
import com.brandomine.tech.common.capability.leveling.LevelCapabilityFactory;
import com.brandomine.tech.common.capability.leveling.LevelStorage;
import com.brandomine.tech.common.capability.power.IPowerCapability;
import com.brandomine.tech.common.capability.power.PowerCapabilityFactory;
import com.brandomine.tech.common.capability.power.PowerStorage;

import net.minecraftforge.common.capabilities.CapabilityManager;

public class ModCapabilities {
	public static void init(){
		CapabilityManager.INSTANCE.register(ILevelCapability.class, new LevelStorage(), new LevelCapabilityFactory());
		CapabilityManager.INSTANCE.register(IPowerCapability.class, new PowerStorage(), new PowerCapabilityFactory());
	}
}
