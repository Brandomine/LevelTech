package com.brandomine.tech.common.proxy;

import com.brandomine.tech.client.hud.HUDLevelBar;
import com.brandomine.tech.common.init.ModItems;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy{
	private final Minecraft mc = Minecraft.getMinecraft();
	
	@Override
	public void preInit(){
		ModItems.initModels();
	}
	
	@Override
	public void registerRenderers(){
		MinecraftForge.EVENT_BUS.register(new HUDLevelBar(mc));
	}
}
