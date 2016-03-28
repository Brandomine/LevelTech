package com.brandomine.tech.common.item;

import com.brandomine.tech.client.gui.GUIMenu;
import com.brandomine.tech.common.lib.Names;
import com.brandomine.tech.common.utils.ExtendedPlayer;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemStoneOfExperience extends ItemWrapper{
	public ItemStoneOfExperience(){
		super();
		this.setUnlocalizedName(Names.Items.STONE);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
		if(!world.isRemote){
			Minecraft.getMinecraft().displayGuiScreen(new GUIMenu());
		}
		
		return super.onItemRightClick(itemStack, world, player);
	}
}
