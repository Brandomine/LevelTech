package com.brandomine.tech.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Random;
import com.brandomine.tech.common.network.PacketHandler;
import akka.actor.Props;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.audio.SoundList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;
import net.minecraftforge.client.event.sound.SoundEvent;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedPlayer implements IExtendedEntityProperties{
	public final static String EXT_PROP_NAME = "ExtendedPlayer";
	public final EntityPlayer player;
	public static final int XP_WATCHER = 20;
	public static final int LEVEL_WATCHER = 21;
	public static final int MAX_XP_WATCHER = 22;
	public static final int POWER_WATCHER = 23;
	public static final int MAX_POWER_WATCHER = 24;
	public static final int ENDER_POWER_WATCHER = 25;
	public static final int OPEN_STONE = 26;
	public static final int RESEARCH_EFFECTS = 27;
	public static final int RESEARCH_1 = 28;
	public static final int RESEARCH_2 = 29;
	public static final int RESEARCH_3 = 30;
	public static final int RESEARCH_4 = 31;
	public static final int RESEARCH_5 = 32;
	public static final int RESEARCH_6 = 33;
	public static final int RESEARCH_7 = 34;
	public static final int RESEARCH_8 = 35;
	public static final int RESEARCH_9 = 36;
	public static final int RESEARCH_10 = 37;
	public static final int RESEARCH_11 = 38;
	public static final int EFFECT_1 = 39;
	public static final int EFFECT_2 = 40;
	public static final int EFFECT_3 = 41;
	public static final int EFFECT_4 = 42;
	
	/*
	Research id's
	1 = Power Pickaxe
	2 = Power Regeneration
	3 = Power Experience Pickaxe
	4 = Ender Capacitor
	5 = Glowed Mana Blocks
	6 = Effects Sword
	7 = Beginners Armor
	8 = Power Effects Sword
	9 = Better Power Regeneration
	10 = Middle Tier Armor
	11 = Power Experience Armor
	
	Effect Research id's
	1 = Night Vision
	2 = Power Hungry (Lets player use power instead of food for hunger)
	3 = Haste
	4 = Mining Power (Lets player mine faster
	
	*/
	
	

	public ExtendedPlayer(EntityPlayer player) {
		this.player = player;
		this.player.getDataWatcher().addObject(XP_WATCHER, 0);
		this.player.getDataWatcher().addObject(LEVEL_WATCHER, 1);
		this.player.getDataWatcher().addObject(MAX_XP_WATCHER, 20);
		this.player.getDataWatcher().addObject(POWER_WATCHER, 0);
		this.player.getDataWatcher().addObject(MAX_POWER_WATCHER, 1);
		this.player.getDataWatcher().addObject(ENDER_POWER_WATCHER, 0);
		this.player.getDataWatcher().addObject(OPEN_STONE, 0);
		this.player.getDataWatcher().addObject(RESEARCH_EFFECTS, 0);
		this.player.getDataWatcher().addObject(RESEARCH_1, 0);
		this.player.getDataWatcher().addObject(RESEARCH_2, 0);
		this.player.getDataWatcher().addObject(RESEARCH_3, 0);
		this.player.getDataWatcher().addObject(RESEARCH_4, 0);
		this.player.getDataWatcher().addObject(RESEARCH_5, 0);
		this.player.getDataWatcher().addObject(RESEARCH_6, 0);
		this.player.getDataWatcher().addObject(RESEARCH_7, 0);
		this.player.getDataWatcher().addObject(RESEARCH_8, 0);
		this.player.getDataWatcher().addObject(RESEARCH_9, 0);
		this.player.getDataWatcher().addObject(RESEARCH_10, 0);
		this.player.getDataWatcher().addObject(RESEARCH_11, 0);
	}

	public static final void register(EntityPlayer player) {
		player.registerExtendedProperties(ExtendedPlayer.EXT_PROP_NAME, new ExtendedPlayer(player));
	}

	public static final ExtendedPlayer get(EntityPlayer player) {
		return (ExtendedPlayer) player.getExtendedProperties(EXT_PROP_NAME);
	}

	public void copy(ExtendedPlayer props) {
		player.getDataWatcher().updateObject(XP_WATCHER, props.getCurrentXP());
		player.getDataWatcher().updateObject(LEVEL_WATCHER, props.getCurrentLevel());
		player.getDataWatcher().updateObject(MAX_XP_WATCHER, props.getMaxXP());
		player.getDataWatcher().updateObject(POWER_WATCHER, props.getCurrentPower());
		player.getDataWatcher().updateObject(MAX_POWER_WATCHER, props.getMaxPower());
		player.getDataWatcher().updateObject(ENDER_POWER_WATCHER, props.getCurrentEnderPower());
		player.getDataWatcher().updateObject(OPEN_STONE, props.didPlayerOpenStone());
		player.getDataWatcher().updateObject(RESEARCH_EFFECTS, props.didPlayerResearchEffects());
	}

	@Override
	public final void saveNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = new NBTTagCompound();
		properties.setInteger("CurrentXP", player.getDataWatcher().getWatchableObjectInt(XP_WATCHER));
		properties.setInteger("CurrentLevel", player.getDataWatcher().getWatchableObjectInt(LEVEL_WATCHER));
		properties.setInteger("MaxXP", player.getDataWatcher().getWatchableObjectInt(MAX_XP_WATCHER));
		properties.setInteger("CurrentPower", player.getDataWatcher().getWatchableObjectInt(POWER_WATCHER));
		properties.setInteger("MaxPower", player.getDataWatcher().getWatchableObjectInt(MAX_POWER_WATCHER));
		properties.setInteger("CurrentEnderPower", player.getDataWatcher().getWatchableObjectInt(ENDER_POWER_WATCHER));
		properties.setInteger("DidPlayerOpenStone", player.getDataWatcher().getWatchableObjectInt(OPEN_STONE));
		properties.setInteger("DidPlayerResearchEffects", player.getDataWatcher().getWatchableObjectInt(RESEARCH_EFFECTS));
		compound.setTag(EXT_PROP_NAME, properties);
	}

	@Override
	public final void loadNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);
		player.getDataWatcher().updateObject(XP_WATCHER, properties.getInteger("CurrentXP"));
		player.getDataWatcher().updateObject(LEVEL_WATCHER, properties.getInteger("CurrentLevel"));
		player.getDataWatcher().updateObject(MAX_XP_WATCHER, properties.getInteger("MaxXP"));
		player.getDataWatcher().updateObject(POWER_WATCHER, properties.getInteger("CurrentPower"));
		player.getDataWatcher().updateObject(MAX_POWER_WATCHER, properties.getInteger("MaxPower"));
		player.getDataWatcher().updateObject(ENDER_POWER_WATCHER, properties.getInteger("CurrentEnderPower"));
		player.getDataWatcher().updateObject(OPEN_STONE, properties.getInteger("DidPlayerOpenStone"));
		player.getDataWatcher().updateObject(RESEARCH_EFFECTS, properties.getInteger("DidPlayerResearchEffects"));
	}

	@Override
	public void init(Entity entity, World world) {}

	public final int getCurrentXP() {
		return player.getDataWatcher().getWatchableObjectInt(XP_WATCHER);
	}

	public final int getXPtoNext(){
		return player.getDataWatcher().getWatchableObjectInt(MAX_XP_WATCHER) - player.getDataWatcher().getWatchableObjectInt(XP_WATCHER);
	}

	public final int getCurrentLevel(){
		return player.getDataWatcher().getWatchableObjectInt(LEVEL_WATCHER);
	}

	public final int getMaxXP() {
		return player.getDataWatcher().getWatchableObjectInt(MAX_XP_WATCHER);
	}

	public final int getCurrentPower(){
		return player.getDataWatcher().getWatchableObjectInt(POWER_WATCHER);
	}

	public final int getMaxPower(){
		return player.getDataWatcher().getWatchableObjectInt(MAX_POWER_WATCHER);
	}

	public final void addToMaxPower(int amount){
		int maxAmount = amount;

		while(maxAmount != 0){
			player.getDataWatcher().updateObject(MAX_POWER_WATCHER, this.getMaxPower() + 1);
			maxAmount = maxAmount - 1;
		}
	}

	public final int getCurrentEnderPower(){
		return player.getDataWatcher().getWatchableObjectInt(ENDER_POWER_WATCHER);
	}

	public final int didPlayerOpenStone(){
		return player.getDataWatcher().getWatchableObjectInt(OPEN_STONE);
	}
	
	//Research Stuff
	public final int didPlayerResearchEffects(){
		return player.getDataWatcher().getWatchableObjectInt(RESEARCH_EFFECTS);
	}
	
	public final int didPlayerResearch1(){
		return player.getDataWatcher().getWatchableObjectInt(RESEARCH_1);
	}
	
	public final int didPlayerResearch2(){
		return player.getDataWatcher().getWatchableObjectInt(RESEARCH_2);
	}
	
	public final int didPlayerResearch3(){
		return player.getDataWatcher().getWatchableObjectInt(RESEARCH_3);
	}
	
	public final int didPlayerResearch4(){
		return player.getDataWatcher().getWatchableObjectInt(RESEARCH_4);
	}
	
	public final int didPlayerResearch5(){
		return player.getDataWatcher().getWatchableObjectInt(RESEARCH_5);
	}
	
	public final int didPlayerResearch6(){
		return player.getDataWatcher().getWatchableObjectInt(RESEARCH_6);
	}
	
	public final int didPlayerResearch7(){
		return player.getDataWatcher().getWatchableObjectInt(RESEARCH_7);
	}
	
	public final int didPlayerResearch8(){
		return player.getDataWatcher().getWatchableObjectInt(RESEARCH_8);
	}
	
	public final int didPlayerResearch9(){
		return player.getDataWatcher().getWatchableObjectInt(RESEARCH_9);
	}
	
	public final int didPlayerResearch10(){
		return player.getDataWatcher().getWatchableObjectInt(RESEARCH_10);
	}
	
	public final int didPlayerResearch11(){
		return player.getDataWatcher().getWatchableObjectInt(RESEARCH_11);
	}

	public final void addPower(int amount){
		int powerAmount = amount;
		while(powerAmount != 0){
			player.getDataWatcher().updateObject(POWER_WATCHER, this.getCurrentPower() + 1);
			if(this.getCurrentPower() >= this.getMaxPower()){
				player.getDataWatcher().updateObject(POWER_WATCHER, this.getMaxPower());
			} 
			powerAmount = powerAmount - 1;
			System.out.println("Left to add: " + powerAmount);
		}
	}

	public final void removePower(int amount){
		int powerAmount = amount;
		while(powerAmount != 0){
			player.getDataWatcher().updateObject(POWER_WATCHER, this.getCurrentPower() - 1);
			if(player.getDataWatcher().getWatchableObjectInt(POWER_WATCHER) <= 0){
				player.getDataWatcher().updateObject(POWER_WATCHER, 0);
			} 
			powerAmount = powerAmount - 1;
			System.out.println("Player: " + this.EXT_PROP_NAME + "Left to Remove: " + powerAmount);
		}
	}

	public final void addXP(int amount) {
		int runAmount = amount;
		while(runAmount != 0 ){
			player.getDataWatcher().updateObject(XP_WATCHER, this.getCurrentXP() + 1);
			runAmount = runAmount - 1;
			if(this.getCurrentXP() == this.getMaxXP()){
				player.getDataWatcher().updateObject(XP_WATCHER, 0);
				player.getDataWatcher().updateObject(LEVEL_WATCHER, this.getCurrentLevel() + 1);
				player.getDataWatcher().updateObject(MAX_XP_WATCHER, this.getMaxXP() + 10);
				player.getDataWatcher().updateObject(MAX_POWER_WATCHER, this.getMaxPower() + 3);
				if(player.worldObj.isRemote){
					Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("Level Up! Level: " + this.getCurrentLevel()));
				}
			}
			//Research Unlock Message
			System.out.println("Level: " + this.getCurrentLevel() + " Xp: " + this.getCurrentXP() + "/" + this.getMaxXP());
			if(this.getCurrentLevel() == 5 && this.getCurrentXP() == 0){
				if(player.worldObj.isRemote){
					Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("Research has been unlocked! Open the Stone of Experience to view."));
				}
			}
			//Effect Unlock Message
			if(this.getCurrentLevel() == 10 && this.getCurrentXP() == 0){
				if(player.worldObj.isRemote){
					Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("Effects are now available! Research effects in the Stone of Experience to use."));
				}
			}
			//Power Pickaxe Unlock Message
			if(this.getCurrentLevel() == 15 && this.getCurrentXP() == 0){
				if(player.worldObj.isRemote){
					Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("Effects are now available! Research effects in the Stone of Experience to use."));
				}
			}
			//Power Regeneration Unlock Message
			if(this.getCurrentLevel() == 20 && this.getCurrentXP() == 0){
				if(player.worldObj.isRemote){
					Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("Effects are now available! Research effects in the Stone of Experience to use."));
				}
			}
			//Power Experience Pickaxe Unlock Message
			if(this.getCurrentLevel() == 25 && this.getCurrentXP() == 0){
				if(player.worldObj.isRemote){
					Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("Effects are now available! Research effects in the Stone of Experience to use."));
				}
			}
			//Ender Capacitor Unlock Message
			if(this.getCurrentLevel() == 30 && this.getCurrentXP() == 0){
				if(player.worldObj.isRemote){
					Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("Effects are now available! Research effects in the Stone of Experience to use."));
				}
			}
			//Mana Glowed Blocks Unlock Message
			if(this.getCurrentLevel() == 35 && this.getCurrentXP() == 0){
				if(player.worldObj.isRemote){
					Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("Effects are now available! Research effects in the Stone of Experience to use."));
				}
			}
			//Effect Sword is unlocked
			if(this.getCurrentLevel() == 40 && this.getCurrentXP() == 0){
				if(player.worldObj.isRemote){
					Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("Effects are now available! Research effects in the Stone of Experience to use."));
				}
			}
		}
	}

	public final void addEnderPower(int amount){
		int powerAmount = amount;
		while(powerAmount >= 1){
			player.getDataWatcher().updateObject(ENDER_POWER_WATCHER, this.getCurrentEnderPower() + 1);
			powerAmount -= 1;
			System.out.println("Add 1 to Ender Power RETURNED TRUE!");
		}
	}

	public final void removeEnderPower(int amount){
		int powerAmount = amount;
		while(powerAmount != 0){
			if(this.getCurrentEnderPower() >= 1){
				player.getDataWatcher().updateObject(ENDER_POWER_WATCHER, this.getCurrentEnderPower() - 1);
			} else if(this.getCurrentEnderPower() == 0){
				player.getDataWatcher().updateObject(ENDER_POWER_WATCHER, 0);
				break;
			}		
			powerAmount = powerAmount - 1;
			System.out.println("Remove 1 from Ender Power RETURNED TRUE!");
		}
	}

	public final void setCurrentLevel(int amount){
		player.getDataWatcher().updateObject(LEVEL_WATCHER, this.getCurrentLevel() + amount);
	}

	public final void playerOpenStone(boolean value){
		boolean opened = value;
		if(opened == true){
			player.getDataWatcher().updateObject(OPEN_STONE, 1);
		}
	}
	
	public final void playerResearchEffects(int value){
		player.getDataWatcher().updateObject(RESEARCH_EFFECTS, value);
	}
	
	public final void playerResearch1(int value){
		player.getDataWatcher().updateObject(RESEARCH_1, value);
	}
	
	public final void playerResearch2(int value){
		player.getDataWatcher().updateObject(RESEARCH_2, value);
	}
	
	public final void playerResearch3(int value){
		player.getDataWatcher().updateObject(RESEARCH_3, value);
	}
	
	public final void playerResearch4(int value){
		player.getDataWatcher().updateObject(RESEARCH_4, value);
	}
	
	public final void playerResearch5(int value){
		player.getDataWatcher().updateObject(RESEARCH_5, value);
	}
	
	public final void playerResearch6(int value){
		player.getDataWatcher().updateObject(RESEARCH_6, value);
	}
	
	public final void playerResearch7(int value){
		player.getDataWatcher().updateObject(RESEARCH_7, value);
	}
	
	public final void playerResearch8(int value){
		player.getDataWatcher().updateObject(RESEARCH_8, value);
	}
	
	public final void playerResearch9(int value){
		player.getDataWatcher().updateObject(RESEARCH_9, value);
	}
	
	public final void playerResearch10(int value){
		player.getDataWatcher().updateObject(RESEARCH_10, value);
	}
	
	public final void playerResearch11(int value){
		player.getDataWatcher().updateObject(RESEARCH_11, value);
	}
}