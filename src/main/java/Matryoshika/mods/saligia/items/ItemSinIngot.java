package Matryoshika.mods.saligia.items;

import Matryoshika.mods.saligia.saligia;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;

public class ItemSinIngot extends Item {
	
	public ItemSinIngot(ToolMaterial sin) {
		super();
		
		this.setUnlocalizedName("ItemSinIngot");
		this.setTextureName(saligia.MODID+":sinMetalIngot");
	}

	
}