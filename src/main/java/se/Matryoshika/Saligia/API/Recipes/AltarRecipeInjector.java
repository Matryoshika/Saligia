package se.Matryoshika.Saligia.API.Recipes;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import se.Matryoshika.Saligia.Utils.AltarRecipes;

/**
 * This class was created by Matryoshika Aug 8, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class AltarRecipeInjector {

	
	public static List<AltarRecipes> saligiaAltarRecipes = new ArrayList<AltarRecipes>();
	
	/**
	 * @param output : What item is gained through crafting
	 * @param outputAmount : How many items should be gotten per batch
	 * @param tier : The required tier of altar needed to craft (1->5)
	 * @param animun : Amount of Animun required to craft
	 * @param inputs : List of up to 9 itemstacks. First itemstack can be Item or (Item)Block, following has to be Items.
	 * @return
	 */
	public static AltarRecipes registerAltarRecipes(ItemStack output, int outputAmount, int tier, int animun, ItemStack... inputs) {
		AltarRecipes recipe = new AltarRecipes(output, outputAmount, tier, animun, inputs);
		saligiaAltarRecipes.add(recipe);
		return recipe;
}
}
