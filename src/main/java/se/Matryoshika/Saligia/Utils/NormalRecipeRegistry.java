/**
 * 
 */
package se.Matryoshika.Saligia.Utils;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import se.Matryoshika.Saligia.Content.ContentRegistry;

/**
 * This class was created by Matryoshika Aug 16, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class NormalRecipeRegistry {
	
	public static void registerRecipes(){
		
		//Items================================================
		GameRegistry.addShapelessRecipe(
				new ItemStack(ContentRegistry.SIN_INGOT, 9), 
				new ItemStack(ContentRegistry.SIN_BLOCK));
		
		
		//Blocks===============================================
		ItemStack SIN_BLOCK = new ItemStack(ContentRegistry.SIN_BLOCK);
		GameRegistry.addRecipe(SIN_BLOCK, new Object[]{
				"III",
				"III",
				"III",
				'I', ContentRegistry.SIN_INGOT
		});
		
		
		//Furnace==============================================
		ItemStack SIN_INGOT = new ItemStack(ContentRegistry.SIN_INGOT);
		ItemStack DUST_INGOT = new ItemStack(ContentRegistry.DUST_INGOT);
		GameRegistry.addSmelting(DUST_INGOT, SIN_INGOT, 8);
	}

}
