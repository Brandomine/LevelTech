package com.brandomine.tech.common.item;

import java.util.List;

import com.brandomine.tech.common.init.ModItems;
import com.brandomine.tech.common.lib.Names;
import com.brandomine.tech.common.utils.CreativeTab;
import com.brandomine.tech.common.utils.ExtendedPlayer;

import net.minecraft.item.Item;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemEnderCapacitor extends Item{
	public ItemEnderCapacitor(){
		this.setRegistryName(Names.Items.ENDER_CAPACITOR);
		this.setUnlocalizedName(Names.Items.ENDER_CAPACITOR);
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTab.TECH_TAB);
		GameRegistry.registerItem(this);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
			ExtendedPlayer props = ExtendedPlayer.get(player);
		
			if(player.isSneaking() == true){
				if(props.getCurrentEnderPower() >= 1 && props.getMaxPower() - props.getCurrentPower() >= 1){
					props.removeEnderPower(1);
					props.addPower(1);
					System.out.println("Adding 1 power to Player; Removing 1 power from Ender Capacitor");
				}
			} else if(player.isSneaking() == false){
				if(props.getCurrentPower() >= 1){
					props.addEnderPower(1);
					props.removePower(1);
					System.out.println("Adding 1 power to Ender Capacitor; Removing 1 power from player");
					}else if(props.getCurrentPower() == 0){
						if(world.isRemote){
							Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("Not Enough Power"));
						}
					}
				}
			return super.onItemRightClick(itemStack, world, player);
		}

	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean extraInformation) {
		ExtendedPlayer props = ExtendedPlayer.get(player);
		list.add("Current Power Stored: " + props.getCurrentEnderPower());
		super.addInformation(itemStack, player, list, extraInformation);
	}
	
	@SideOnly(Side.CLIENT)
	public void initModel(){
		ModelLoader.setCustomModelResourceLocation(this, 0,new ModelResourceLocation(getRegistryName()));
	}
}
