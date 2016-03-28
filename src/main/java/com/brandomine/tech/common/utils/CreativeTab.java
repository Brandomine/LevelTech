package com.brandomine.tech.common.utils;

import com.brandomine.tech.common.lib.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class CreativeTab {
	public static final CreativeTabs TECH_TAB = new CreativeTabs(Reference.MODID.toLowerCase())
    {
        @Override
        public Item getTabIconItem()
        {
            return Items.bone;
        }
    };
}
