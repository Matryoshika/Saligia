/**
 * This code was made by Matryoshika at 2:11:23 PM Apr 27, 2016.
 * This code is property of Matryoshika.
 * It may be modified for personal use, but may then not be distributed.
 *
 * Please fork & create a pull-request if the source should change.
 */
package Matryoshika.mods.saligia.API.soulsystem;

import net.minecraft.item.ItemStack;

/**
 * @author Matryoshika
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
