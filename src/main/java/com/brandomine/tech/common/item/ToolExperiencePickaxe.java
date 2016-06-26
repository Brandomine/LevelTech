package com.brandomine.tech.common.item;

import com.brandomine.tech.common.capability.leveling.LevelInfo;
import com.brandomine.tech.common.capability.leveling.PlayerLevelInfo;
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
	
	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player) {
		LevelInfo level = new PlayerLevelInfo().getLevelInfo(player);
		World world = Minecraft.getMinecraft().theWorld;
		world.getBlockState(pos.toImmutable());
		if(world.getBlockState(pos.toImmutable()).getBlock() == Blocks.STONE){
			level.addXp(1);
		}
		if(world.getBlockState(pos.toImmutable()).getBlock() == Blocks.COAL_ORE){
			level.addXp(2);
		}
		if(world.getBlockState(pos.toImmutable()).getBlock() == Blocks.IRON_ORE){
			level.addXp(5);
		}
		if(world.getBlockState(pos.toImmutable()).getBlock() == Blocks.GOLD_ORE){
			level.addXp(7);
		}
		if(world.getBlockState(pos.toImmutable()).getBlock() == Blocks.REDSTONE_ORE){
			level.addXp(3);
		}
		if(world.getBlockState(pos.toImmutable()).getBlock() == Blocks.DIAMOND_ORE){
			level.addXp(10);
		}
		if(world.getBlockState(pos.toImmutable()).getBlock() == Blocks.EMERALD_ORE){
			level.addXp(20);
		}
		if(world.getBlockState(pos.toImmutable()).getBlock() == Blocks.QUARTZ_ORE){
			level.addXp(4);
		}
		
		return super.onBlockStartBreak(itemstack, pos, player);
	}
}
