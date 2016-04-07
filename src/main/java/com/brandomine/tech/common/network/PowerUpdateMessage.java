package com.brandomine.tech.common.network;

import com.brandomine.tech.common.capability.power.PowerInfo;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class PowerUpdateMessage implements IMessage{
	@Deprecated
	public PowerUpdateMessage(){
		
	}
	
	PowerInfo toSend = new PowerInfo(0, 0);
	
	public PowerUpdateMessage(PowerInfo toSend){
		this.toSend = toSend;
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		NBTTagCompound nbt = new NBTTagCompound();
		toSend.writeToNBT(nbt);
		ByteBufUtils.writeTag(buf, nbt);
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		NBTTagCompound nbt = ByteBufUtils.readTag(buf);
		toSend.readFromNBT(nbt);
	}
}
