package com.brandomine.tech.common.capability.power;

import com.brandomine.tech.common.MainRegistry;
import com.brandomine.tech.common.capability.leveling.ILevelCapability;
import com.brandomine.tech.common.capability.leveling.LevelInfo;
import com.brandomine.tech.common.network.LevelUpdateMessage;
import com.brandomine.tech.common.network.PowerUpdateMessage;
import com.brandomine.tech.common.network.TechNetwork;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class PlayerPowerInfo {
	public static void savePowerInfo(EntityPlayer player, PowerInfo power){
		if(player.hasCapability(MainRegistry.CAPABILITY_POWER, null)){
			player.getCapability(MainRegistry.CAPABILITY_POWER, null).setInfo(power);
		}
		if(player instanceof EntityPlayerMP){
			TechNetwork.networkWrapper.sendTo(new PowerUpdateMessage(power), (EntityPlayerMP) player);
		}
	}
	
	public static PowerInfo getPowerInfo(EntityPlayer player){
		final IPowerCapability cap = player.getCapability(MainRegistry.CAPABILITY_POWER, null);
		PowerInfo power = null;
		if(cap != null){
			power = cap.getInfo();
		}
		return power;
	}
	
	public static void modifyPowerInfo(EntityPlayer player, PowerInfo power){
		PowerInfo info = getPowerInfo(player);
		savePowerInfo(player, new PowerInfo(power.getPower() + info.getPower(), power.getMaxPower() + info.getMaxPower(), power.getEnderPower() + info.getEnderPower()));
	}
}
