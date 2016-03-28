package com.brandomine.tech.common.tile;

import com.brandomine.tech.common.utils.ExtendedPlayer;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBlockCapacitor extends TileEntity{
	
	private int maxStorage;
	public int storedPower;

	@Override
	public void writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
		tag.setInteger("maxStorage", 1000000);
		tag.setInteger("storedPower", this.powerStored(tag));
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
		this.maxStorage = tag.getInteger("maxStorage");
		this.storedPower = tag.getInteger("storedPower");
	}
	
	public int powerStored(NBTTagCompound tag){
		if(storedPower != 0){
			tag.getInteger("storedPower");
		}else{
			tag.setInteger("storedPower", 0);
		}
		return tag.getInteger("storedPower");
	}
	
	@SuppressWarnings("null")
	public void addPower(int amount){
		EntityPlayer player = null;
		ExtendedPlayer props = new ExtendedPlayer(player);
		int currentPower = storedPower;
		int runAmount = amount;
		
		while(runAmount !=0){
			NBTTagCompound tag = null;
			tag.setInteger("storedPower", currentPower + 1);
			props.removePower(1);
			if(tag.getInteger("storedPower") > this.maxStorage){
				tag.setInteger("storedPower", this.maxStorage);
				props.addPower(1);
			}
			runAmount = runAmount - 1;
		}
	}
}
