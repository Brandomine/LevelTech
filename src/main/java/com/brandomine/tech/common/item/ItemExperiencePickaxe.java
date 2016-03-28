package com.brandomine.tech.common.item;

import java.util.List;
import java.util.Random;
import com.brandomine.tech.common.init.ModItems;
import com.brandomine.tech.common.lib.Names;
import com.brandomine.tech.common.utils.CreativeTab;
import com.brandomine.tech.common.utils.ExtendedPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;

import net.minecraft.client.stream.IStream;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;

public class ItemExperiencePickaxe extends ItemPickaxe{
	World world;
	private static final EntityPlayer player = null;

	public ItemExperiencePickaxe(ToolMaterial material) {
		super(material);
		this.setCreativeTab(CreativeTab.TECH_TAB);
		this.setUnlocalizedName("tech:" + Names.Items.EXP);
	}

	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos blockPos, EntityPlayer player) {
		ExtendedPlayer props = ExtendedPlayer.get(player);
		Random r = new Random();
		props.addXP(1);
		int a = 1;
		int b = 1000;
		int c = r.nextInt(b-a) + a;
		int d = r.nextInt(b-a) + a;
		if(c == d){
			if(player.inventory.addItemStackToInventory(itemstack) == false){
				if(player.worldObj.isRemote){
					Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("You found a power up! It couldnt fit in your inventory so you destroyed it!"));
				}
			}else{
				player.inventory.addItemStackToInventory(new ItemStack(ModItems.itemPowerUpRefill));
				if(player.worldObj.isRemote){
					Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("You found a power up! It has been placed in your inventory!"));
				}
			}
		}
		System.out.println(c + " / " + d);
		return super.onBlockStartBreak(itemstack, blockPos, player);
	}

	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean extraInformation) {
		list.add("Level 1 Experience Pickaxe");
		list.add("XP gain: +1");
		super.addInformation(itemStack, player, list, extraInformation);
	}

}
