package com.brandomine.tech.common.item;

import com.brandomine.tech.common.init.ModItems;
import com.brandomine.tech.common.lib.Names;
import com.brandomine.tech.common.utils.CreativeTab;
import com.brandomine.tech.common.utils.ExtendedPlayer;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;

public class ItemPowerPickaxe extends ItemPickaxe{

	private static final EntityPlayer player = null;

	public ItemPowerPickaxe(ToolMaterial material) {
		super(material);
		this.setUnlocalizedName("tech:" + Names.Items.POWER);
		this.setCreativeTab(CreativeTab.TECH_TAB);
	}
	
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos blockPos, EntityPlayer player) {
		ExtendedPlayer props = ExtendedPlayer.get(player);	

		if(player.inventory.hasItem(ModItems.itemEnderCapacitor) == true){
			if(props.getCurrentEnderPower() >= 1){
				props.removeEnderPower(1);
				return super.onBlockStartBreak(itemstack, blockPos, player);
			}else if(props.getCurrentEnderPower() <= 0){
				if(props.getCurrentPower() >= 1){
					props.removePower(1);
					return super.onBlockStartBreak(itemstack, blockPos, player);
				}else if(props.getCurrentPower() <= 0){
					return true;
				}
			}
		}else if(player.inventory.hasItem(ModItems.itemEnderCapacitor) == false){
			if(props.getCurrentPower() >= 1){
				props.removePower(1);
				return super.onBlockStartBreak(itemstack, blockPos, player);
			}else if(props.getCurrentPower() <= 0){
				Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("Not Enough Power"));
				return true;
			}
		}
		return true; 
	}
}
