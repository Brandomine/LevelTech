package com.brandomine.tech.common.network;

import com.brandomine.tech.common.lib.Reference;
import com.brandomine.tech.common.network.AbstractMessage.AbstractClientMessage;
import com.brandomine.tech.common.utils.SyncPlayerPropsMessage;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class PacketHandler{
	private static byte packetId = 0;
	private static final SimpleNetworkWrapper dispatcher = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);
	
	public static final void registerPackets(){
		registerMessage(SyncPlayerPropsMessage.class);
	}
	
	private static final <T extends AbstractMessage<T> & IMessageHandler<T, IMessage>> void registerMessage(Class<T> clazz) {
		if (AbstractMessage.AbstractClientMessage.class.isAssignableFrom(clazz)) {
			PacketHandler.dispatcher.registerMessage(clazz, clazz, packetId++, Side.CLIENT);
		} else if (AbstractClientMessage.class.isAssignableFrom(clazz)) {
			PacketHandler.dispatcher.registerMessage(clazz, clazz, packetId++, Side.SERVER);
		} else {
			// hopefully you didn't forget to extend the right class, or you will get registered on both sides
			PacketHandler.dispatcher.registerMessage(clazz, clazz, packetId, Side.CLIENT);
			PacketHandler.dispatcher.registerMessage(clazz, clazz, packetId++, Side.SERVER);
		}
	}
	
	public static final void sendTo(IMessage message, EntityPlayerMP player) {
		PacketHandler.dispatcher.sendTo(message, player);
	}
	public static void sendToAll(IMessage message) {
		PacketHandler.dispatcher.sendToAll(message);
	}
	public static final void sendToAllAround(IMessage message, NetworkRegistry.TargetPoint point) {
		PacketHandler.dispatcher.sendToAllAround(message, point);
	}
	public static final void sendToDimension(IMessage message, int dimensionId) {
		PacketHandler.dispatcher.sendToDimension(message, dimensionId);
	}
	public static final void sendToServer(IMessage message) {
		PacketHandler.dispatcher.sendToServer(message);
	}

}
