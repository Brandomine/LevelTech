package com.brandomine.tech.common.network;

import com.brandomine.tech.common.lib.Reference;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class TechNetwork {
	public static final SimpleNetworkWrapper networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);
	
	private static int discriminatorCount = 0;
	
	private static void init(){
		networkWrapper.registerMessage(LevelUpdateMessageHandler.class, LevelUpdateMessage.class, discriminatorCount++, Side.CLIENT);
	}
}
