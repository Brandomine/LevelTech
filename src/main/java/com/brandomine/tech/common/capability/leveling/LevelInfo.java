package com.brandomine.tech.common.capability.leveling;

import net.minecraft.nbt.NBTTagCompound;

public class LevelInfo {
	private double xp = 0;
	private double level = 1;
	private double maxXp = 20;
	
	public LevelInfo(LevelStorage level){
		this.level = this.getLevel();
		this.xp = this.getXp();
		this.maxXp = this.getMaxXp();
	}
	
	public LevelInfo(double level, double xp, double maxXp){
		this.level = level;
		this.maxXp = maxXp;
		this.xp = xp;
	}
	
	public LevelInfo(double xp, double maxXp) {
		this(1, xp, maxXp);
	}


	public double getLevel(){
		return level;
	}
	
	public double getXp(){
		return xp;
	}
	
	public double getMaxXp(){
		return maxXp;
	}
	
	public void writeToNBT(NBTTagCompound tag){
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setDouble("Level", this.getLevel());
		nbt.setDouble("Xp", this.getXp());
		nbt.setDouble("MaxXp", this.getMaxXp());
		tag.setTag("LevelInfo", nbt);
	}
	
	public void setLevel(double level){
		this.level = level;
	}
	
	public void setMaxXp(double maxXp){
		this.maxXp = maxXp;
	}
	
	public void setXp(double xp){
		this.xp = xp;
	}
	
	public void readFromNBT(NBTTagCompound tag){
		NBTTagCompound nbt = tag.getCompoundTag("LevelInfo");
		level = nbt.getDouble("Level");
		xp = nbt.getDouble("Xp");
		maxXp = nbt.getDouble("MaxXp");
	}
	
	public void levelUp(double levels){
		this.level += levels;
	}
	
	public void addXp(double amount){
		double runAmount = amount;
		while(runAmount != 0){
			this.xp = xp + 1;
			if(xp == maxXp){
				levelUp(1);
				setXp(0);
				setMaxXp(maxXp + 10);
			}
			runAmount = runAmount - 1;
		}
	}
}
