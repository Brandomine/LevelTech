package com.brandomine.tech.common.item;

import com.brandomine.tech.common.init.ModItems;
import com.brandomine.tech.common.lib.Names;
import com.brandomine.tech.common.utils.CreativeTab;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMaxPowerExpand extends Item{
	public ItemMaxPowerExpand(){
		super();
		this.setRegistryName(Names.Items.POWER_UP_POWER_REFILL);
		this.setUnlocalizedName(Names.Items.POWER_UP_POWER_REFILL);
		this.setCreativeTab(CreativeTab.TECH_TAB);
		GameRegistry.registerItem(this);
	}
	
	@SideOnly(Side.CLIENT)
	public void initModel(){
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		-- itemStackIn.stackSize;
		return super.onItemRightClick(itemStackIn , worldIn, playerIn, hand);
	}
}
