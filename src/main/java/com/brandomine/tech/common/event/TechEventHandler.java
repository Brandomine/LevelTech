package com.brandomine.tech.common.event;

import java.util.Random;

import com.brandomine.tech.common.MainRegistry;
import com.brandomine.tech.common.init.ModItems;
import com.brandomine.tech.common.item.ToolExperiencePickaxe;
import com.brandomine.tech.common.lib.Reference;


import jline.internal.Log;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;

public class TechEventHandler {
	@SubscribeEvent
	public void onDrops(BlockEvent.HarvestDropsEvent event){
		Random r = new Random();
		if (event.getState().getBlock() == Blocks.COAL_ORE){
			//event.getDrops().add(new ItemStack(ModItems.));
			event.setDropChance(1.0F);
		}
	}
}
