package com.brandomine.tech.common.capability.power;

import com.brandomine.tech.common.capability.leveling.ILevelCapability;
import com.brandomine.tech.common.capability.leveling.LevelInfo;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class PowerStorage implements IStorage<IPowerCapability>{
	@Override
	public NBTBase writeNBT(Capability<IPowerCapability> capability, IPowerCapability instance, EnumFacing side) {
		NBTTagCompound nbt = new NBTTagCompound();
		if(instance != null && instance.getInfo() != null){
			instance.getInfo().writeToNBT(nbt);
		}
		return nbt;
	}

	@Override
	public void readNBT(Capability<IPowerCapability> capability, IPowerCapability instance, EnumFacing side, NBTBase nbt) {
		if(instance != null && instance.getInfo() == null){
			instance.setInfo(new PowerInfo(0,0,0));
		}
		if(nbt instanceof NBTTagCompound && instance != null && instance.getInfo() != null){
			instance.getInfo().readFromNBT((NBTTagCompound) nbt);
		}
	}
}
