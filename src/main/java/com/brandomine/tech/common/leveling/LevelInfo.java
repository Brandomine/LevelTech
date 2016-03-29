package com.brandomine.tech.common.leveling;

import com.brandomine.tech.common.leveling.capabilities.LevelStorage;

import net.minecraft.nbt.NBTTagCompound;

public class LevelInfo {
	private int xp = 0;
	private int level = 1;
	private int maxXp = 20;
	
	public LevelInfo(LevelStorage level){
		this.level = this.getLevel();
		this.xp = this.getXp();
		this.maxXp = this.getMaxXp();
	}
	
	public LevelInfo(int level, int xp, int maxXp){
		this.level = level;
		this.maxXp = maxXp;
		this.xp = xp;
	}
	
	public LevelInfo(int xp, int maxXp) {
		this(1, xp, maxXp);
	}


	public int getLevel(){
		return level;
	}
	
	public int getXp(){
		return xp;
	}
	
	public int getMaxXp(){
		return maxXp;
	}
	
	public void writeToNBT(NBTTagCompound tag){
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("Level", this.getLevel());
		nbt.setInteger("Xp", this.getXp());
		nbt.setInteger("MaxXp", this.getMaxXp());
		tag.setTag("LevelInfo", nbt);
	}
	
	public void setLevel(int level){
		this.level = level;
	}
	
	public void setMaxXp(int maxXp){
		this.maxXp = maxXp;
	}
	
	public void setXp(int xp){
		this.xp = xp;
	}
	
	public void readFromNBT(NBTTagCompound tag){
		NBTTagCompound nbt = tag.getCompoundTag("LevelInfo");
		level = nbt.getInteger("Level");
		xp = nbt.getInteger("Xp");
		maxXp = nbt.getInteger("MaxXp");
	}
	
	public void levelUp(int levels){
		level += levels;
	}
	
	public void addXp(int amount){
		int runAmount = amount;
		while(runAmount != 0){
			xp = xp + 1;
			if(xp == maxXp){
				levelUp(1);
				setXp(0);
				setMaxXp(maxXp + 10);
			}
			runAmount = runAmount - 1;
		}
	}
}
