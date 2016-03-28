package com.brandomine.tech.client.hud;

import org.lwjgl.opengl.GL11;

import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

@SideOnly(Side.CLIENT)
public class HUDLevelBar extends Gui{
	private Minecraft mc;
	private FontRenderer FR;

	public HUDLevelBar(Minecraft mc){
		super();
		this.mc = mc;
	}
	
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onRenderExperienceBar(RenderGameOverlayEvent event){
		if(event.isCancelable() || event.getType() != ElementType.EXPERIENCE){
			return;
		}
		
		ScaledResolution scaled = new ScaledResolution(mc);
		
		//if(props == null || props.getMaxXP() == 0){
			return;
		//}
		
		//float xPos = 0.002F;
		//float yPos = 0.24F;
		
		//float expAmount = props.getCurrentXP();
		//float expToNext = props.getXPtoNext();
		
		
		//int amount = Math.max((int) (182 *  (1 - (double)(expAmount) / expToNext)), 0);
		
		//int x = (int)(xPos * scaled.getScaledWidth()) * 4;
       // int y = (int)(yPos * scaled.getScaledHeight()) * 4;
       
       // if(props.getCurrentXP() != 0 || props.getCurrentLevel() > 1){
       // 	this.mc.fontRendererObj.drawStringWithShadow("Current Level: " + props.getCurrentLevel(), x, y, 0xffFFFFFF);
       // 	this.mc.fontRendererObj.drawStringWithShadow("XP: " + props.getCurrentXP() + " / " + props.getMaxXP(), x, y - 10 , 0xffFFFFFF);
       //	this.mc.fontRendererObj.drawStringWithShadow("Power: " + props.getCurrentPower() + " / " + props.getMaxPower(), x, y - 20 , 0xffFFFFFF);
       // }
		
		
		}
}
