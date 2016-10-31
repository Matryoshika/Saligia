/**
 * 
 */
package se.Matryoshika.Saligia.API.Soulsystem;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import se.Matryoshika.Saligia.API.Tools.ISaligiaTool;

/**
 * This class was created by Matryoshika Oct 31, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class CreativeAnimunBase extends Item implements IAnimun{


	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected){
		if(entity.worldObj.isRemote)
			return;
		
		if(entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) entity;
			
			for(ItemStack stacks : player.inventory.mainInventory){
				if(stacks != null && stacks.getItem() instanceof ISaligiaTool){
					ISaligiaTool item = (ISaligiaTool) stacks.getItem();
					item.tryRepair(player, stacks, stacks.getMaxDamage() / 666);
						
				}
			}
			for(ItemStack stacks : player.inventory.armorInventory){
				if(stacks != null && stacks.getItem() instanceof IAnimun){
					
				}
			}
		}
    }
	
	@Override
	public void input() {}


	@Override
	public void output() {	}


	@Override
	public void transfer(IAnimun start, IAnimun finish) {}


	@Override
	public void set(int amount) {}


	@Override
	public float maxAmount() {
		return Float.MAX_VALUE;
	}


	@Override
	public int getCurrentAmount() {
		return (int) Float.MAX_VALUE;
	}


	@Override
	public int outputPacket() {
		return (int) Float.MAX_VALUE / 666;
	}


	@Override
	public int inputPacket() {
		return (int) Float.MAX_VALUE / 666;
	}


	@Override
	public float getFillAmountPercentage() {
		return 100;
	}


	@Override
	public float getTier() {
		return -1;
	}


	@Override
	public boolean isHandHeld() {
		return false;
	}

	@Override
	public boolean repairable() {
		return false;
	}
	
	

}
