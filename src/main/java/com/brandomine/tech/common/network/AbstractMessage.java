package com.brandomine.tech.common.network;

import java.io.IOException;

import com.brandomine.tech.common.MainRegistry;
import com.google.common.base.Throwables;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;

public abstract class AbstractMessage <T extends AbstractMessage<T>> implements IMessage, IMessageHandler <T, IMessage>{
	protected abstract void read(PacketBuffer buffer) throws IOException;
	protected abstract void write(PacketBuffer buffer) throws IOException;
	public abstract void process(EntityPlayer player, Side side);
	
	protected boolean isValidOnSide(Side side) {
		return true; 
	}
	
	protected boolean requiresMainThread() {
		return true;
	}
	
	@Override
	public void fromBytes(ByteBuf buffer) {
		try {
			read(new PacketBuffer(buffer));
		} catch (IOException e) {
			throw Throwables.propagate(e);
		}
	}
	
	@Override
	public void toBytes(ByteBuf buffer) {
		try {
			write(new PacketBuffer(buffer));
		} catch (IOException e) {
			throw Throwables.propagate(e);
		}
	}
	
	@Override
	public final IMessage onMessage(T msg, MessageContext ctx) {
		if (!msg.isValidOnSide(ctx.side)) {
			throw new RuntimeException("Invalid side " + ctx.side.name() + " for " + msg.getClass().getSimpleName());
		} 
		return null;
	}
	
	
	public static abstract class AbstractClientMessage<T extends AbstractMessage<T>> extends AbstractMessage<T> {
		@Override
		protected final boolean isValidOnSide(Side side) {
			return side.isClient();
		}
		
	public static abstract class AbstractServerMessage<T extends AbstractMessage<T>> extends AbstractMessage<T> {
		@Override
		protected final boolean isValidOnSide(Side side) {
			return side.isServer();
			}
		}
	}
}
