package com.brandomine.tech.common.item;

import java.util.List;

import com.brandomine.tech.common.init.ModItems;
import com.brandomine.tech.common.lib.Names;
import com.brandomine.tech.common.utils.ExtendedPlayer;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class ItemPowerUpRefill extends ItemWrapper{
	public ItemPowerUpRefill(){
		super();
		this.setUnlocalizedName(Names.Items.UP_REFILL);
	}
	
	
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
		ExtendedPlayer props = ExtendedPlayer.get(player);

		if(player.isSneaking() == true){
			props.addToMaxPower(10);
			player.inventory.consumeInventoryItem(ModItems.itemPowerUpRefill);
			if(player.worldObj.isRemote){
				Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("Max Power +10!"));
			}
		}else if(player.isSneaking() == false){
			if(props.getCurrentPower() < props.getMaxPower()){
				int max = props.getMaxPower();
				props.addPower(max);
				player.inventory.consumeInventoryItem(ModItems.itemPowerUpRefill);
			}else{
				if(player.worldObj.isRemote){
					Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("You try to use the power up but you realize that your power is already full!"));
				}
			}
		}
		return super.onItemRightClick(itemstack, world, player);
	}
	
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean extraInformation) {
		list.add("Shift right click to add 10 to max Power!");
		list.add("Right click to fill power to max!");
		super.addInformation(itemStack, player, list, extraInformation);
	}
}
