package com.brandomine.tech.common.event;

import java.util.Random;

import com.brandomine.tech.common.MainRegistry;
import com.brandomine.tech.common.capability.leveling.ILevelCapability;
import com.brandomine.tech.common.capability.leveling.LevelInfo;
import com.brandomine.tech.common.capability.leveling.PlayerLevelInfo;
import com.brandomine.tech.common.capability.power.IPowerCapability;
import com.brandomine.tech.common.capability.power.PlayerPowerInfo;
import com.brandomine.tech.common.capability.power.PowerInfo;
import com.brandomine.tech.common.lib.Reference;
import com.brandomine.tech.common.network.LevelUpdateMessage;
import com.brandomine.tech.common.network.PowerUpdateMessage;
import com.brandomine.tech.common.network.TechNetwork;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;

public class TechEventHandler {
	
	@SubscribeEvent
	public void entityConstructing(EntityConstructing event){
		if(event.getEntity() instanceof EntityPlayerMP){
			TechNetwork.networkWrapper.sendTo(new LevelUpdateMessage(PlayerLevelInfo.getLevelInfo((EntityPlayer) event.getEntity())), (EntityPlayerMP) event.getEntity());
			TechNetwork.networkWrapper.sendTo(new PowerUpdateMessage(PlayerPowerInfo.getPowerInfo((EntityPlayer) event.getEntity())), (EntityPlayerMP) event.getEntity());
		}
	}
	
	@SubscribeEvent
	public void onEntityContructing(AttachCapabilitiesEvent.Entity event){
		if(!(event.getEntity() instanceof EntityPlayer)){
			return;
		}
		
		class ProviderLevel implements ILevelCapability, ICapabilitySerializable<NBTTagCompound> {

			ProviderLevel(LevelInfo info) {
				this.info = info;
			}

			private LevelInfo info;

			@Override
			public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
				return MainRegistry.CAPABILITY_LEVEL != null && capability == MainRegistry.CAPABILITY_LEVEL;
			}

			@Override
			public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
				if (MainRegistry.CAPABILITY_LEVEL != null && capability == MainRegistry.CAPABILITY_LEVEL)
					return MainRegistry.CAPABILITY_LEVEL.cast(this);
				return null;
			}

			@Override
			public String getOwner() {
				return "LEVEL_CAP";
			}

			@Override
			public LevelInfo getInfo() {
				return info;
			}

			@Override
			public void setInfo(LevelInfo mana) {
				this.info = mana;
			}

			@Override
			public NBTTagCompound serializeNBT() {
				NBTTagCompound nbt = new NBTTagCompound();
				this.info.writeToNBT(nbt);
				return nbt;
			}

			@Override
			public void deserializeNBT(NBTTagCompound nbt) {
				this.info.readFromNBT(nbt);
			}
		}
		
		class ProviderPower implements IPowerCapability, ICapabilitySerializable<NBTTagCompound> {

			ProviderPower(PowerInfo info) {
				this.info = info;
			}

			private PowerInfo info;

			@Override
			public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
				return MainRegistry.CAPABILITY_POWER != null && capability == MainRegistry.CAPABILITY_POWER;
			}

			@Override
			public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
				if (MainRegistry.CAPABILITY_POWER != null && capability == MainRegistry.CAPABILITY_POWER)
					return MainRegistry.CAPABILITY_POWER.cast(this);
				return null;
			}

			@Override
			public String getOwner() {
				return "POWER_CAP";
			}

			@Override
			public PowerInfo getInfo() {
				return info;
			}

			@Override
			public void setInfo(PowerInfo info) {
				this.info = info;
			}

			@Override
			public NBTTagCompound serializeNBT() {
				NBTTagCompound nbt = new NBTTagCompound();
				this.info.writeToNBT(nbt);
				return nbt;
			}

			@Override
			public void deserializeNBT(NBTTagCompound nbt) {
				this.info.readFromNBT(nbt);
			}
		}
		event.addCapability(new ResourceLocation(Reference.MODID + ":LevelCap"), new ProviderLevel(new LevelInfo(0, 20)));
		event.addCapability(new ResourceLocation(Reference.MODID + ":PowerCap"), new ProviderPower(new PowerInfo(0, 3)));
	}
	
	@SubscribeEvent
	public void onPlayerCloned(PlayerEvent.Clone e) {
		PlayerLevelInfo.saveLevelInfo(e.getEntityPlayer(), PlayerLevelInfo.getLevelInfo(e.getOriginal()));
		PlayerPowerInfo.savePowerInfo(e.getEntityPlayer(), PlayerPowerInfo.getPowerInfo(e.getOriginal()));
	}
	
	@SubscribeEvent
	public void joinWorld(EntityJoinWorldEvent e) {
		if (!e.getWorld().isRemote && e.getEntity() instanceof EntityPlayer) {
			TechNetwork.networkWrapper.sendTo(new LevelUpdateMessage(PlayerLevelInfo.getLevelInfo((EntityPlayer) e.getEntity())), (EntityPlayerMP) e.getEntity());
			TechNetwork.networkWrapper.sendTo(new PowerUpdateMessage(PlayerPowerInfo.getPowerInfo((EntityPlayer) e.getEntity())), (EntityPlayerMP) e.getEntity());
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
