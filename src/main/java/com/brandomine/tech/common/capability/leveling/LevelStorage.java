package com.brandomine.tech.common.capability.leveling;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class LevelStorage implements IStorage<ILevelCapability>{

	@Override
	public NBTBase writeNBT(Capability<ILevelCapability> capability, ILevelCapability instance, EnumFacing side) {
		NBTTagCompound nbt = new NBTTagCompound();
		if(instance != null && instance.getInfo() != null){
			instance.getInfo().writeToNBT(nbt);
		}
		return nbt;
	}

	@Override
	public void readNBT(Capability<ILevelCapability> capability, ILevelCapability instance, EnumFacing side, NBTBase nbt) {
		if(instance != null && instance.getInfo() == null){
			instance.setInfo(new LevelInfo(1,0,20, 0, 3));
		}
		if(nbt instanceof NBTTagCompound && instance != null && instance.getInfo() != null){
			instance.getInfo().readFromNBT((NBTTagCompound) nbt);
		}
	}

}
