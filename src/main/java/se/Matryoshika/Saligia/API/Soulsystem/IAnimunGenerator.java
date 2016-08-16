package se.Matryoshika.Saligia.API.Soulsystem;

import net.minecraft.item.ItemStack;

/**
 * This interface was created by Matryoshika Aug 15, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public interface IAnimunGenerator {
	/**
	 * Ticks between batches
	 */
	public int ticksToGenerate();
	
	/**
	 * Amount of Animun per batch
	 */
	public int generateAmount();
	
	/**
	 * For any generator that needs to run on fuel
	 */
	public ItemStack fuel(ItemStack stack);

}
