package com.brandomine.tech.common.event;

import java.util.Random;

import com.brandomine.tech.common.leveling.PlayerLevelInfo;
import com.brandomine.tech.common.network.PacketHandler;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;

public class TechEventHandler {
	@SubscribeEvent
	public void entityConstructing(EntityConstructing event){
		if(event.getEntity() instanceof EntityPlayerMP){
			PacketHandler.sendTo(new SyncPlayerPropsMessage(PlayerLevelInfo.getLevelInfo(EntityPlayer) event.getEntity), (EntityPlayerMP) event.getEntity());
		}
	}
	
	@SubscribeEvent
	public void onDrops(BlockEvent.HarvestDropsEvent event){
		Random r = new Random();
		if (event.getState().getBlock() == Blocks.coal_ore){
	//		event.getDrops().add(new ItemStack(ModItems.itemPowerUpRefill));
			event.setDropChance(1.0F);
		} 
	}
}
