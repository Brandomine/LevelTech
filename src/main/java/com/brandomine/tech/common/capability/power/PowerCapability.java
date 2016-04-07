package com.brandomine.tech.common.capability.power;

import com.brandomine.tech.common.capability.leveling.LevelInfo;

public class PowerCapability implements IPowerCapability{
	private PowerInfo info;
	
	@Override
	public PowerInfo getInfo() {
		return info;
	}

	@Override
	public void setInfo(PowerInfo power) {
		info = power;
	}

	@Override
	public String getOwner() {
		return "power_CAP";
	}
}
