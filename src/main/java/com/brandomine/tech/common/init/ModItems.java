package com.brandomine.tech.common.init;

import com.brandomine.tech.common.item.ItemMaxPowerExpand;
import com.brandomine.tech.common.item.ItemStone;
import com.brandomine.tech.common.item.ToolExperiencePickaxe;
//import com.brandomine.tech.common.item.ItemEnderCapacitor;
//import com.brandomine.tech.common.item.ItemExperiencePickaxe;
//import com.brandomine.tech.common.item.ItemPowerPickaxe;
//import com.brandomine.tech.common.item.ItemPowerUpRefill;
//import com.brandomine.tech.common.item.ItemStoneOfExperience;
//import com.brandomine.tech.common.item.ItemWrapper;
import com.brandomine.tech.common.lib.Names;
import com.brandomine.tech.common.lib.Reference;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraftforge.common.util.EnumHelper;

@GameRegistry.ObjectHolder(Reference.MODID)
public class ModItems {
	public static ToolMaterial enumToolMaterialExperience = EnumHelper.addToolMaterial("Experience", 3, 100, 11.0F, 5.0F, 30);
	public static ToolMaterial enumToolMaterialPower = EnumHelper.addToolMaterial("power", 5, 0, 11.0F, 5.0F, 30);
	
	public static ToolExperiencePickaxe toolExperiencePickaxe;
	public static ItemMaxPowerExpand itemMaxPowerExpand;
	public static ItemStone itemStone;
	
	public static void init(){
		toolExperiencePickaxe = new ToolExperiencePickaxe(enumToolMaterialExperience);
		itemMaxPowerExpand = new ItemMaxPowerExpand();
		itemStone = new ItemStone();
	}
	
	@SideOnly(Side.CLIENT)
	public static void initModels(){
	//	itemEnderCapacitor.initModel();
		toolExperiencePickaxe.initModel();
		itemMaxPowerExpand.initModel();
	}
}
