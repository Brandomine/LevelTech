package com.brandomine.tech.common.capability.power;

import com.brandomine.tech.common.capability.leveling.LevelStorage;

import net.minecraft.nbt.NBTTagCompound;

public class PowerInfo {
	private double power = 0;
	private double maxPower = 5;
	private double enderPower = Double.POSITIVE_INFINITY + 0;
	
	
	public PowerInfo(PowerStorage power){
		this.power = this.getPower();
		this.maxPower = this.getMaxPower();
		this.enderPower = this.getEnderPower();
	}
	
	public PowerInfo(double power, double maxPower, double enderPower){
		this.power = power;
		this.maxPower = maxPower;
		this.enderPower = enderPower;
	}
	
	public PowerInfo(double power, double maxPower) {
		this(power, maxPower, 0);
	}

	public double getPower() {
		return power;
	}

	public double getEnderPower() {
		return enderPower;
	}

	public double getMaxPower() {
		return maxPower;
	}
	
	public void writeToNBT(NBTTagCompound tag){
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setDouble("Power", this.getPower());
		nbt.setDouble("MaxPower", this.getMaxPower());
		nbt.setDouble("EnderPower", this.getEnderPower());
		tag.setTag("PowerInfo", nbt);
	}
	
	public void setPower(double amount){
		this.power = amount;
	}
	
	public void setMaxPower(double amount){
		this.maxPower = amount;
	}
	
	public void setEnderPower(double amount){
		this.enderPower = amount;
	}
	
	public void readFromNBT(NBTTagCompound tag){
		NBTTagCompound nbt = tag.getCompoundTag("PowerInfo");
		power = nbt.getDouble("Power");
		maxPower = nbt.getDouble("MaxPower");
		enderPower = nbt.getDouble("EnderPower");
	}
	
	public boolean canAddPower(){
		if(this.power < this.maxPower){
			return true;
		}else{
			return false;
		}
	}
	

	public boolean canRemovePower(){
		if(this.power > 0){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean canRemoveEnderPower(){
		if(this.enderPower > 0){
			return true;
		}else{
			return false;
		}
	}
	
	public void addPower(double amount){
		double runAmount = amount;
		while(runAmount != 0){
			if(canAddPower() == true){
				this.power ++;
				runAmount --;
			}else if(canAddPower() != true){
				break;
			}
		}
	}
	
	public void removePower(double amount){
		double runAmount = amount;
		while(runAmount != 0){
			if(canAddPower() == true){
				this.power ++;
				runAmount --;
			}else if(canAddPower() != true){
				break;
			}
		}
	}
	
	public void addEnderPower(double amount){
		double runAmount = amount;
		while(runAmount != 0){
			this.enderPower ++;
			runAmount --;
		}
	}
	
	public void removeEnderPower(double amount){
		double runAmount = amount;
		while(runAmount != 0){
			if(canRemoveEnderPower() == true){
				this.enderPower --;
				runAmount --;
			}else if(canRemoveEnderPower() != true){
				break;
			}
		}
	}
}
