package com.brandomine.tech.common.leveling.capabilities;

import com.brandomine.tech.common.leveling.LevelInfo;

public interface ILevelCapability {
		LevelInfo getInfo();
		void setInfo(LevelInfo level);
	String getOwner();
}
