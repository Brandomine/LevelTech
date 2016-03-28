package com.brandomine.tech.common.capability;

public interface ILevelHandler {
	int getLevel();
	int getXp();
	int getMaxXp();
	int addXp(int value);
}
