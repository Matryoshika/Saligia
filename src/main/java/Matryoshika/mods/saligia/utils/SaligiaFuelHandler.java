package Matryoshika.mods.saligia.utils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import Matryoshika.mods.saligia.blocks.saligia_Blocks;
import cpw.mods.fml.common.IFuelHandler;

public class SaligiaFuelHandler{
	
	private static IFuelHandler fuelHandler;
	
	public static IFuelHandler getFuelHandler() {
        if (fuelHandler == null)
            fuelHandler = new FuelHandler();
        return fuelHandler;
    }
	
	private static class FuelHandler implements IFuelHandler{
		private static final int CHARCOAL_TIME = 16000;
		private final Item charcoalBlock = Item.getItemFromBlock(saligia_Blocks.CharcoalBlock);
		
		@Override
		public int getBurnTime(ItemStack fuel) {
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
