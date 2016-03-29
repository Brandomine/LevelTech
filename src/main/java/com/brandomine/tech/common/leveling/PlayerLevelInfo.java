package com.brandomine.tech.common.leveling;

import com.brandomine.tech.common.MainRegistry;
import com.brandomine.tech.common.leveling.capabilities.ILevelCapability;
import com.brandomine.tech.common.network.LevelUpdateMessage;
import com.brandomine.tech.common.network.TechNetwork;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class PlayerLevelInfo {
	public static void saveLevelInfo(EntityPlayer player, LevelInfo level){
		if(player.hasCapability(MainRegistry.CAPABILITY_LEVEL, null)){
			player.getCapability(MainRegistry.CAPABILITY_LEVEL, null).setInfo(level);
		}
		if(player instanceof EntityPlayerMP){
			TechNetwork.networkWrapper.sendTo(new LevelUpdateMessage(level), (EntityPlayerMP) player);
		}
	}
	
	public static LevelInfo getLevelInfo(EntityPlayer player){
		final ILevelCapability cap = player.getCapability(MainRegistry.CAPABILITY_LEVEL, null);
		LevelInfo level = null;
		if(cap != null){
			level = cap.getInfo();
		}
		return level;
	}
	
	public static void modifyLevelInfo(EntityPlayer player, LevelInfo level){
		LevelInfo info = getLevelInfo(player);
		saveLevelInfo(player, new LevelInfo(level.getLevel() + info.getLevel(), level.getMaxXp() + info.getMaxXp(), info.getXp() + level.getXp()));
	}

}
