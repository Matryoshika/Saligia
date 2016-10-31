/**
 * 
 */
package se.Matryoshika.Saligia.Content.Tiles.Altars;

import net.minecraft.item.ItemStack;
import se.Matryoshika.Saligia.Saligia;

/**
 * This class was created by Matryoshika Aug 19, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class TilePaganAltar extends TileAbstractAltar{
	
	
	@Override
	public void update(){
		System.out.println(this.getStackInSlot(0));
	}

	
	

	public int inputMaxSpeed(int maxInput) {
		return 6;
	}

	
	

	public int inputMinSpeed(int minInput) {
		return 1;
	}


	@Override
	public int getSlots() {
		return 9;
	}



	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventorySlots[slot];
	}




	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
		
		if(inventorySlots[slot] == null){
			
			stack.splitStack(1);
			inventorySlots[slot] = new ItemStack(stack.copy().getItem());
			return stack;
			
		}
			
		return null;
	}



	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate){
		
		ItemStack stack = getStackInSlot(slot);
		

		return stack;
	}

}
