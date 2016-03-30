package com.brandomine.tech.common.item;

import com.brandomine.tech.common.leveling.LevelInfo;
import com.brandomine.tech.common.leveling.PlayerLevelInfo;
import com.brandomine.tech.common.lib.Names;
import com.brandomine.tech.common.utils.CreativeTab;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ToolExperiencePickaxe extends ItemPickaxe{
	private Minecraft mc;
	
	public ToolExperiencePickaxe(ToolMaterial material) {
		super(material);
		this.setRegistryName(Names.Items.EXPERIENCE_PICKAXE);
		this.setUnlocalizedName(Names.Items.EXPERIENCE_PICKAXE);
		this.setCreativeTab(CreativeTab.TECH_TAB);
		GameRegistry.registerItem(this);
	}
	
	@SideOnly(Side.CLIENT)
	public void initModel(){
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
	
	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player) {
		LevelInfo level = PlayerLevelInfo.getLevelInfo(player);
		level.addXp(1);
		return super.onBlockStartBreak(itemstack, pos, player);
	}
}
