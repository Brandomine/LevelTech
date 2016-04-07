package com.brandomine.tech.common.capability.power;

public interface IPowerCapability {
	PowerInfo getInfo();
	void setInfo(PowerInfo power);
	String getOwner();
}
