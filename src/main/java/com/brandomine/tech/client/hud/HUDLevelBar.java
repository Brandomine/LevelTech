package com.brandomine.tech.client.hud;

import org.lwjgl.opengl.GL11;

import com.brandomine.tech.common.capability.leveling.LevelCapability;
import com.brandomine.tech.common.capability.leveling.LevelInfo;
import com.brandomine.tech.common.capability.leveling.LevelStorage;
import com.brandomine.tech.common.capability.leveling.PlayerLevelInfo;
import com.brandomine.tech.common.capability.power.PlayerPowerInfo;
import com.brandomine.tech.common.capability.power.PowerInfo;
import com.brandomine.tech.common.utils.DoubleConverter;

import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraft.client.renderer.*;

@SideOnly(Side.CLIENT)
public class HUDLevelBar extends Gui{
	private FontRenderer FR;
	private Minecraft mc;
	
	public HUDLevelBar(Minecraft mc){
		super();
		this.mc = Minecraft.getMinecraft();
	}

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onRenderExperienceBar(RenderGameOverlayEvent.Post event) throws InterruptedException{
		if(event.isCancelable() || event.getType() != ElementType.EXPERIENCE){
			return;
		}
		
		ScaledResolution scaled = new ScaledResolution(mc);
		LevelInfo level = PlayerLevelInfo.getLevelInfo(mc.thePlayer);
		PowerInfo power = PlayerPowerInfo.getPowerInfo(mc.thePlayer);
		TickEvent ticks = new TickEvent.RenderTickEvent(null, zLevel);
		DoubleConverter converter = new DoubleConverter();

		float xPos = 0.002F;
		float yPos = 0.24F;
		
		int x = (int)(xPos * scaled.getScaledWidth()) * 4;
		int y = (int)(yPos * scaled.getScaledHeight()) * 4;
		
		this.mc.fontRendererObj.drawString("Current Level: " + converter.doubleToString(level.getLevel()), x, y, 0xffFFFFFF);
		this.mc.fontRendererObj.drawString("XP: " + converter.doubleToString(level.getXp()) + " / " + converter.doubleToString(level.getMaxXp()), x, y - 10 , 0xffFFFFFF);
		this.mc.fontRendererObj.drawString("Power: " + converter.doubleToString(power.getPower()) + " / " + converter.doubleToString(power.getMaxPower()), x, y - 20 , 0xffFFFFFF);
	}
}
