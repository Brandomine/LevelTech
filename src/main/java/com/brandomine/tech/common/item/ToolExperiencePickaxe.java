package com.brandomine.tech.common.item;

import com.brandomine.tech.common.lib.Names;
import com.brandomine.tech.common.utils.CreativeTab;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.world.BlockEvent;
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
}
