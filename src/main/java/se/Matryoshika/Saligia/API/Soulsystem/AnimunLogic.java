/**
 * 
 */
package se.Matryoshika.Saligia.API.Soulsystem;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import se.Matryoshika.Saligia.Utils.Constants;

/**
 * This class was created by Matryoshika Oct 31, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class AnimunLogic implements IAnimun{
	
	private int animun = 0;


	@Override
	public void input() {
		
		animun = (int) Math.min(animun+inputPacket(), maxAmount());
		
	}


	@Override
	public void output() {
		
		animun = Math.max(animun-outputPacket(), 0);
		
	}


	@Override
	public void transfer(IAnimun start, IAnimun finish) {
		
		if(finish.getCurrentAmount() + start.outputPacket() <= finish.maxAmount() && start.getCurrentAmount() >= start.outputPacket()){
			start.output();
			finish.set(getCurrentAmount()+start.outputPacket());
		}
		
		if(start.getCurrentAmount() <= 0)
			start.set(0);
		
		if(finish.getCurrentAmount() >= finish.maxAmount())
			finish.set((int) finish.maxAmount());
		
	}
	
	
	@Override
	public void set(int amount) {
		
		animun = amount;
		
	}


	@Override
	public float maxAmount() {
		
		return isHandHeld() ? Constants.getStorageValues(getTier()) / 3 : Constants.getStorageValues(getTier());
		
	}


	@Override
	public int getCurrentAmount() {
		
		return animun;
		
	}


	@Override
	public int outputPacket() {
		
		return (int) (Constants.getStorageValues(getTier()) / 666)+5;
		
	}


	@Override
	public int inputPacket() {
		
		return (int) (Constants.getStorageValues(getTier()) / 666)+5;
	}


	@Override
	public float getFillAmountPercentage() {
		
		return (float) (Math.round((maxAmount() / getCurrentAmount())*100)/100.00);
		
	}

	@Override
	public float getTier() {
		return 0;
	}

	@Override
	public boolean isHandHeld() {
		return false;
	}

	@Override
	public boolean repairable() {
		return false;
	}

	@Override
	public void tryRepair(EntityPlayer owner, ItemStack stack, int animunRequirement) {}


}
