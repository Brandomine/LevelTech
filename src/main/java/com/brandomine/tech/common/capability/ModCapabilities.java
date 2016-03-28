package com.brandomine.tech.common.capability;

import java.util.concurrent.Callable;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class ModCapabilities {
	@CapabilityInject(ILevelHandler.class)
	public static Capability<ILevelHandler> LEVEL_HANDLER_CAPABILITY = null;
	
	private static boolean enabled = false;
	
	public static void enable(){
		if(!enabled){
			enabled = true;
			CapabilityManager.INSTANCE.register(ILevelHandler.class, new Storage(), new Callable<ILevelHandler>(){
				@Override
				public ILevelHandler call() throws Exception{
					return new LevelBuffer();
				}
			});
		}
	}
	
	private static class Storage implements Capability.IStorage<ILevelHandler>{

		@Override
		public NBTBase writeNBT(Capability<ILevelHandler> capability, ILevelHandler instance, EnumFacing side) {
			if(!(instance instanceof ILevelPersist)){
				return null;
			}
				
			NBTTagCompound tag = new NBTTagCompound();
			ILevelPersist get = (ILevelPersist)instance;
			
			int level = get.getLevel();
			int xp = get.getXp();
			int maxXp = get.getMaxXp();
			
			if(level < 0){
				level = 1;
			}
			
			
			
			tag.setInteger("Level", level);
			return tag;
		}

		@Override
		public void readNBT(Capability<ILevelHandler> capability, ILevelHandler instance, EnumFacing side, NBTBase nbt) {
			if(!(instance instanceof ILevelPersist))
				return;
			
			NBTTagCompound tag = (NBTTagCompound) nbt;
			ILevelPersist get = (ILevelPersist) instance;
			
			int level = get.getLevel();
			int xp = get.getXp();
			int maxXp = get.getMaxXp();
			
			if(maxXp < 20){
				level = 20;
			}
			
			if(xp == maxXp){
				xp = 0;
				level ++;
				maxXp += 5;
			}
			
			get.setXp(xp);
			get.setLevel(level);
			get.setMaxXp(maxXp);
		}
		
	}
}
