package com.brandomine.tech.common.item;

import java.util.List;

import com.brandomine.tech.client.gui.GUIMenu;
import com.brandomine.tech.common.capability.leveling.LevelStorage;
import com.brandomine.tech.common.lib.Names;
import com.brandomine.tech.common.utils.CreativeTab;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.nbt.NBTTagCompound;

public class ItemStone extends Item{
	public static String PlayerName;
	public ItemStone(){
		super();
		this.setRegistryName(Names.Items.EXPERIENCE_STONE);
		this.setUnlocalizedName(Names.Items.EXPERIENCE_STONE);
		this.setCreativeTab(CreativeTab.TECH_TAB);
		GameRegistry.registerItem(this);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if(!worldIn.isRemote){
			Minecraft.getMinecraft().displayGuiScreen(new GUIMenu());
		}
		return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		tooltip.add(playerIn.getName() + "'s leveling is in this book!");
		super.addInformation(stack, playerIn, tooltip, advanced);
	}

}
