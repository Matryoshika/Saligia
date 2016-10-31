/**
 * 
 */
package se.Matryoshika.Saligia.API;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import se.Matryoshika.Saligia.API.Soulsystem.IAnimun;

/**
 * This class was created by Matryoshika Oct 30, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class Utils {
	
	public static ItemStack searchPlayerInventoryForAnimun(EntityPlayer player){
		for(ItemStack stack : player.inventory.mainInventory){
			if(stack.getItem() instanceof IAnimun){
				IAnimun item = (IAnimun) stack.getItem();
				if(item.getCurrentAmount() > 0)
					return stack;
			}
				
		}
		for(ItemStack stack : player.inventory.armorInventory){
			if(stack.getItem() instanceof IAnimun)
				return stack;
		}
		
		return null;
	}

}
