package com.brandomine.tech.common.event;

import java.util.Random;

import com.brandomine.tech.common.init.ModItems;
import com.brandomine.tech.common.lib.Names.Items;
import com.brandomine.tech.common.network.PacketHandler;
import com.brandomine.tech.common.utils.SyncPlayerPropsMessage;

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
	public void onEntityConstructing(EntityConstructing event) {
	//	if (event.getEntity() instanceof EntityPlayer) {
	//		if (ExtendedPlayer.get((EntityPlayer) event.getEntity()) == null)
	//			ExtendedPlayer.register((EntityPlayer) event.getEntity());
	//	}
	}

	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event) {
		if (event.getEntity() instanceof EntityPlayer && !event.getEntity().worldObj.isRemote) {
			PacketHandler.sendTo(new SyncPlayerPropsMessage((EntityPlayer) event.getEntity()), (EntityPlayerMP) event.getEntity());
		}
	}

	@SubscribeEvent
	public void onClonePlayer(PlayerEvent.Clone event) {
	//	ExtendedPlayer.get(event.getEntityPlayer()).copy(ExtendedPlayer.get(event.getOriginal()));
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
