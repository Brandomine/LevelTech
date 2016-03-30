package com.brandomine.tech.client.hud;

import org.lwjgl.opengl.GL11;

import com.brandomine.tech.common.capability.leveling.LevelCapability;
import com.brandomine.tech.common.capability.leveling.LevelInfo;
import com.brandomine.tech.common.capability.leveling.LevelStorage;
import com.brandomine.tech.common.capability.leveling.PlayerLevelInfo;

import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.common.capabilities.Capability;

@SideOnly(Side.CLIENT)
public class HUDLevelBar extends Gui{
	private FontRenderer FR;
	private Minecraft mc;

	public HUDLevelBar(Minecraft mc){
		super();
		this.mc = Minecraft.getMinecraft();
	}

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onRenderExperienceBar(RenderGameOverlayEvent.Post event){
		if(event.isCancelable() || event.getType() != ElementType.EXPERIENCE){
			return;
		}

		ScaledResolution scaled = new ScaledResolution(mc);
		LevelInfo level = PlayerLevelInfo.getLevelInfo(mc.thePlayer);

		float xPos = 0.002F;
		float yPos = 0.24F;
		
		int x = (int)(xPos * scaled.getScaledWidth()) * 4;
		int y = (int)(yPos * scaled.getScaledHeight()) * 4;

		// if(level.getXp() != 0 || level.getLevel() > 1){
		this.mc.fontRendererObj.drawString("Current Level: " + level.getLevel(), x, y, 0xffFFFFFF);
		this.mc.fontRendererObj.drawString("XP: " + level.getXp() + " / " + level.getMaxXp(), x, y - 10 , 0xffFFFFFF);
		//this.mc.fontRendererObj.drawStringWithShadow("Power: " + props.getCurrentPower() + " / " + props.getMaxPower(), x, y - 20 , 0xffFFFFFF);
		// }
	}
}
