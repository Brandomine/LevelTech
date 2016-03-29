package com.brandomine.tech.common;

import com.brandomine.tech.client.hud.HUDLevelBar;
import com.brandomine.tech.common.event.TechEventHandler;
import com.brandomine.tech.common.init.ModBlocks;
import com.brandomine.tech.common.init.ModItems;
import com.brandomine.tech.common.leveling.capabilities.ILevelCapability;
import com.brandomine.tech.common.lib.Reference;
import com.brandomine.tech.common.proxy.CommonProxy;

import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID, version = Reference.MOD_VERSION)
public class MainRegistry
{
	@Instance
	public static MainRegistry instance;
	
	@SidedProxy(clientSide = "com.brandomine.tech.common.proxy.ClientProxy", serverSide = "com.brandomine.tech.common.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	@CapabilityInject(ILevelCapability.class)
	public static final Capability<ILevelCapability> CAPABILITY_LEVEL = null;
	
	@EventHandler
	public void preinit(FMLPreInitializationEvent event){
		ModItems.init();
		proxy.preInit();
		//ModBlocks.init();
		}
	
    @EventHandler
    public void init(FMLInitializationEvent event){
    	proxy.registerRenderers();
    	MinecraftForge.EVENT_BUS.register(new TechEventHandler());
    }
    
    @EventHandler
    public void postinit(FMLPostInitializationEvent event){
    	
    }
}
