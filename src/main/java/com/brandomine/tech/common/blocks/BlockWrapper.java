package com.brandomine.tech.common.blocks;

import com.brandomine.tech.common.lib.Reference;
import com.brandomine.tech.common.utils.CreativeTab;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;


public class BlockWrapper extends Block{
	public BlockWrapper(Material material)
    {
        super(material);
        this.setCreativeTab(CreativeTab.TECH_TAB);
    }

    public BlockWrapper()
    {
        this(Material.rock);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", Reference.MODID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}
