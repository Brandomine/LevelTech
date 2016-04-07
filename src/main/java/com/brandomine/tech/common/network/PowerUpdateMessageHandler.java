package com.brandomine.tech.common.network;

import com.brandomine.tech.common.MainRegistry;
import com.brandomine.tech.common.capability.power.PowerInfo;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class PowerUpdateMessageHandler implements IMessageHandler<PowerUpdateMessage, IMessage>{
	
	@Override
	public IMessage onMessage(PowerUpdateMessage message, MessageContext ctx) {
		final PowerInfo info = message.toSend;
		if(ctx.side == Side.CLIENT){
			Minecraft.getMinecraft().addScheduledTask(new Runnable(){
				@Override
				public void run(){
					if(Minecraft.getMinecraft().thePlayer.hasCapability(MainRegistry.CAPABILITY_POWER, null)){
						Minecraft.getMinecraft().thePlayer.getCapability(MainRegistry.CAPABILITY_POWER, null).setInfo(info);
					}
				}
			});
		}
		return null;
	}
}
