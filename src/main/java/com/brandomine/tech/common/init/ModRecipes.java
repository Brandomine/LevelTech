package com.brandomine.tech.common.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {
	public static void init(){
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.itemStone), "ddd", "isi", "dgd", 'd', Items.DIAMOND, 'i', Items.IRON_INGOT, 's', new ItemStack(Blocks.STONE), 'g', Items.GOLD_INGOT);
	}
}
