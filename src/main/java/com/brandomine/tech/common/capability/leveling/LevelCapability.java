package com.brandomine.tech.common.capability.leveling;

public class LevelCapability implements ILevelCapability{
	
	private LevelInfo info;

	@Override
	public LevelInfo getInfo() {
		return info;
	}

	@Override
	public void setInfo(LevelInfo level) {
		info = level;
	}

	@Override
	public String getOwner() {
		return "LEVEL_CAP";
	}

}
