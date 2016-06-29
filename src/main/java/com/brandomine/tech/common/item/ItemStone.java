package com.brandomine.tech.common.item;

import java.util.List;
import java.util.UUID;

import com.brandomine.tech.client.gui.GUIMenu;
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
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		if(stack.hasTagCompound() == false){
			stack.setTagCompound(new NBTTagCompound());
		}
		//XP, Max XP
		int[] playerInfo = {0, 10};
		String Owner = playerIn.getName();
		UUID UUID = playerIn.getUniqueID();
		stack.getTagCompound().setIntArray("STONEDATA", playerInfo);
		stack.getTagCompound().setUniqueId("UUID", UUID);
		stack.getTagCompound().setString("OWNER", Owner);
		super.onCreated(stack, worldIn, playerIn);
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		if(stack.hasTagCompound() == false){
			stack.setTagCompound(new NBTTagCompound());
		}
		UUID playerID = stack.getTagCompound().getUniqueId("UUID");
		if(playerIn.getUniqueID() == playerID){
			String Owner = stack.getTagCompound().getString("OWNER");
			if(Owner != playerIn.getName()){
				stack.getTagCompound().setString("OWNER", playerIn.getName());
			}
		}
		if(stack.getTagCompound().getString("OWNER") == null){
			tooltip.add("If this item is in a chest, It has been cheated in and cant be used!");
		}else{
			tooltip.add("Owner: " + stack.getTagCompound().getString("OWNER"));
		}
		super.addInformation(stack, playerIn, tooltip, advanced);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if(!worldIn.isRemote){
			if(itemStackIn.getTagCompound().getString("OWNER") == playerIn.getName()){
				Minecraft.getMinecraft().displayGuiScreen(new GUIMenu());
			}
		}
		return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
	}

}
