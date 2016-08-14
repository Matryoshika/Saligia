package se.Matryoshika.Saligia.Utils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;
import se.Matryoshika.Saligia.Content.ContentRegistry;

/**
 * This class was created by Matryoshika Aug 9, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class SaligiaFuelHandler{
	
	private static IFuelHandler fuelHandler;

	
	public static IFuelHandler getFuelHandler(){
		if(fuelHandler == null){
			fuelHandler = new FuelHandler();
		}
		return fuelHandler;
	}
	
	private static class FuelHandler implements IFuelHandler{
		private static final int CHARCOAL_TIME = 16000;
		private final Item charcoalBlock = Item.getItemFromBlock(ContentRegistry.charcoalBlock);
		
		@Override
		public int getBurnTime(ItemStack fuel){
			if(fuel == null){
				return 0;
			}
			if(fuel.getItem() == charcoalBlock){
				return CHARCOAL_TIME;
			}
			return 0;
		}
	}
}
