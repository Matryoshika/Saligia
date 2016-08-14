package se.Matryoshika.Saligia.Utils;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * This class was created by Matryoshika Aug 8, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class AltarRecipes {

	public int animun;
	public int tier;
	ItemStack result;
	List<ItemStack> ingredients;
	public int outputAmount;
	
	public AltarRecipes(ItemStack result, int outputAmount, int tier, int animun, ItemStack... ingredients){
		this.animun = animun;
		this.result = result;
		this.tier = tier;
		this.outputAmount = outputAmount;
		
		List<ItemStack> ingredientsToAdd = new ArrayList();
		for(ItemStack rec : ingredients){
			if(rec instanceof ItemStack){
				ingredientsToAdd.add(rec);
			}
			else{
				throw new IllegalArgumentException("Invalid Recipe-ingredient");
			}
			this.ingredients = ingredientsToAdd;
				
		}
	}
	
	public ItemStack result(){
		return result;
	}
	
	public List<ItemStack> getIngredients(){
		return new ArrayList(ingredients);
	}
	
	public int getTier(){
		return tier;
	}
	
	public int getOutputAmount(){
		return outputAmount;
	}
	
	boolean compareItems(ItemStack stackx, ItemStack stacky) {
		return stackx.getItem() == stacky.getItem() && stackx.getItemDamage() == stacky.getItemDamage();
	}
	
	public boolean matches(IInventory inv) {
		List<ItemStack> inputsMissing = new ArrayList(ingredients);

		for(int i = 0; i < inv.getSizeInventory(); i++) {
			ItemStack stack = inv.getStackInSlot(i);
			if(stack == null)
				break;

			int stackIndex = -1, oredictIndex = -1;

			for(int j = 0; j < inputsMissing.size(); j++) {
				ItemStack input = inputsMissing.get(j);
				 if(input instanceof ItemStack && compareItems((ItemStack) input, stack)) {
					stackIndex = j;
					break;
				}
			}

			if(stackIndex != -1)
				inputsMissing.remove(stackIndex);
			else if(oredictIndex != -1)
				inputsMissing.remove(oredictIndex);
			else return false;
		}

		return inputsMissing.isEmpty();
	}
	



	public int getAnimunUsage() {
		return animun;
	}
}
