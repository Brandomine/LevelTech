package com.brandomine.tech.client.gui;

import org.lwjgl.opengl.GL11;

import com.brandomine.tech.common.utils.ExtendedPlayer;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class GUIMenu extends GuiScreen{
	int guiWidth = 248;
	int guiHeight = 166;
	GuiButton button1;
	GuiButton button2;
	GuiButton button3;
	GuiButton button4;
	GuiButton button5;
	
	@Override
	public void drawScreen(int x, int y, float ticks){
		int guiX = (width - guiWidth) / 2;
		int guiY = (height - guiHeight) / 2;
		GL11.glColor4f(1, 1, 1, 1);
		mc.renderEngine.bindTexture(new ResourceLocation("tech:/textures/gui/demo_background.png"));
		drawTexturedModalRect(guiX, guiY, 0, 0, guiWidth, guiHeight);
		fontRendererObj.drawString("Level Tech v. 0.0.1 BETA", guiX + 63, guiY + 5, 0x000000);
		super.drawScreen(x, y, ticks);
	}
	
	@Override
	public void initGui(){
		ExtendedPlayer props = ExtendedPlayer.get(this.mc.thePlayer);
		int guiX = (width - guiWidth) / 2;
		int guiY = (height - guiHeight) / 2;
		buttonList.clear();
		buttonList.add(button1 = new GuiButton(0, guiX + 10, guiY + 15, 228, 20, "Item Info"));
		buttonList.add(button2 = new GuiButton(1, guiX + 10, guiY + 42, 228, 20, "Research"));
		buttonList.add(button3 = new GuiButton(2, guiX + 10, guiY + 69, 228, 20, "Effects"));
		buttonList.add(button4 = new GuiButton(3, guiX + 10, guiY + 96, 228, 20, "Sword Effects"));
		buttonList.add(button5 = new GuiButton(4, guiX + 10, guiY + 123, 228, 20, "Pickaxe Effects"));
		//Research Button Control
		if(props.getCurrentLevel() >= 5){
			button2.enabled = true;
		}else{
			button2.enabled = false;
			button2.displayString = "Locked: Level 5";
		}
		//Effects Button Control
		if(props.getCurrentLevel() >= 10 && props.didPlayerResearchEffects() == 1){
			button3.enabled = true;
		}else{
			button3.enabled = false;
			if(props.getCurrentLevel() < 10){
				button3.displayString = ("Locked: Level 10.");
			}else if(props.didPlayerResearchEffects() == 0){
				button3.displayString = "Research Locked: Effects";
			}
		}
		//Sword Effects Button Control
		
		//Pickaxe Effects Button Control
		super.initGui();
	}
	
	@Override
	protected void actionPerformed(GuiButton button){
		switch(button.id){
		//Effects
		case 0:
			return;
		//Research
		case 1:
			return;
		//Item Info
		case 2:
			return;
		case 3:
			return;
		case 4:
			return;
		}
	}
}
