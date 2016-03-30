package com.brandomine.tech.common.network;

import com.brandomine.tech.common.MainRegistry;
import com.brandomine.tech.common.capability.leveling.LevelInfo;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class LevelUpdateMessageHandler implements IMessageHandler<LevelUpdateMessage, IMessage>{

	@Override
	public IMessage onMessage(LevelUpdateMessage message, MessageContext ctx) {
		final LevelInfo info = message.toSend;
		if(ctx.side == Side.CLIENT){
			Minecraft.getMinecraft().addScheduledTask(new Runnable(){
				@Override
				public void run(){
					if(Minecraft.getMinecraft().thePlayer.hasCapability(MainRegistry.CAPABILITY_LEVEL, null)){
						Minecraft.getMinecraft().thePlayer.getCapability(MainRegistry.CAPABILITY_LEVEL, null).setInfo(info);
					}
				}
			});
		}
		return null;
	}

}
