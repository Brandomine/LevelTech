package com.brandomine.tech.common.utils;

import java.io.IOException;

import com.brandomine.tech.common.network.AbstractMessage.AbstractClientMessage;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;

public class SyncPlayerPropsMessage extends AbstractClientMessage<SyncPlayerPropsMessage>{
	private NBTTagCompound data;
	
	public SyncPlayerPropsMessage() {}
	
	public SyncPlayerPropsMessage(EntityPlayer player) {
		data = new NBTTagCompound();
		//ExtendedPlayer.get(player).saveNBTData(data);
	}

	@Override
	protected void read(PacketBuffer buffer) throws IOException {
		data = buffer.readNBTTagCompoundFromBuffer();
	}

	@Override
	protected void write(PacketBuffer buffer) throws IOException {
		buffer.writeNBTTagCompoundToBuffer(data);
	}

	@Override
	public void process(EntityPlayer player, Side side) {
		//ExtendedPlayer.get(player).loadNBTData(data);
	}

	
}
