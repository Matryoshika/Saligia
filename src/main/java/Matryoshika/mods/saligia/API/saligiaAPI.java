package Matryoshika.mods.saligia.API;

import java.util.ArrayList;
import java.util.List;

import Matryoshika.mods.saligia.utils.AltarRecipes;
import net.minecraft.item.ItemStack;

public final class saligiaAPI {
	
	public static List<AltarRecipes> saligiaAltarRecipes = new ArrayList<AltarRecipes>();
	
	/**
	 * 
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
