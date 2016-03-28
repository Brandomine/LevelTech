package com.brandomine.tech.common.capability;

public class LevelBuffer implements ILevelHandler {
	protected int xp = 0;
	protected int maxXp = 20;
	protected int level = 1;
	
	public LevelBuffer(){
		
	}
	
	public LevelBuffer(int maxXp){
		this.maxXp = maxXp;
	}
	@Override
	public int getMaxXp() {
		return maxXp;
	}
	public LevelBuffer setMaxXp(int maxXp){
		this.maxXp = maxXp;
		return this;
	}
	
	@Override
	public int getLevel() {
		return level;
	}
	public void addLevel(int amount){
		int runAmount = amount;
		while(runAmount != 0){
			this.level = this.level + 1;
			runAmount --;
		}
	}

	@Override
	public int getXp() {
		return xp;
	}
	@Override
	public int addXp(int value) {
		int runAmount = value;
		while(runAmount != 0){
			this.xp = this.xp + 1;
			if(this.xp == this.maxXp){
				this.level = this.level + 1;
			}
			runAmount --;
		}
		return runAmount;
	}

}
